package com.fj;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;



public class Config {

    public static final int KB = 1024;
    public static final int MB = 1024 * 1024;


    private static Properties properties;
   static{
       try{
           properties = new Properties();
           FileInputStream input = new FileInputStream("src/main/resources/config.properties");
           properties.load(input);
       }
       catch (IOException ex) {
           ex.printStackTrace();
           // handle the exception as needed
       }
   }

   public static String getProperty(String key){
       return properties.getProperty(key);
   }
   public static int getIntProperty(String key){
       return Integer.parseInt(properties.getProperty(key));
   }


}
