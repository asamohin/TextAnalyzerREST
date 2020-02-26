/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mai.textanalyzer.constants;

/**
 *
 * @author artee
 */
public class Constants {
        private static Constants instance;
        private Constants(){}
        public static Constants getInstance(){
        if(instance == null){		//если объект еще не создан
            instance = new Constants();	//создать новый объект
        }
        return instance;		// вернуть ранее созданный объект
    }
    private static String rootdir = null;

    public static String getRootdir() {
        return rootdir;
    }

    public static void setRootdir(String rootdir) {
        Constants.rootdir = rootdir;
    }
    
}
