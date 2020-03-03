/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mai.textanalyzer.web.vaadin.pages.classification;

import com.mai.textanalyzer.classifier.common.ClassifierEnum;
import com.mai.textanalyzer.classifier.common.TextClassifier;
import com.mai.textanalyzer.constants.Constants;
import com.mai.textanalyzer.creater.Creater;
import com.mai.textanalyzer.indexing.common.Indexer;
import com.mai.textanalyzer.indexing.common.IndexerEnum;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Sergey
 */
public class LoadingComponents {

    private static LoadingComponents instance;
        private LoadingComponents(){}
        public static LoadingComponents getInstance(){
        if(instance == null){		//если объект еще не создан
            instance = new LoadingComponents();	//создать новый объект
        }
        return instance;		// вернуть ранее созданный объект
    }    
    //public static String rootdir = "D:\\modeluper";
    private static final File rootDir = new File(Constants.getRootdir());
    private static final Indexer doc2vec;
    private static final Indexer tfIdf;
    private static final List<TextClassifier> doc2vecClassifiers;
    private static final List<TextClassifier> tfIdfClassifiers;

    static {
    //public static void init() {
        trace(">>>Starting loading components");  
        trace("rootdir = " + Constants.getRootdir());
        Creater.checkRootFolderStructure(rootDir, null, null);
        doc2vec = Creater.checkExistIndexerModel(rootDir, IndexerEnum.DOC2VEC) ? Creater.loadIndexer(IndexerEnum.DOC2VEC, rootDir) : null;
        tfIdf = Creater.checkExistIndexerModel(rootDir, IndexerEnum.TF_IDF) ? Creater.loadIndexer(IndexerEnum.TF_IDF, rootDir) : null;
        tfIdfClassifiers = new ArrayList<>();
        for (ClassifierEnum classifierEnum : ClassifierEnum.values()) {
            TextClassifier classifier = Creater.checkExistClassifierModel(rootDir, classifierEnum, IndexerEnum.TF_IDF) ? Creater.loadClassifier(rootDir, classifierEnum, IndexerEnum.TF_IDF) : null;
            if (classifier != null) {
                tfIdfClassifiers.add(classifier);
            }
        }
        doc2vecClassifiers = new ArrayList<>();
        trace("ClassifierEnum.values()= " + ClassifierEnum.values());
        for (ClassifierEnum classifierEnum : ClassifierEnum.values()) {
            trace("ClassifierEnum= " + classifierEnum);
            TextClassifier classifier = Creater.checkExistClassifierModel(rootDir, classifierEnum, IndexerEnum.DOC2VEC) ? Creater.loadClassifier(rootDir, classifierEnum, IndexerEnum.DOC2VEC) : null;
            trace("classifier= " + classifier);
            if (classifier != null) {
                doc2vecClassifiers.add(classifier);
            }
        }
    }

    public static File getRootDir() {
        return new File(rootDir.getPath());
    }

    public static Indexer getIndexer(IndexerEnum indexerEnum) {
        if (indexerEnum == IndexerEnum.DOC2VEC) {
            return doc2vec;
        } else if (indexerEnum == IndexerEnum.TF_IDF) {
            return tfIdf;
        }
        throw new UnsupportedOperationException();
    }

    public static TextClassifier getClassifier(ClassifierEnum classifierEnum, IndexerEnum indexerEnum) {
        if (indexerEnum == IndexerEnum.DOC2VEC) {
            for (TextClassifier classifier : doc2vecClassifiers) {
                if (classifier.getClassifierEnum() == classifierEnum) {
                    return classifier;
                }
            }
            return null;
        } else if (indexerEnum == IndexerEnum.TF_IDF) {
            for (TextClassifier classifier : tfIdfClassifiers) {
                if (classifier.getClassifierEnum() == classifierEnum) {
                    return classifier;
                }
            }
            return null;
        }
        throw new UnsupportedOperationException();
    }
    
    private static void trace(String s) {
//        System.out.println(s);
    }
}
