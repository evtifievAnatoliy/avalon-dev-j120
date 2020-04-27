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
public class Order {
    
    private LocalDateTime dateTheOrderWasGreated;
    private String contactPerson;
    private Address adressToDelivery;
    private String contactPersonTelephone;
    private byte disconte;
    private StatusOrder statusOrder;
    
    private List<ListOfOrderItems> orderItems;
    
}
