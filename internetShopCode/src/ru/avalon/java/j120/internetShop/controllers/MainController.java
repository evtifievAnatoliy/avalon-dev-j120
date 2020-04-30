/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.avalon.java.j120.internetShop.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import ru.avalon.java.j120.internetShop.models.Item;

/**
 *
 * @author user
 */
// Класс который инициализирует заказы и товары
// Считывает коллекции из файлов и записывает их в файл

public class MainController {
    
    private String itemsPath;
    private StockItems stockItems;

    ItemsReaderWriter itemsReaderWriter = new ItemsReaderWriter();
    
    public MainController(String itemsPath) throws IOException {
        this.itemsPath = itemsPath;
        stockItems = new StockItems(itemsReaderWriter.readItems(itemsPath));
        
    }

    public StockItems getStockItems() {
        return stockItems;
    }
    
    
    
        
    public void writeItems() throws IOException{
        itemsReaderWriter.writeItems(itemsPath, stockItems.getItems());
    }
    
    
    
}
