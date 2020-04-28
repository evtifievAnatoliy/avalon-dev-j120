/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.avalon.java.j120.internetShop.models;
import ru.avalon.java.j120.internetShop.commons.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @author eag
 */
// класс заказа
public class Order {
    
    private int orderNumber; //порядковый номер заказа
    private LocalDateTime dateTheOrderWasGreated; // текущая дата
    private Person contactPerson; // данные клиента
    private Address adressToDelivery; // адрес доставки
    private String contactPersonTelephone; // номер телефона
    private byte disconte; // скидка клиента
    private StatusOrder statusOrder; // статус заказа
    
    private List<OrderPosition> orderItems; // товары в заказе
    
}
