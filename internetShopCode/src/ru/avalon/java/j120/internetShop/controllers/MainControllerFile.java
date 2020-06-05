/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.avalon.java.j120.internetShop.controllers;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;
import ru.avalon.java.j120.internetShop.controllers.interfaces.MainController;
import ru.avalon.java.j120.internetShop.models.Item;

/**
 *
 * @author user
 */
// Класс который инициализирует заказы и товары
// Считывает коллекции из файлов и записывает их в файл

public class MainControllerFile implements MainController{
    
    private StockItems stockItems;
    private Orders orders;
    private CustomersManager customersManager;
        
    ItemsReaderWriter itemsReaderWriter = new ItemsReaderWriter();
    OrderReaderWriter orderReaderWriter = new OrderReaderWriter();
    CustomersReaderWriter customersReaderWriter = new CustomersReaderWriter();
    
    public MainControllerFile() throws IOException, ClassNotFoundException, ParseException {
        stockItems = new StockItems(itemsReaderWriter.readItems());
        orders = new Orders(orderReaderWriter.readOrders());
        customersManager = new CustomersManager(customersReaderWriter.readCustomers());
    }

    public StockItems getStockItems() {
        return stockItems;
    }
    public CustomersManager getCustomersManager() {
        return customersManager;
    }

    public Orders getOrders() {
        return orders;
    }
    
    
    
    public void writeItems() throws IOException{
        itemsReaderWriter.writeItems(stockItems.getItemsAsList());
    }
    
    public void writeOrder() throws IOException{
        orderReaderWriter.writeOrders(orders.getOrders());
    }
    
    public void writeCustomers() throws IOException{
        customersReaderWriter.writeCustomers(customersManager.getCustomersAsList());
    }
    
    
}
