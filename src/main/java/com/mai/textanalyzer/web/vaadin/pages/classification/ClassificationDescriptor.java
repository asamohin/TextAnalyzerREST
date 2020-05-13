/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mai.textanalyzer.web.vaadin.pages.classification;

import java.util.ArrayList;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author artee
 */

@RestController
public class ClassificationDescriptor {
    @RequestMapping(value = "/classifierDescription", method = {RequestMethod.GET}, consumes = {"application/json"})
    @ResponseBody
    public ArrayList<String> getClassifierDescription() throws Exception{
        ArrayList<String> info = new ArrayList<>();
        info.add("Naive Bayes : Наивный баиесовский классификатор : NAIVE_BAYES");
        info.add("Support Vector Machine : свм : SVM");
        info.add("K-nearest Neighbours: к-ближайших : IBK");
        info.add("Logistic Regression : логистичекая регрессия : LR");
        info.add("Random Forest : дерево принятия решений : RF");
        info.add("Multi Classifier : мульти классификатор : MYLTI_CLASSIFIER");
        info.add("Bagging : беггинг : BAGGING");
        info.add("Boostring : бустинг : BOOSTING");
        info.add("Stacking : стекинг : STACKING");
        
        return info;
    }    
}
