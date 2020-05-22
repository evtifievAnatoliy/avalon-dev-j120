/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.avalon.java.j120.internetShop.models;
import java.io.Serializable;
import java.text.NumberFormat;
import java.text.ParseException;
import ru.avalon.java.j120.internetShop.commons.*;
import ru.avalon.java.j120.internetShop.configuration.Configuration;

/**
 *
 * @author eag
 */
public class OrderPosition implements Serializable{
    private final Item item; // товар
    private int numberOfItems; // кол-во товара в заказе
    private int amountOfItems; // сумма (товар на кол-во)
    private byte disconte;
    

    public OrderPosition(Item item, int numberOfItems, byte disconte) throws ParseException{
        
        this.item = item;
        this.numberOfItems = numberOfItems;
        
        checkDisconte(disconte);
        
        this.disconte = disconte;
        this.amountOfItems = this.item.getPrice() * this.numberOfItems - this.item.getPrice() * this.numberOfItems * this.disconte / 100;
    
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
        
        else if (numberOfItems < 1)
            throw new IllegalArgumentException("Кол-во товара не может быть меньше 1.");
        
        this.numberOfItems = numberOfItems;
        this.amountOfItems = this.item.getPrice() * this.numberOfItems - this.item.getPrice() * this.numberOfItems * this.disconte / 100;
    }

    public void setDisconte(byte disconte) throws ParseException {
        checkDisconte(disconte);
        this.disconte = disconte;
        this.amountOfItems = this.item.getPrice() * this.numberOfItems - this.item.getPrice() * this.numberOfItems * this.disconte / 100;
    }
    
    private void checkDisconte(byte disconte) throws ParseException{
        NumberFormat numberFormatDisconte =  NumberFormat.getIntegerInstance();
        if (disconte > numberFormatDisconte.parse(Configuration.getInstance().getProperty("max.Discount")).byteValue())
            throw new IllegalArgumentException("Скидка для клиента превышает максимальную. \nМаксимальная скидка: " 
                    + Configuration.getInstance().getProperty("max.Discount") + ".");
        
        if (disconte < 0)
            throw new IllegalArgumentException("Скидка не может быть отрицательной." );
    }
    
    
    
    
    
}
