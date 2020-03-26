/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mai.textanalyzer.dao.accuracy;

import com.mai.textanalyzer.classifier.common.ClassifierEnum;
import com.mai.textanalyzer.indexing.common.IndexerEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

/**
 *
 * @author artee
 */
public class AccuracyGetter {
        private static final Logger log = LoggerFactory.getLogger(AccuracyGetter.class.getName()); 
        public static void main(String[] args) throws Exception {
            ClassifierEnum classifierEnum = ClassifierEnum.NAIVE_BAYES;
            IndexerEnum indexerEnum = IndexerEnum.DOC2VEC;
            AccuracyService as = new AccuracyService();
            AccuracyDao na = new AccuracyDao();
            //na.setJdbcTemplate(null);
            //as.setAccuracyDao(na);
            log.info(as.getMapAccuracy(classifierEnum, indexerEnum).toString());
        }
}
