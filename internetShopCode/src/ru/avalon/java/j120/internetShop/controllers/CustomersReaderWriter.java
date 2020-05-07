/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.avalon.java.j120.internetShop.controllers;
import java.io.FileInputStream;
import ru.avalon.java.j120.internetShop.commons.*;


import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;



/**
 *
 * @author eag
 */
// класс который работает с записью и чтением базы клиентов
public class CustomersReaderWriter {
    
    // метод записи в файл. На входе путь записи и  коллекция Заказчиков
    public void writeCustomers(String customersPath, ArrayList<Person> customers) throws IOException{
        	
        if (customers !=null) // проверяем на наличие элементов в коллекции
        { 
            // пробуем записать в файл коллекцию Заказчиков (сериализовать)
            try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(customersPath))){
                //for (Item item : items)
                //{
                    oos.writeObject(customers);
                //}       
            }
        }
        else
            throw new IllegalArgumentException("Error. Заказчики в файл не записаны, т.к. список заказчиков пуст. ");
    }
    
    // метод чтения из файла. На входе путь записи коллекции Заказчиков
    public ArrayList<Person> readCustomers(String customersPath) throws IOException, ClassNotFoundException{
                
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(customersPath))){
        // пробуем десериализовать коллекцию Заказчиков
        ArrayList<Person> customers = (ArrayList<Person>) ois.readObject();
                        
        return customers;
            
        }
            
        catch (IOException e){
            return null;
        }
    }
    
}
