/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.avalon.java.j120.internetShop.controllers;

import java.util.ArrayList;
import ru.avalon.java.j120.internetShop.models.Item;

/**
 *
 * @author user
 */
// Класс который редактирует и добавляет товары
// Считывает коллекции из файлов и записывает их в файл
public class StockItems {

    private ArrayList<Item> items;
    
    public StockItems( ArrayList<Item> items) {
        this.items = items;
    }
    
    // метод добавление нового товара в коллекцию товаров
    public void addItem(Item item){
        for (Item i: items)
            if(i.getArticle().equals(item.getArticle()))
            {
                throw new IllegalArgumentException("Error. Не удалось записать товар. Товар с артикулом: " + item.getArticle() + " уже есть в базе. ");
            }
        this.items.add(item);
    }

    public Iterable<Item> getItems() {
        return items;
    }
    
}
