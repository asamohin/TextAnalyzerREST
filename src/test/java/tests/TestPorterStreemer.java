/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;

import com.mai.textanalyzer.word_processing.PorterStremmer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Sergey
 */
public class TestPorterStreemer {
    private static final Logger log = LoggerFactory.getLogger(TestPorterStreemer.class.getName()); 
    
    public static void main(String[] args) {
        log.info(PorterStremmer.stem("колонизация"));
        log.info(PorterStremmer.stem("колонизацию"));
        log.info(PorterStremmer.stem("покупаешь"));
        log.info(PorterStremmer.stem("покупаем"));
        log.info(PorterStremmer.stem("покупают"));

    }
}
