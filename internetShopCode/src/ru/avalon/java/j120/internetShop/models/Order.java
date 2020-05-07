/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.avalon.java.j120.internetShop.models;

import ru.avalon.java.j120.internetShop.commons.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.io.Serializable;

/**
 *
 * @author eag
 */
// класс заказа
public class Order implements Serializable{
    
    private int orderNumber; //порядковый номер заказа
    private LocalDateTime dateTheOrderWasGreated; // текущая дата
    private Person contactPerson; // данные клиента
    private byte disconte; // скидка клиента
    private StatusOrder statusOrder; // статус заказа
    
    private ArrayList<OrderPosition> orderItems; // товары в заказе

    public Order(int orderNumber, Person contactPerson, byte disconte, StatusOrder statusOrder, ArrayList<OrderPosition> orderItems) {
        this.orderNumber = orderNumber;
        this.dateTheOrderWasGreated = LocalDateTime.now();
        this.contactPerson = contactPerson;
        this.disconte = disconte;
        this.statusOrder = statusOrder;
        this.orderItems = orderItems;
    }

    public int getOrderNumber() {
        return orderNumber;
    }
    
}
