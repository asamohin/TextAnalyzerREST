/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mai.textanalyzer.web.vaadin.pages.multiclassifierlearning;

import com.mai.textanalyzer.web.vaadin.pages.classification.model.InputData;
import java.io.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import org.h2.tools.DeleteDbFiles;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;
/**
 *
 * @author asamokhin
 */
public class MultiLearner {
    private static final String rootPath = "d:\\modeluper\\DocForTest\\";
    private static final String[] classifierNames = {"NAIVE_BAYES", "SVM", "IBK", "LR", "RF", "BAGGING", "BOOSTING", "STACKING"};
    //private static final String[] classifierNames = {"STACKING"};
    private static final int top = 3;
    private static final String VECTORIZATION_ID = "1";
    public static void main(String[] args) throws Exception {
        InputData input = new InputData();

        File folderDir = new File(rootPath);
        //названия директорий
        String[] dirNames = folderDir.list(new FilenameFilter() {
 
            @Override public boolean accept(File folder, String name) {
                return name.endsWith("");
            }
            
        });
        ArrayList<String> classifier = new ArrayList<>();
        for (int j = 0; j < classifierNames.length; j++) {
            classifier.clear();
            classifier.add(classifierNames[j]);
            input.setClassifier(classifier);
            input.setModel("DOC2VEC");
            int TP = 0;
            int FN = 0;
            int FP = 0;
            int TN = 0;   
            HashMap<String, Double> resTopic = new HashMap<>();
            HashMap<String, Double> anotherResTopic = new HashMap<>();
            double F = 0.0;
            double precision = 0.0;
            double recall = 0.0;
            for (String dirName : dirNames) {
                TP = 0;
                FN = 0;
                FP = 0;
                TN = 0;             
                //System.out.println("dirName = " + dirName);
                String[] fileNames = getFileList(dirName);
                //System.out.println("fileNames = " + fileNames.length);            
                for (String fileName : fileNames) {
                //работаем с темой dirName, проанализировали все тексты из этой области
                resTopic = runClassifier(fileName, dirName, input);
                if (sortedClassifierResult(resTopic, dirName)) {
                      TP = TP + 1;
                  }
                }
                //вычислили другую директорию
                String anotherDir = runAnotherClassifier(dirNames, dirName);  
                //список файлов в директории dirNames[i]            
                String[] anotherFileNames = getFileList(anotherDir);
                for (String anotherFileName : anotherFileNames) {  
                    anotherResTopic = runClassifier(anotherFileName, anotherDir, input);   
                    if (sortedClassifierResult(anotherResTopic, dirName)) {
                          FP = FP + 1;
                      }                
                }              
                FN = fileNames.length - TP;
                TN = anotherFileNames.length - FP;
                System.out.println("FN = " + FN + "; TP = " + TP + "; " + "TN = " + TN + "; FP = " + FP);
                if (TP + FP != 0)
                    precision = (double)TP / (double)(TP + FP);
                else
                    precision = 0.0;
                if (TP + FN != 0)
                    recall = (double)TP / (double)(TP + FN);
                else
                    recall = 0.0;     
                if (precision + recall != 0)
                    F = 2.0 * precision * recall / (precision + recall);
                else
                    F = 0.0;
                System.out.println("precision = " + precision + "; recall = " + recall + "; " + "F = " + F);
                System.out.println("classifier = " + precision + "; topic = " + dirName);
                baseInsertIntoTopic(dirName);
                baseInsertIntoAccuracy(classifierNames[j], String.valueOf(fileNames.length), String.valueOf(F));
            }
        }
    }
    
    
    public static String runAnotherClassifier(String[] dirNames, String currentDirectory) {
        for (String dirName : dirNames) {
            if (!dirName.equalsIgnoreCase(currentDirectory)) {
                return dirName;
            }
        }
        return "";
    }
    
