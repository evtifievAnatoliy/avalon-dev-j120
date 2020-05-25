/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.avalon.java.j120.internetShop.controllers;

import java.io.IOException;
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
    public void addOrderPosition(Item item, int numberOfItems, byte disconte, Order editOrder) throws IOException{
        
        checkEditOrder(editOrder);          // проверка на новый заказ и на статус редактируемого заказа 
        
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
    public void removeOrderPosition(int number, Order editOrder){
        
        checkEditOrder(editOrder);          // проверка на новый заказ и на статус редактируемого заказа 
        orderItems.remove(number);
        
    }

    public ArrayList<OrderPosition> getOrderItems() {
        return orderItems;
    }
     
    // метод изменения стоимости товаров в коллекции товаров после измения скидки заказа
    public void changeAmountOfItems(byte disconte) throws IOException, ParseException{
        
        for(OrderPosition position : orderItems)
            position.setDisconte(disconte);
        
                
    }

    public void setOrderItems(ArrayList<OrderPosition> orderItems) {
        this.orderItems = orderItems;
    }
    
    public void checkEditOrder (Order editOrder){
        if(editOrder == null)  
            return;
        else {
            if (editOrder.getStatusOfOrder() == StatusOfOrder.ГОТОВИТСЯ){
                return;
            }
            else
                throw new IllegalArgumentException("Error. Изменить заказ с № " + editOrder.getOrderNumber() + " нельзя.\nИзменение заказа возможно только для заказов со статусом ГОТОВИТСЯ.");
        }
    }
}
