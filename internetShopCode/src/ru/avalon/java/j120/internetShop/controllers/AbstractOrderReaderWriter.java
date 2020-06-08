/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.avalon.java.j120.internetShop.controllers;
import java.io.File;
import ru.avalon.java.j120.internetShop.models.*;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;


import java.util.ArrayList;
import ru.avalon.java.j120.internetShop.configuration.Configuration;


/**
 *
 * @author user
 */
// класс который работает с записью и чтением каталогов заказов из файла

public interface AbstractOrderReaderWriter {
    
    
    // метод записи в файл. На входе путь записи и  коллекция Товаров
    public void writeOrders(ArrayList<Order> orders) throws IOException;
        	
    
    // метод чтения из файла. На входе путь записи и  коллекция Товаров
    public ArrayList<Order> readOrders() throws IOException, ClassNotFoundException;
                
}
