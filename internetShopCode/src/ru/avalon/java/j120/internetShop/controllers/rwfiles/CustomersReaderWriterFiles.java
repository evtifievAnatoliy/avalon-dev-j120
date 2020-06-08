/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.avalon.java.j120.internetShop.controllers.rwfiles;
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
import ru.avalon.java.j120.internetShop.controllers.AbstractCustomersReaderWriter;




/**
 *
 * @author eag
 */
// класс который работает с записью и чтением базы клиентов
public class CustomersReaderWriterFiles implements AbstractCustomersReaderWriter{
    
        
// метод записи в файл. На входе путь записи и  коллекция Заказчиков
    public void writeCustomers(ArrayList<Person> customers) throws IOException{
        	
        if (customers !=null) // проверяем на наличие элементов в коллекции
        { 
            
            // пробуем записать в файл коллекцию Заказчиков (сериализовать)
            try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(Configuration.getInstance().getProperty("customers.Path")))){
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
    public ArrayList<Person> readCustomers() throws IOException, ClassNotFoundException{
                
        File file = new File(Configuration.getInstance().getProperty("customers.Path"));
        if(!file.exists()){
            ArrayList<Person> customers = new ArrayList<Person>();
            return customers;
        }
        
        
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(Configuration.getInstance().getProperty("customers.Path")))){
            // пробуем десериализовать коллекцию Заказчиков
            ArrayList<Person> customers = (ArrayList<Person>) ois.readObject();
                        
            return customers;
            
        }
            
        catch (IOException e){
            throw new IllegalArgumentException("Error. Ошибка чтения файла заказчиков. ");
        }
    }
    
}
