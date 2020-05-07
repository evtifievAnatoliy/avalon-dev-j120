/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.avalon.java.j120.internetShop.commons;

import java.io.Serializable;

/**
 *
 * @author user
 */
// класс клиента
public class Person implements Serializable{
    private String name;
    private Address adressToDelivery; // адрес доставки
    private String phoneNumber; // номер телефона

    public Person(String name, Address adressToDelivery, String phoneNumber) {
        this.name = name;
        this.adressToDelivery = adressToDelivery;
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public Address getAdressToDelivery() {
        return adressToDelivery;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
    
    
}
