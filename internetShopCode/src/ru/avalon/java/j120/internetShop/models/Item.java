/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.avalon.java.j120.internetShop.models;

import java.io.Serializable;

/**
 *
 * @author eag
 */ 
// класс товаров
public class Item implements Serializable{
    private final String article; // артикул товара
    private String name; // название
    private String color; //цвет
    private int price; // цена
    private int stockBalance; //остаток на складе

    public Item(String article, String name, int price, int stockBalance) {
        if (price <= 0){
            throw new IllegalArgumentException("Цена не может быть меньше или равна 0");
        }   
        if (stockBalance < 0){
            throw new IllegalArgumentException("Остаток на складе не может быть отрицательным.");
        }  
        this.article = article;
        this.name = name;
        this.price = price;
        this.stockBalance = stockBalance;
    }

    public Item(String article, String name, String color, int price, int stockBalance) {
        this(article, name, price, stockBalance);
        this.color = color;  
    }

   
    public String getArticle() {
        return article;
    }

    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }

    public int getPrice() {
        return price;
    }

    public int getStockBalance() {
        return stockBalance;
    }
    
    // метод уменьшения остатка
    public void reduceTheStockBalance(int numberReduce) {
        if (this.stockBalance >= numberReduce)
            this.stockBalance = this.stockBalance - numberReduce;
        else
            throw new IllegalArgumentException("Error. Остаток на складе меньше кол-ва заказанных позиций товара.");
    }
    
    @Override
    public String toString() {
        return article + ";" + name + ";" + color + ";" + price + ";" + stockBalance;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setPrice(int price) {
        if(price <= 0)
            throw new IllegalArgumentException("Error. Цена товара не может быть нулевой или отрицательной.");
        this.price = price;
    }

    public void setStockBalance(int stockBalance) {
        if (stockBalance < 0)
            throw new IllegalArgumentException("Error. Остаток на складе не может быть отрицательным.");
        this.stockBalance = stockBalance;
    }
    
        
}
