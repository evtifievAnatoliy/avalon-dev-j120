/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.avalon.java.j120.internetShop.controllers;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import ru.avalon.java.j120.internetShop.models.*;
import ru.avalon.java.j120.internetShop.commons.Person;

import java.util.ArrayList;
import ru.avalon.java.j120.internetShop.controllers.interfaces.MainController;
/**
 *
 * @author user
 */
// Класс который редактирует и добавляет товары
public class Orders {
    
    private ArrayList<Order> orders;
    

    public Orders(ArrayList<Order> orders) {
        this.orders = orders;
    }
    
     // метод добавление нового заказа
    public void addOrder(Person contactPerson, byte disconte, StatusOfOrder statusOrder, OrderManager orderManager){
        
        if(orderManager.getOrderItems().size() == 0)
            throw new IllegalArgumentException("Error. В заказе нет ни одного товара.");
        
        LocalDateTime localTime = LocalDateTime.now();
                
        String numberOfOrder = String.valueOf(localTime.getYear()) + String.valueOf(localTime.getMonthValue()) + String.valueOf(localTime.getDayOfMonth()
            + String.valueOf(localTime.getHour()) + String.valueOf(localTime.getMinute()) + String.valueOf(localTime.getSecond()));
        this.orders.add(new Order(numberOfOrder, localTime.now(), contactPerson, disconte, statusOrder, orderManager));
    }
    
     // метод изменения заказа
    public void editOrder(String orderNumber, Person contactPerson, byte disconte,  ArrayList<OrderPosition> orderItems){
                
        if(orderItems.size() == 0)
            throw new IllegalArgumentException("Error. В заказе нет ни одного товара.");
        
            //ArrayList<Order> newOrders = new ArrayList<Order>();
            for (Order order: this.orders){
                if (order.getOrderNumber().equals(orderNumber)){
                    order.setContactPerson(contactPerson);
                    order.setDisconte(disconte);
                    order.setOrderItems(orderItems);
                    order.setStatusOfOrder(StatusOfOrder.ГОТОВИТСЯ);
                }
            }
     
              
    }

    // метод изменения статуса заказа
    public void setStatusOfOrder(String orderNumber, StatusOfOrder statusOrder, MainController mainController) throws IOException{
                
        for (Order order: this.orders){
            if (order.getOrderNumber().equals(orderNumber))
            {
                if (order.getStatusOfOrder() == StatusOfOrder.ГОТОВИТСЯ){
                    if(statusOrder == StatusOfOrder.ОТГРУЖЕН){
                        mainController.getStockItems().reduceTheStockBalance(order.getOrderItems());
                        mainController.writeItems();
                    }
                    order.setStatusOfOrder(statusOrder);
                }
                else
                    throw new IllegalArgumentException("Error. Изменить заказ можно только со статусом " + StatusOfOrder.ГОТОВИТСЯ +
                    "\nУ изменяемого заказа статус: " + order.getStatusOfOrder());
            }
        }
        
                
    }
    
    // метод удаления заказа
    public void removeOrder(int number){
                
              
        if (orders.get(number).getStatusOfOrder() == StatusOfOrder.ГОТОВИТСЯ)
            orders.remove(number);
         
        else 
            throw new IllegalArgumentException("Error. Удалить заказ с № " + orders.get(number).getOrderNumber() + " не получилось.\nУдалить заказ можно только со статусом ГОТОВИТСЯ.");
     
      
    }
    
    // метод получения заказа по порядковому номеру
    public Order getOrder(int number){
        
        return orders.get(number);
        
    }
    
        
    public ArrayList<Order> getOrders() {
        return orders;
    }
    
    
}
