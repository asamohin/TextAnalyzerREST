/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mai.textanalyzer.word_processing;

import org.deeplearning4j.text.tokenization.tokenizer.preprocessor.CommonPreprocessor;
import ru.textanalysis.tfwwt.jmorfsdk.*;
import ru.textanalysis.tfwwt.jmorfsdk.load.*;
/**
 *
 * @author Sergey
 */
public class MyPreprocessor extends CommonPreprocessor {

    /*
        CommonPreprocessor will apply the following regex to each token:
        [\d\.:,"'\(\)\[\]|/?!;]+
        So, effectively all numbers, punctuation symbols and some special symbols
        are stripped off.
        Additionally it forces lower case for all tokens.
     */
    
    JMorfSdk jMorfSdk = JMorfSdkLoad.loadFullLibrary();
    
    @Override
    public String preProcess(String token) {
        //return PorterStremmer.stem(super.preProcess(token));
        return InitialForm.getInitialForm(super.preProcess(token), jMorfSdk);
    }

}
