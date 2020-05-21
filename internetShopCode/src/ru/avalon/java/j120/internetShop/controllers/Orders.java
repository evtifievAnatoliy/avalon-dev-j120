/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.avalon.java.j120.internetShop.controllers;

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
        this.orders.add(new Order(numberOfOrder, contactPerson, disconte, statusOrder, orderItems));
    }

    public ArrayList<Order> getOrders() {
        return orders;
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
            throw new IllegalArgumentException("Error. Удалить заказ с № " + number + " не получилось.\n Удалить заказ можно только со статусом ГОТОВИТСЯ.");
     
      
    }
    
}
