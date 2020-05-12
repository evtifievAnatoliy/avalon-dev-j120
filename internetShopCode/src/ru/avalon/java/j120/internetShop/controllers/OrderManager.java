/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.avalon.java.j120.internetShop.controllers;

import ru.avalon.java.j120.internetShop.models.*;
import ru.avalon.java.j120.internetShop.commons.Person;

import java.util.ArrayList;


/**
 *
 * @author eag
 */
// класс который работает с созданием и редактированием заказов 
public class OrderManager {
    
    private Order order;
    private ArrayList<OrderPosition> orderItems = new ArrayList<OrderPosition>();

    
    public OrderManager(Order order) {
        this.order = order;
    }
          
    
    // метод добавление нового товара в коллекцию товаров текущего заказа
    public void addOrderPosition(Item item, int numberOfItems){
        
        if (orderItems != null) 
            for (OrderPosition o: orderItems)
            {
                if(o.getItem().getArticle().equals(item.getArticle())) //проверка на наличие добавляемого товара в товарах заказа
                    {
                        throw new IllegalArgumentException("Error. Товар с артикулом: " + item.getArticle() + " в заказе уже есть.");
                    }
                if(item.getStockBalance() < numberOfItems) //проверка на наличие товара на складе
                    {
                        throw new IllegalArgumentException("Error. Товар с артикулом: " + item.getArticle() + " в нужном кол-ве на складе нет.");
                    }
            }
               
        OrderPosition orderPosition = new OrderPosition(item, numberOfItems);
        orderItems.add(orderPosition);
    
    }
    
// метод добавление нового заказа
    public void addOrder(Person contactPerson, byte disconte, StatusOfOrder statusOrder){
        
        if (this.order == null) //проверка на нулевое значение
            this.order = new Order();
        
        this.orders.add(new Order(this.orders.size(), contactPerson, disconte, statusOrder, this.orderItems));
    }
    
}
