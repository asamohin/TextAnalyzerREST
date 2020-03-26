/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mai.textanalyzer.creater;
import com.mai.textanalyzer.classifier.common.ClassifierEnum;
import com.mai.textanalyzer.indexing.common.IndexerEnum;
import com.mai.textanalyzer.web.vaadin.pages.multiclassifierlearning.MultiLearner;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 *
 * @author asamokhin
 */
public class LearnClassifief {
    private static final Logger log = LoggerFactory.getLogger(LearnClassifief.class.getName());      
    public static void main(String[] args) throws Exception {
        Creater learndata = new Creater();
        List<ClassifierEnum> listClassifier = new ArrayList();
                listClassifier.add(ClassifierEnum.NAIVE_BAYES);
                listClassifier.add(ClassifierEnum.SVM);
                listClassifier.add(ClassifierEnum.IBK);
                listClassifier.add(ClassifierEnum.LR);
                listClassifier.add(ClassifierEnum.RF);
                listClassifier.add(ClassifierEnum.BAGGING);
                listClassifier.add(ClassifierEnum.BOOSTING);
                listClassifier.add(ClassifierEnum.STACKING);              
     
        IndexerEnum indexerEnum = IndexerEnum.DOC2VEC;
        File rootDir = new File(args[0]);
        for(int i = 0; i < listClassifier.size(); i++) {
        log.info("starting learn " + listClassifier.get(i));
        learndata.createAndSaveClassifier(rootDir, listClassifier.get(i), indexerEnum,false);
        log.info("learn " + listClassifier.get(i) + " finished successfully");
        }
    }
}
