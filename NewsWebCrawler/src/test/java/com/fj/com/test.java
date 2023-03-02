package com.fj.com;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class test {
    @Test
    public void test(){
        System.out.println("Hello world!");
    }

    @Test
    public void testProperties() throws IOException {
        Properties props = new Properties();
        //FileReader file = new FileReader("src/main/resources/config.properties");
        FileInputStream fis = new FileInputStream("src/main/resources/config.properties");
        //InputStream inputStream = getClass().getClassLoader().getResourceAsStream("config.properties");
        props.load(fis);
    }
}
