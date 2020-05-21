/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.avalon.java.j120.internetShop.controllers;
import ru.avalon.java.j120.internetShop.commons.*;

import java.util.ArrayList;


/**
 *
 * @author eag
 */
// Класс который работает с базой клиентов 

public class CustomersManager {
    
    private ArrayList<Person> customers;
    
    public CustomersManager( ArrayList<Person> customers) {
        this.customers = customers;
    }
    
    // метод добавление нового клиента в базу клиентов
    public void addCustomer(Person customer){
        if(this.customers != null)
        {
            for (Person p: customers) // проверка наличия такого клиента по номеру телефона 
                if(p.getPhoneNumber().equals(customer.getPhoneNumber()))
                    return;
                    //throw new IllegalArgumentException("Error. Не удалось добавить заказчика в базу клиентов. Заказчик с телефоном: " + customer.getPhoneNumber() + " уже есть в базе. ");
        }
        if (this.customers == null) //проверка на нулевое значение
            this.customers = new ArrayList<Person>();
        
        this.customers.add(customer);
    }

    public ArrayList<Person> getCustomersAsList() {
        return customers;
    }
    
}
