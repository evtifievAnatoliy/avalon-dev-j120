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
import java.time.LocalDate;
import ru.avalon.java.j120.internetShop.controllers.OrderManager;

/**
 *
 * @author eag
 */
// класс заказа
public class Order implements Serializable{
    
    private final String ORDER_NUMBER; //порядковый номер заказа
    private final LocalDate DATE_THE_WAS_GREATED; // текущая дата
    private Person contactPerson; // данные клиента
    private byte disconte; // скидка клиента
    private StatusOfOrder statusOfOrder; // статус заказа
    
    private OrderManager orderManager;
    //private ArrayList<OrderPosition> orderItems; // товары в заказе

    public Order(String orderNumber, LocalDate localDate, Person contactPerson, byte disconte, StatusOfOrder statusOfOrder, OrderManager orderManager) {
        this.ORDER_NUMBER = orderNumber;
        this.DATE_THE_WAS_GREATED = localDate;
        this.contactPerson = contactPerson;
        this.disconte = disconte;
        this.statusOfOrder = statusOfOrder;
        this.orderManager = orderManager;
        //this.orderItems = orderManager.getOrderItems();
        
    }

    public OrderManager getOrderManager() {
        return orderManager;
    }
    
    public String getOrderNumber() {
        return ORDER_NUMBER;
    }

    public LocalDate getDateTheOrderWasGreated() {
        return DATE_THE_WAS_GREATED;
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
        return orderManager.getOrderItems();
    }

    public void setContactPerson(Person contactPerson) {
        if (this.statusOfOrder == StatusOfOrder.ГОТОВИТСЯ){
            this.contactPerson = contactPerson;
        }
        else
            throw new IllegalArgumentException("Error. Изменить заказ можно только со статусом " + StatusOfOrder.ГОТОВИТСЯ +
                    "\nУ изменяемого заказа статус: " + statusOfOrder);
    }

    public void setDisconte(byte disconte) {
        if (this.statusOfOrder == StatusOfOrder.ГОТОВИТСЯ){
            this.disconte = disconte;
        }
        else
            throw new IllegalArgumentException("Error. Изменить заказ можно только со статусом " + StatusOfOrder.ГОТОВИТСЯ +
                    "\nУ изменяемого заказа статус: " + statusOfOrder);
    }

    public void setOrderItems(ArrayList<OrderPosition> orderItems) {
        if (this.statusOfOrder == StatusOfOrder.ГОТОВИТСЯ){
            this.orderManager.setOrderItems(orderItems);
        }
        else
            throw new IllegalArgumentException("Error. Изменить заказ можно только со статусом " + StatusOfOrder.ГОТОВИТСЯ +
                    "\nУ изменяемого заказа статус: " + statusOfOrder);
    }

    public void setStatusOfOrder(StatusOfOrder statusOfOrder) {
        if (this.statusOfOrder == StatusOfOrder.ГОТОВИТСЯ){
            this.statusOfOrder = statusOfOrder;
        }
        else
            throw new IllegalArgumentException("Error. Изменить заказ можно только со статусом " + StatusOfOrder.ГОТОВИТСЯ +
                    "\nУ изменяемого заказа статус: " + statusOfOrder);
    }
    
    
}
