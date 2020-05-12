/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.avalon.java.j120.internetShop.configuration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 *
 * @author user
 */
public class Configuration {
    
    private static Configuration instance;
    private final static String DEFAULT_PATCH = "config.properties";
    private final static String USER_PATCH = "userConfig.properties";
    private Properties properties;
    
    
    private Configuration(){
        Properties defaultProperties = new Properties();
        try{
            defaultProperties.load(new FileInputStream(this.DEFAULT_PATCH));
        }
        catch(IOException e){
            throw new RuntimeException("Error. Файл к default настройкам: " + this.DEFAULT_PATCH + " отсуствует.");
        }
        
        properties = new Properties(defaultProperties);
        try{
            defaultProperties.load(new FileInputStream(USER_PATCH));
        }
        catch(IOException e){
            throw new RuntimeException("Error. Файл настройкам пользователя: " + USER_PATCH + " отсуствует.");
        }
        
    }
    
    public static Configuration getInstance(){
        if (instance == null)
            instance = new Configuration();
    return instance;
    }
    
    public String getProperty(String path){
        return properties.getProperty(path);
    }
    
    
}
