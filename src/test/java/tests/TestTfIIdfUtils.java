/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;

import com.mai.textanalyzer.indexing.tf_idf.TfIIdfUtils;
import java.io.IOException;
import org.deeplearning4j.bagofwords.vectorizer.TfidfVectorizer;
import org.deeplearning4j.models.word2vec.VocabWord;
import org.deeplearning4j.models.word2vec.wordstore.VocabCache;
import org.junit.Test;

/**
 *
 * @author Sergey
 */
public class TestTfIIdfUtils {

    String text = "скучный дождь";

//    @Test
    public void testMethod() throws IOException {

//        TfidfVectorizer tfidfVectorizer = TfIIdfUtils.createModel();
//
//        log.info(tfidfVectorizer.transform(text));
//
//        VocabCache<VocabWord> vocabCache = tfidfVectorizer.getVocabCache();
//        vocabCache.saveVocab();
//        log.info("cache.numWords()- " + vocabCache.numWords());
//        log.info("cache.totalNumberOfDocs()- " + vocabCache.totalNumberOfDocs());
//
//        //Saving
//        TfIIdfUtils.saveModel(tfidfVectorizer);
//
//        TfidfVectorizer tfidfVectorizer2 = TfIIdfUtils.loadModel();
//        
////        VocabConstructor<VocabWord> constructor = new VocabConstructor.Builder<VocabWord>()
////                .setTargetVocabCache(vocabCache).setStopWords(new ArrayList<>())
////                .allowParallelTokenization(false).build();
////        VocabCache<VocabWord> buildCache = constructor.buildJointVocabulary(true, false);
//
//        log.info("buildCache.numWords()- " + tfidfVectorizer2.getVocabCache().numWords());
//        log.info("buildCache.totalNumberOfDocs()- " + tfidfVectorizer2.getVocabCache().totalNumberOfDocs());
//
//        log.info(tfidfVectorizer2.transform(text));
    }
}
