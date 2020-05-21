/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.avalon.java.j120.internetShop.controllers;

import java.text.ParseException;
import ru.avalon.java.j120.internetShop.models.*;
import ru.avalon.java.j120.internetShop.commons.Person;

import java.util.ArrayList;


/**
 *
 * @author eag
 */
// класс который работает с созданием и редактированием заказов 
public class OrderManager {
    
    private ArrayList<OrderPosition> orderItems = new ArrayList<OrderPosition>();

    
    // метод добавление нового товара в коллекцию товаров текущего заказа
    public void addOrderPosition(Item item, int numberOfItems, byte disconte) throws ParseException{
        
        if (orderItems != null) 
            for (OrderPosition o: orderItems)
            {
                if(o.getItem().getArticle().equals(item.getArticle())) //проверка на наличие добавляемого товара в товарах заказа
                    {
                        throw new IllegalArgumentException("Error. Товар с артикулом: " + item.getArticle() + " в заказе уже есть.");
                    }
                
            }
        if(item.getStockBalance() < numberOfItems) //проверка на наличие товара на складе
            {
                throw new IllegalArgumentException("Error. Товар с артикулом: " + item.getArticle() + " в нужном кол-ве на складе нет.");
            }
           
        OrderPosition orderPosition = new OrderPosition(item, numberOfItems, disconte);
        orderItems.add(orderPosition);
    
    }
    // метод удаления товара из коллекции товаров текущего заказа
    public void removeOrderPosition(int number){
        
        if (orderItems == null)
            throw new IllegalArgumentException("Error. Системная ошибка. Товаров в заказе нет.");
        
        if (orderItems.size() == 0)
            throw new IllegalArgumentException("Error. Товаров в заказе нет.");
                
        if (number < 0)
            throw new IllegalArgumentException("Error. Товар в заказе не выбран.");
                
        if (orderItems.size() <= number)
            throw new IllegalArgumentException("Error. Размер коллекци меньше номера удаляемого товара!!!");
                
        orderItems.remove(number);
        
    }

    public ArrayList<OrderPosition> getOrderItems() {
        return orderItems;
    }
     
    // метод изменения стоимости товаров в коллекции товаров после измения скидки заказа
    public void changeAmountOfItems(byte disconte) throws ParseException{
        
        for(OrderPosition position : orderItems)
            position.setDisconte(disconte);
        
                
    }
    
    
}
