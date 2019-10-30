package com.mai.textanalyzer.web.vaadin.pages.classification;

import com.mai.textanalyzer.classifier.common.ClassifierEnum;
import com.mai.textanalyzer.classifier.common.Prediction;
import com.mai.textanalyzer.classifier.common.TextClassifier;
import com.mai.textanalyzer.indexing.common.Indexer;
import com.mai.textanalyzer.indexing.common.IndexerEnum;
import com.vaadin.ui.CustomComponent;
import java.util.List;
import org.nd4j.linalg.api.ndarray.INDArray;
import java.util.ArrayList;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;
import java.util.HashMap;
//import java.math.BigDecimal;
//import java.math.RoundingMode;

@RestController
public class ClassificationComponent extends CustomComponent {
    
    public static final String model_DOC2VEC = "DOC2VEC";
    public static final String model_TF_IDF = "TF_IDF";
    
    public static final String classifier_NAIVE_BAYES = "NAIVE_BAYES";
    public static final String classifier_SVM = "SVM";
    public static final String classifier_IBK = "IBK";
    public static final String classifier_LR = "LR";
    public static final String classifier_RF = "RF";
    public static final String classifier_MYLTI_CLASSIFIER = "MYLTI_CLASSIFIER";
    public static final String classifier_BAGGING = "BAGGING";
    public static final String classifier_BOOSTING = "BOOSTING";
    public static final String classifier_STACKING = "STACKING";    

           
           @RequestMapping(value = "/predictions", method = {RequestMethod.POST}, consumes = {"application/json"})
    public HashMap<String,  HashMap<String, String>> getPredictions(@RequestBody InputData input) throws Exception{
            int i = 0;
            int j = 0;
            String text = input.getText();
            ArrayList<String> classifier = input.getClassifier();
            String model = input.getModel();
            
            ClassifierEnum selectedClassifier = null;
            HashMap<String, String> map = new HashMap<>();
            HashMap<String,  HashMap<String, String>>  newmap = new HashMap<>();
            IndexerEnum indexerEnum = null;
            Indexer indexer = null;
            TextClassifier textClassifier = null;
            List<Prediction> predictions = null;
            
            if(model.equals(model_DOC2VEC)) {
                indexerEnum = IndexerEnum.DOC2VEC;
                indexer = LoadingComponents.getIndexer(IndexerEnum.DOC2VEC);
            }
            if(model.equals(model_TF_IDF)) {
                indexerEnum = IndexerEnum.TF_IDF;
                indexer = LoadingComponents.getIndexer(IndexerEnum.TF_IDF);                
            } 
            INDArray iNDArray = indexer.getIndex(text);            
            for(i = 0; i< classifier.size(); i++) {
            map.clear();
            selectedClassifier = null;
            switch (classifier.get(i)) {
                case classifier_NAIVE_BAYES:  selectedClassifier = ClassifierEnum.NAIVE_BAYES;
                         break;
                case classifier_SVM:  selectedClassifier = ClassifierEnum.SVM;
                         break;
                case classifier_IBK:  selectedClassifier = ClassifierEnum.IBK;
                         break;
                case classifier_LR:  selectedClassifier = ClassifierEnum.LR;
                         break;
                case classifier_RF:  selectedClassifier = ClassifierEnum.RF;
                         break;
                case classifier_MYLTI_CLASSIFIER:  selectedClassifier = ClassifierEnum.MYLTI_CLASSIFIER;
                         break;
                case classifier_BAGGING:  selectedClassifier = ClassifierEnum.BAGGING;
                         break;
                case classifier_BOOSTING:  selectedClassifier = ClassifierEnum.BOOSTING;
                         break;
                case classifier_STACKING: selectedClassifier = ClassifierEnum.STACKING;
                         break;
            }
            System.out.println(selectedClassifier);
            //System.out.println(map.size());
            if (iNDArray == null) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "В тексте недостаточно информации для его классификации");
            }            
            if (selectedClassifier == null) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Не выбран классификатор");
            }
            textClassifier = null;
            textClassifier = LoadingComponents.getClassifier(selectedClassifier, indexerEnum);
            if (textClassifier == null) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Классификатор "+ classifier.get(i) +" еще не обучен");
            }
            predictions = textClassifier.getDistribution(iNDArray);
            //System.out.println(predictions.get(0).getValue());
            //for (Prediction prediction : predictions) {
            for(j = 0; j < predictions.size(); j++) {
            System.out.println(predictions.get(j).getTopic() + " - " + predictions.get(j).getValue() + " - " + predictions.get(j));
            map.put(predictions.get(j).getTopic(), predictions.get(j).toString());
            //map.put(prediction.getTopic(), new BigDecimal(prediction.getValue()).setScale(10, RoundingMode.UP).doubleValue());
            }
            
            newmap.put(classifier.get(i), new HashMap<>(map));
            
            }
            return newmap;
    }          
      
}