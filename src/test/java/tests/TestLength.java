/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Sergey
 */
public class TestLength {
    private static final Logger log = LoggerFactory.getLogger(TestLength.class.getName()); 
    
    public static void main(String[] args) {
        String[] str = new String[5];
        log.info(String.valueOf(str.length));
    }

}
