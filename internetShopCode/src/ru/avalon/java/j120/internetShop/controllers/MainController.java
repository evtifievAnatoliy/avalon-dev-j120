/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.avalon.java.j120.internetShop.controllers;

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
    
    ArrayList<Item> items;
    String itemsPath;

    ItemsReaderWriter itemsReaderWriter = new ItemsReaderWriter();
    
    public MainController(String itemsPath) {
        this.itemsPath = itemsPath;
        this.items = itemsReaderWriter.readItems(itemsPath);
    }
    
    // метод добавление нового товара в коллекцию товаров
    public void addItem(Item item){
        this.items.add(item);
    }
    
    public void writeItems(){
        itemsReaderWriter.writeItems(itemsPath, items);
    }
    
}
