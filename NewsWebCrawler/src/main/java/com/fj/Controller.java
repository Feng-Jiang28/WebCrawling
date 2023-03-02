package com.fj;

import java.io.*;
import java.util.Properties;

public class Controller {
    public static void main(String[] args) throws Exception{
        Properties props = new Properties();
        //FileReader file = new FileReader("src/main/resources/config.properties");
        FileInputStream fis = new FileInputStream("src/main/resources/config.properties");
        //InputStream inputStream = getClass().getClassLoader().getResourceAsStream("config.properties");
        props.load(fis);

        String max = props.getProperty("maximum_pages");
        // System.out.println(max);
    }
}
