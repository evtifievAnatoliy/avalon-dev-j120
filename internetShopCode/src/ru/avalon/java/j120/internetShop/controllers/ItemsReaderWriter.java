/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.avalon.java.j120.internetShop.controllers;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import ru.avalon.java.j120.internetShop.configuration.Configuration;
import ru.avalon.java.j120.internetShop.models.Item;

/**
 *
 * @author user
 */
// класс который работает с записью и чтением каталогов товаров из файла

public class ItemsReaderWriter {
    
               
    // метод записи в файл. На входе путь записи и  коллекция Товаров
    public void writeItems(ArrayList<Item> items) throws IOException{
        	
        if (items !=null) // проверяем на наличие элементов в коллекции
        { 
            // пробуем записать в файл
            try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(Configuration.getInstance().getProperty("items.Path")))){
                for (Item item : items)
                {
                    bufferedWriter.write(item.toString() + "\n");
                }       
            }
        }
        else
            throw new IllegalArgumentException("Error. Товары в файл не записаны, т.к. список товаров пуст. ");
    }
    
    // метод чтения из файла. На входе путь записи и  коллекция Товаров
    public ArrayList<Item> readItems() throws IOException{
                
        // создаем коллекцию товаров
        ArrayList<Item> items = new ArrayList<Item>();
            try(BufferedReader br = new BufferedReader(new FileReader(Configuration.getInstance().getProperty("items.Path")))){
                String str;
                while ((str = br.readLine()) != null) {
                    String[] strSplit=str.split(";");
                    // пробуем создать объект товар и добавить его в коллекцию
                    Item item = new Item(strSplit[0], strSplit[1], strSplit[2], Integer.parseInt(strSplit[3]), Integer.parseInt(strSplit[4]));
                    items.add(item);
            }
            return items;
        }
    }
}
