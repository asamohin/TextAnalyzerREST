/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mai.textanalyzer.word_processing;

import ru.textanalysis.tawt.jmorfsdk.*;
import java.util.NoSuchElementException;

/**
 *
 * @author asamokhin
 */
public class InitialForm {

    public static String getInitialForm(String word, JMorfSdk jMorfSdk ) {
          //System.out.println("1- " + word);
          //JMorfSdk jMorfSdk = JMorfSdkLoad.loadFullLibrary();
          try {
            //System.out.println("2- " + jMorfSdk.getAllCharacteristicsOfForm(word).getFirst().getInitialFormString());
            return jMorfSdk.getAllCharacteristicsOfForm(word).getFirst().getInitialFormString();
          }
          catch (NoSuchElementException e) {
             return word;
          }
        }

}
