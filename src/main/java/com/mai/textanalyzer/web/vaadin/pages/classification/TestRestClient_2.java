/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mai.textanalyzer.web.vaadin.pages.classification;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.io.*;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.HttpStatusCodeException;
import java.nio.file.Files;
import java.nio.file.Paths;
/**
 *
 * @author asamokhin
 */
public class TestRestClient_2 {
    public static void main(String[] args) throws Exception {
InputData input = new InputData();
ByteArrayOutputStream outputBuffer = new ByteArrayOutputStream();

RestTemplate restTemplate = new RestTemplate();

//byte[] array = Files.readAllBytes(Paths.get("d:\\utils\\RootFolderSize62407\\DocForTest\\Административное право\\368a89d25d2711b5f75df9320703fbee.xml.txt"));
//byte[] array = Files.readAllBytes(Paths.get("d:\\utils\\RootFolderSize62407\\DocForTest\\Авиация и космонавтика\\54564c0594e8596aea5d46111e9349bf.xml.txt"));
//byte[] array = Files.readAllBytes(Paths.get("d:\\utils\\RootFolderSize62407\\DocForTest\\Биология\\49f4053fe55b28cd0fd687a46aad651f.xml.txt"));
//byte[] array = Files.readAllBytes(Paths.get("d:\\utils\\RootFolderSize62407\\DocForTest\\Кибернетика\\2c12d27738b268a30035c6c61e49c4e2.xml.txt"));
byte[] array = Files.readAllBytes(Paths.get(args[0]));

outputBuffer.write(array);
String text = new String(outputBuffer.toByteArray(), "UTF-8");
System.out.println(text);
/*
          FileReader reader = new FileReader("d:\\utils\\RootFolderSize62407\\DocForTest\\Административное право\\368a89d25d2711b5f75df9320703fbee.xml.txt");
        //FileReader reader = new FileReader("d:\\utils\\RootFolderSize62407\\DocForTest\\Авиация и космонавтика\\54564c0594e8596aea5d46111e9349bf.xml.txt");
        //FileReader reader = new FileReader("d:\\utils\\RootFolderSize62407\\DocForTest\\Биология\\49f4053fe55b28cd0fd687a46aad651f.xml.txt");
        //FileReader reader = new FileReader("d:\\utils\\RootFolderSize62407\\DocForTest\\Кибернетика\\2c12d27738b268a30035c6c61e49c4e2.xml.txt");
        //FileReader reader = new FileReader("d:\\utils\\RootFolderSize62407\\DocForTest\\Иностранный язык\\4e2ed60c6f34ca6b6ac40c3cf6a382ef.xml.txt");
            String text = "";
            int c;
            int i = 0;
            while((c=reader.read())!=-1){
                 
                text = text + (char)c;
            }
*/
ArrayList<String> classifier = new ArrayList<String>();
//ArrayList<String> classifier = null;

classifier.add("NAIVE_BAYES");
classifier.add("SVM");
classifier.add("IBK");
classifier.add("LR");
classifier.add("RF");
classifier.add("BAGGING");
classifier.add("BOOSTING");
classifier.add("STACKING");
classifier.add("MYLTI_CLASSIFIER");

input.setText(text);
input.setClassifier(classifier);
input.setModel("DOC2VEC");
HttpEntity<InputData> entity = new HttpEntity<InputData>(input);
ResponseEntity<String> response = null;

 try {
    response = restTemplate.postForEntity(args[1], entity, String.class);
 }
 catch (HttpStatusCodeException e) {
     System.out.println(e.getResponseBodyAsString());
     return;
 }
        try(FileWriter writer = new FileWriter(args[2], false))
        {
            writer.write(response.getBody());
            writer.flush();
        }
        catch(IOException ex){
             
            System.out.println(ex.getMessage());
        } 
        
System.out.println("finish successful!");

    }
   
}
