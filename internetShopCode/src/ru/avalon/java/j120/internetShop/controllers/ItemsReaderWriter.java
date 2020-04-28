/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.avalon.java.j120.internetShop.controllers;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import ru.avalon.java.j120.internetShop.models.Item;

/**
 *
 * @author user
 */
// класс который работает с записью и чтением каталогов товаров из файла

public class ItemsReaderWriter {
    
    // метод записи в файл. На входе путь записи и  коллекция Товаров
    public static void writeItems(String itemsPath, ArrayList<Item> items){
        	
        // пробуем записать в файл
        try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(itemsPath)))
        {
            // перевод строки в байты
            for (Item item : items)
            {
                bufferedWriter.write(item.toString() + "\n");
	    }       
        }
        catch(IOException ex){
            System.out.println("Error. Не удалось записать список товаров");
            ex.printStackTrace();
        }
        
    }
    
    // метод чтения из файла. На входе путь записи и  коллекция Товаров
    public static ArrayList<Item> readItems(String itemsPath){
                
        // создаем коллекцию товаров
        ArrayList<Item> items = new ArrayList<Item>();
        try (BufferedReader br = new BufferedReader(new FileReader(itemsPath))){
            String str;
            while ((str = br.readLine()) != null) {
                
                String[] strSplit=str.split(";");
                // пробуем создать объект товар и добавить его в коллекцию
                try{
                    Item item = new Item(strSplit[0], strSplit[1], strSplit[2], Integer.parseInt(strSplit[3]), Integer.parseInt(strSplit[4]));
                    items.add(item);
                }
                catch(Exception ex){
                    System.out.println("Error. Не удалось прочитать товар с артикулом: " + strSplit[0] + ". Exeption: " + ex.getMessage());
                    break;
                }
               
            }
              
        return items;
        }
        catch(IOException ex){
            System.out.println("Error. Не удалось прочитать список товаров");
            ex.printStackTrace();
            return null;
        }
    }
}
