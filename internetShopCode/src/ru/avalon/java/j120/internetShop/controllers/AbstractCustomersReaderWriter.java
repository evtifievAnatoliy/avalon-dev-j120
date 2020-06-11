/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.avalon.java.j120.internetShop.controllers;
import java.io.File;
import java.io.FileInputStream;
import ru.avalon.java.j120.internetShop.commons.*;


import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import ru.avalon.java.j120.internetShop.configuration.Configuration;




/**
 *
 * @author eag
 */
// класс который работает с записью и чтением базы клиентов
public interface AbstractCustomersReaderWriter {
    
        
// метод записи в файл. На входе путь записи и  коллекция Заказчиков
    public void writeCustomers(ArrayList<Person> customers, Person newCustomer) throws IOException;
        	
    
    // метод чтения из файла. На входе путь записи коллекции Заказчиков
    public ArrayList<Person> readCustomers() throws IOException, ClassNotFoundException;
                
}
