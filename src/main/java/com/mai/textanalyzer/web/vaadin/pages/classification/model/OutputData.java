/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mai.textanalyzer.web.vaadin.pages.classification.model;

import java.util.ArrayList;

/**
 *
 * @author asamokhin
 */
public class OutputData {
    private String classifier;
    private ArrayList<PredictionData>  newmap;

    public OutputData(String classifier, ArrayList<PredictionData> newmap) {
        this.classifier = classifier;
        this.newmap = newmap;
    }
    
    public String getClassifier() {
        return classifier;
    }

    public void setClassifier(String classifier) {
        this.classifier = classifier;
    }

    public ArrayList<PredictionData> getNewmap() {
        return newmap;
    }

    public void setNewmap(ArrayList<PredictionData> newmap) {
        this.newmap = newmap;
    }
      
}
