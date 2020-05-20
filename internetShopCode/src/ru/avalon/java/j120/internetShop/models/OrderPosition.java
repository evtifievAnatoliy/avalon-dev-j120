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
    private int numberOfItems; // кол-во товара в заказе
    private int amountOfItems; // сумма (товар на кол-во)

    public OrderPosition(Item item, int numberOfItems) {
        if (item.getStockBalance() <=0 )
            throw new IllegalArgumentException("Товара на складе нет.");
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

    public void setNumberOfItems(int numberOfItems) {
        if (item.getStockBalance() < numberOfItems)
            throw new IllegalArgumentException("Товара в нужном кол-ве на складе нет. \nОстаток на складе: " 
                    + item.getStockBalance() + ".");
        this.numberOfItems = numberOfItems;
        this.amountOfItems = this.item.getPrice() * this.numberOfItems;
    }
    
    
    
    
    
    
    
}
