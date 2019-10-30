/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mai.textanalyzer.dao.accuracy;

import com.mai.textanalyzer.classifier.common.ClassifierEnum;
import com.mai.textanalyzer.indexing.common.IndexerEnum;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

/**
 *
 * @author artee
 */
public class AccuracyGetter {
        public static void main(String[] args) throws Exception {
            ClassifierEnum classifierEnum = ClassifierEnum.NAIVE_BAYES;
            IndexerEnum indexerEnum = IndexerEnum.DOC2VEC;
            AccuracyService as = new AccuracyService();
            AccuracyDao na = new AccuracyDao();
            //na.setJdbcTemplate(null);
            //as.setAccuracyDao(na);
            System.out.println(as.getMapAccuracy(classifierEnum, indexerEnum));
        }
}
