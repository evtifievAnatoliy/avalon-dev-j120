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
    public void addOrder(Person contactPerson, byte disconte, StatusOfOrder statusOrder, ArrayList<OrderPosition> orderItems){
        
        if (this.orders == null) //проверка на нулевое значение
            this.orders = new ArrayList<Order>();
        
        if(orderItems.size() == 0)
            throw new IllegalArgumentException("Error. В заказе нет ни одного товара.");
        
        LocalDateTime localTime = LocalDateTime.now();
                
        String numberOfOrder = String.valueOf(localTime.getYear()) + String.valueOf(localTime.getMonthValue()) + String.valueOf(localTime.getDayOfMonth()
            + String.valueOf(localTime.getHour()) + String.valueOf(localTime.getMinute()) + String.valueOf(localTime.getSecond()));
        this.orders.add(new Order(numberOfOrder, localTime.now(), contactPerson, disconte, statusOrder, orderItems));
    }
    
     // метод изменения заказа
    public void editOrder(String orderNumber, LocalDateTime dateTheOrderWasGreated, Person contactPerson, byte disconte, StatusOfOrder statusOrder, ArrayList<OrderPosition> orderItems){
                
        if (this.orders == null) //проверка на нулевое значение
            throw new IllegalArgumentException("Error. Системная ошибка. Orders in metod editOrder in class Orders null.");
        
        if(orderItems.size() == 0)
            throw new IllegalArgumentException("Error. В заказе нет ни одного товара.");
        
        if (statusOrder == StatusOfOrder.ГОТОВИТСЯ){
            
            ArrayList<Order> newOrders = new ArrayList<Order>();
            for (Order order: this.orders){
                if (order.getOrderNumber().equals(orderNumber))
                    newOrders.add(new Order(orderNumber, dateTheOrderWasGreated, contactPerson, disconte, statusOrder, orderItems));
                else
                    newOrders.add(order);
            }
            this.orders = newOrders;
        }
        else
            throw new IllegalArgumentException("Error. Изменить заказ можно только со статусом " + StatusOfOrder.ГОТОВИТСЯ +
                    "\nУ изменяемого заказа статус: " + statusOrder);
                
    }

    // метод изменения статуса заказа
    public void setStatusOfOrder(String orderNumber, StatusOfOrder statusOrder, MainController mainController) throws IOException{
                
        if (this.orders == null) //проверка на нулевое значение
            throw new IllegalArgumentException("Error. Системная ошибка. Orders in metod editOrder in class Orders null.");
        
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
        
        
        if (orders == null)
            throw new IllegalArgumentException("Error. Системная ошибка. Заказов в заказе нет.");
        
        if (orders.size() == 0)
            throw new IllegalArgumentException("Error. Заказов нет в списке заказов.");
                
        if (number < 0)
            throw new IllegalArgumentException("Error. Заказ в в списке заказов не выбран.");
                
        if (orders.size() <= number)
            throw new IllegalArgumentException("Error. Системная ошибка. Размер коллекци меньше номера удаляемого заказа!!!");
                
        if (orders.get(number).getStatusOfOrder() == StatusOfOrder.ГОТОВИТСЯ)
            orders.remove(number);
         
        else 
            throw new IllegalArgumentException("Error. Удалить заказ с № " + number + " не получилось.\nУдалить заказ можно только со статусом ГОТОВИТСЯ.");
     
      
    }
    
    // метод получения заказа по порядковому номеру
    public Order getOrder(int number){
        
        
        if (orders == null)
            throw new IllegalArgumentException("Error. Системная ошибка. Заказов в заказе нет.");
        
        if (orders.size() == 0)
            throw new IllegalArgumentException("Error. Заказов нет в списке заказов.");
                
        if (number < 0)
            throw new IllegalArgumentException("Error. Заказ в в списке заказов не выбран.");
                
        if (orders.size() <= number)
            throw new IllegalArgumentException("Error. Системная ошибка. Размер коллекци меньше номера удаляемого заказа!!!");
                
        if (orders.get(number).getStatusOfOrder() == StatusOfOrder.ГОТОВИТСЯ)
            return orders.get(number);
         
        else 
            throw new IllegalArgumentException("Error. Изменить заказ с № " + number + " нельзя.\n  Изменение заказа возможно только для заказов со статусом ГОТОВИТСЯ.");
     
      
    }
    
     // метод получения заказа по артикулу
    public Order getOrder(String article){
        
        for(Order order: this.orders)
            if(order.getOrderNumber().equals(article))
                return order;
                 
        throw new IllegalArgumentException("Error. Заказ с артикулом: " + article + " не найден.");
     
    }
    
    public ArrayList<Order> getOrders() {
        return orders;
    }
    
    
}
