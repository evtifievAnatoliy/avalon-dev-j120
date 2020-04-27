/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.avalon.java.j120.internetShop.models;
import ru.avalon.java.j120.internetShop.commons.*;

/**
 *
 * @author eag
 */
public class ListOfOrderItems{
    private final Item item;
    private final int numberOfItems;
    private final int amountOfItems;

    public ListOfOrderItems(Item item, int numberOfItems) {
        this.item = item;
        this.numberOfItems = numberOfItems;
        this.amountOfItems = this.item.getPrice() * this.numberOfItems;
    
    }
    
}