    public static HashMap<String,  Double> runClassifier(String fileName, String dirName, InputData input) throws IOException, ParseException {
            HashMap<String,  Double>  newmap = new HashMap<>();
            RestTemplate restTemplate = new RestTemplate();
            byte[] array;
            ByteArrayOutputStream outputBuffer = new ByteArrayOutputStream();
            String text;
            HttpEntity<InputData> entity = new HttpEntity<>(input);
            ResponseEntity<String> response;
            
                array = Files.readAllBytes(Paths.get(rootPath + dirName + "\\" + fileName));
                outputBuffer.write(array);
                text = new String(outputBuffer.toByteArray(), "UTF-8");
                input.setText(text);
                try {
                    response = restTemplate.postForEntity("http://localhost:8080/TextAnalizerREST-0.4/predictions", entity, String.class);
                    newmap = getClassifierResult(response.getBody(), input.getClassifier().get(0));                  
                }
                catch (HttpStatusCodeException e) {
                    System.out.println(e.getResponseBodyAsString());
                } 
            
            return newmap;
    }
    
    public static String[] getFileList(String fileDir) {
        File anotherFolderFiles = new File(rootPath + fileDir);        
            //список файлов в директории dirNames[i]
            String[] dirNames = anotherFolderFiles.list(new FilenameFilter() {

                @Override public boolean accept(File folder, String name) {
                    return name.endsWith(".txt");
                }

            });        
        return dirNames;    
    }    
        
    public static HashMap<String, Double> getClassifierResult(String json, String classifierName) throws ParseException {
        JSONParser parser = new JSONParser();
        JSONObject json0 = (JSONObject) parser.parse(json);
        String json1 = json0.get(classifierName).toString();
        JSONObject json2 = (JSONObject) parser.parse(json1);
        return json2;    
    } 

    
    public static boolean sortedClassifierResult(HashMap<String,  Double>  map, String topic) {
        Object[] list = map.entrySet().stream()
            .sorted(HashMap.Entry.<String,  Double>comparingByValue().reversed()).toArray();
        for (int i = 0; i < top; i++) {
            if (list[i].toString().contains(topic)) return true;
        }
    return false;
    }
        
    public static void baseInsertIntoAccuracy(String classifierName, String docCount, String accuracy) throws ClassNotFoundException {
        DeleteDbFiles.execute("~", "test", true);

        Class.forName("org.h2.Driver");
        try (Connection conn = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/warn_test"); 
            Statement stat = conn.createStatement()) {
            String CLASSIFIER_ID = "";
            switch (classifierName) {
                case "NAIVE_BAYES":  CLASSIFIER_ID = "1";
                         break;
                case "SVM":  CLASSIFIER_ID = "2";
                         break;
                case "IBK":  CLASSIFIER_ID = "3";
                         break;
                case "LR":  CLASSIFIER_ID = "4";
                         break;
                case "RF":  CLASSIFIER_ID = "5";
                         break;
                case "BAGGING":  CLASSIFIER_ID = "8";
                         break;
                case "BOOSTING":  CLASSIFIER_ID = "9";
                         break;
                case "STACKING":  CLASSIFIER_ID = "10";
                         break;
            }            

            stat.executeUpdate("INSERT INTO ACCURACY VALUES (null, " + VECTORIZATION_ID + ", " + CLASSIFIER_ID + ", " + docCount + ", " + accuracy +");");

        } catch (Exception e) {
            e.printStackTrace();
        }          
    }    
    
    public static void baseInsertIntoTopic(String topic) throws ClassNotFoundException {
        DeleteDbFiles.execute("~", "test", true);

        Class.forName("org.h2.Driver");
        try (Connection conn = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/warn_test"); 
            Statement stat = conn.createStatement()) {

            stat.executeUpdate("INSERT INTO TOPIC VALUES (null, '" + topic + "');");

        } catch (Exception e) {
            e.printStackTrace();
        }          
    }    
}