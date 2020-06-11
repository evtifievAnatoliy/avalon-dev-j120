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
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import ru.avalon.java.j120.internetShop.configuration.Configuration;
import ru.avalon.java.j120.internetShop.models.Item;
import ru.avalon.java.j120.internetShop.models.OrderPosition;

/**
 *
 * @author user
 */
// класс который работает с записью и чтением каталогов товаров из файла

public interface AbstractItemsReaderWriter {
    
               
    // метод записи в файл. На входе путь записи и  коллекция Товаров
    public void writeItems(ArrayList<Item> items, Item newItem, ArrayList<Item> updateItems) throws IOException;
        	
    
    // метод чтения из файла. На входе путь записи и  коллекция Товаров
    public ArrayList<Item> readItems() throws IOException, ParseException;
        
}
