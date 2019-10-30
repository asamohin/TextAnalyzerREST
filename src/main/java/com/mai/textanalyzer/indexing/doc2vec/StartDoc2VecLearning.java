/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mai.textanalyzer.indexing.doc2vec;

import com.mai.textanalyzer.creater.*;
import com.mai.textanalyzer.indexing.common.Indexer;
import java.io.File;

/**
 *
 * @author asamokhin
 */

public class StartDoc2VecLearning { 
        
        public static void main(String[] args) throws Exception {    
    File rootDir = new File(args[0]);
    Indexer doc2Vec = Creater.createAndSaveDoc2Vec(rootDir);
        }
}
