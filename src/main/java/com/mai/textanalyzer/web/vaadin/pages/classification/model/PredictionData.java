/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mai.textanalyzer.web.vaadin.pages.classification.model;

import java.util.Comparator;

/**
 *
 * @author artee
 */
public class PredictionData {
    private String topic;
    private double value;

    public PredictionData(String topic, double value) {
        this.topic = topic;
        this.value = value;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
    
    public static final Comparator<PredictionData> COMPARE_BY_VALUE = new Comparator<PredictionData>() {
        @Override
        public int compare(PredictionData lhs, PredictionData rhs) {
            return Double.compare(lhs.getValue(), rhs.getValue());
        }
    };    
    
}
