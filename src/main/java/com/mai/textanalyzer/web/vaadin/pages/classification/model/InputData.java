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
public class InputData {
    private String text;
    //private String classifier;
    private ArrayList<String> classifier = new ArrayList<>();
    private String model;
    private int n;

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }
    
    public void setText(String text) {
        this.text = text;
    }
    public void setClassifier(ArrayList<String> classifier) {
        this.classifier = classifier;
    }
    public void setModel(String model) {
        this.model = model;
    }    
    
    public String getText() {
        return this.text;
    }
    public ArrayList<String> getClassifier() {
        return this.classifier;
    }
    
    public String getModel() {
        return this.model;
    }     
}
