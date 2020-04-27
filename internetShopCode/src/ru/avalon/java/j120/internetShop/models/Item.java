/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.avalon.java.j120.internetShop.models;

/**
 *
 * @author eag
 */
public class Item {
    private int article;
    private String name;
    private String color;
    private int price;
    private int stockBalance;

    public Item(int article, String name, int price, int stockBalance) {
        this.article = article;
        this.name = name;
        this.price = price;
        this.stockBalance = stockBalance;
    }

    public Item(int article, String name, String color, int price, int stockBalance) {
        this(article, name, price, stockBalance);
        this.color = color;  
    }
    
    
}
