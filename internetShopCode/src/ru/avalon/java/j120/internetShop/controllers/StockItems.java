/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.avalon.java.j120.internetShop.controllers;

import java.util.ArrayList;
import ru.avalon.java.j120.internetShop.models.Item;
import ru.avalon.java.j120.internetShop.models.OrderPosition;

/**
 *
 * @author user
 */
// Класс который редактирует и добавляет товары на складе

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
    // метод уменьшения остатка товаров на складе
    public void reduceTheStockBalance(ArrayList<OrderPosition> positions){
        for (OrderPosition position : positions){
            for(Item stockItem : this.items){
                if(position.getItem().getArticle().equals(stockItem.getArticle())){
                    if (stockItem.getStockBalance() >= position.getNumberOfItems())
                        stockItem.reduceTheStockBalance(position.getNumberOfItems());
                    else
                        throw new IllegalArgumentException("Error. Остаток на складе меньше кол-ва заказанных позиций товара. "
                                + "\nДля товара с артикулом: " + position.getItem().getArticle() + 
                                "\nОстаток на складе: " + stockItem.getStockBalance());
                   
                }
                   
            }
            
        }
    
    }

    public Iterable<Item> getItems() {
        return items;
    }

    public ArrayList<Item> getItemsAsList() {
        return items;
    }
    
    
}
