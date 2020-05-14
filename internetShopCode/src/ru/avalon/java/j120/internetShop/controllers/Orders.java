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
        
        this.orders.add(new Order(this.orders.size(), contactPerson, disconte, statusOrder, orderItems));
    }

    public ArrayList<Order> getOrders() {
        return orders;
    }
    
    
}
