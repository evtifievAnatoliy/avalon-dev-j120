/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.avalon.java.j120.internetShop.controllers;
import ru.avalon.java.j120.internetShop.models.*;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.FileReader;
import java.io.IOException;


import java.util.ArrayList;


/**
 *
 * @author user
 */
// класс который работает с записью и чтением каталогов заказов из файла

public class OrderReaderWriter {
    
    // метод записи в файл. На входе путь записи и  коллекция Товаров
    public void writeOrders(String ordersPath, ArrayList<Order> orders) throws IOException{
        	
        if (orders !=null) // проверяем на наличие элементов в коллекции
        { 
            // пробуем записать в файл коллекцию заказов (сериализовать)
            try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ordersPath))){
                //for (Item item : items)
                //{
                    oos.writeObject(orders);
                //}       
            }
        }
        else
            throw new IllegalArgumentException("Error. Заказы в файл не записаны, т.к. список заказов пуст. ");
    }
    
    // метод чтения из файла. На входе путь записи и  коллекция Товаров
    public ArrayList<Order> readOrders(String ordersPath) throws IOException, ClassNotFoundException{
                
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ordersPath))){
        // пробуем десериализовать коллекцию заказов
        ArrayList<Order> orders = (ArrayList<Order>) ois.readObject();
                        
        return orders;
            
        }
            
        catch (IOException e){
            return null;
        }
    }
}
