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
    
    private String orderNumber; //порядковый номер заказа
    private LocalDateTime dateTheOrderWasGreated; // текущая дата
    private Person contactPerson; // данные клиента
    private byte disconte; // скидка клиента
    private StatusOfOrder statusOfOrder; // статус заказа
    
    private ArrayList<OrderPosition> orderItems; // товары в заказе

    public Order(String orderNumber, Person contactPerson, byte disconte, StatusOfOrder statusOfOrder, ArrayList<OrderPosition> orderItems) {
        this.orderNumber = orderNumber;
        this.dateTheOrderWasGreated = LocalDateTime.now();
        this.contactPerson = contactPerson;
        this.disconte = disconte;
        this.statusOfOrder = statusOfOrder;
        this.orderItems = orderItems;
    }

    public Order() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public LocalDateTime getDateTheOrderWasGreated() {
        return dateTheOrderWasGreated;
    }

    public Person getContactPerson() {
        return contactPerson;
    }

    public Byte getDisconte() {
        return disconte;
    }

    public StatusOfOrder getStatusOfOrder() {
        return statusOfOrder;
    }

    public ArrayList<OrderPosition> getOrderItems() {
        return orderItems;
    }

    public void setStatusOfOrder(StatusOfOrder statusOfOrder) {
        this.statusOfOrder = statusOfOrder;
    }
    
    
}
