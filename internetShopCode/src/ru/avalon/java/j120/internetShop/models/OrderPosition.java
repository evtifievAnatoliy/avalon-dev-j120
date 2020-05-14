/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.avalon.java.j120.internetShop.models;
import java.io.Serializable;
import ru.avalon.java.j120.internetShop.commons.*;

/**
 *
 * @author eag
 */
public class OrderPosition implements Serializable{
    private final Item item; // товар
    private final int numberOfItems; // кол-во товара в заказе
    private final int amountOfItems; // сумма (товар на кол-во)

    public OrderPosition(Item item, int numberOfItems) {
        this.item = item;
        this.numberOfItems = numberOfItems;
        this.amountOfItems = this.item.getPrice() * this.numberOfItems;
    
    }

    public Item getItem() {
        return item;
    }

    public int getNumberOfItems() {
        return numberOfItems;
    }

    public int getAmountOfItems() {
        return amountOfItems;
    }
    
    
    
    
    
}
