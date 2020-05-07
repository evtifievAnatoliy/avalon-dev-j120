/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.avalon.java.j120.internetShop.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import ru.avalon.java.j120.internetShop.models.Item;

/**
 *
 * @author user
 */
// Класс который инициализирует заказы и товары
// Считывает коллекции из файлов и записывает их в файл

public class MainController {
    
    private String itemsPath;
    private String ordersPath;
    private String customersPath;
    private StockItems stockItems;
    private OrderManager orderManager;
    private CustomersManager customersManager;
    

    ItemsReaderWriter itemsReaderWriter = new ItemsReaderWriter();
    OrderReaderWriter orderReaderWriter = new OrderReaderWriter();
    CustomersReaderWriter customersReaderWriter = new CustomersReaderWriter();
    
    public MainController(String itemsPath, String ordersPath, String customersPath) throws IOException, ClassNotFoundException {
        this.itemsPath = itemsPath;
        this.ordersPath = ordersPath;
        this.customersPath = customersPath;
        stockItems = new StockItems(itemsReaderWriter.readItems(itemsPath));
        orderManager = new OrderManager(orderReaderWriter.readOrders(ordersPath));
        customersManager = new CustomersManager(customersReaderWriter.readCustomers(customersPath));
    }

    public StockItems getStockItems() {
        return stockItems;
    }

    public OrderManager getOrderManager() {
        return orderManager;
    }

    public CustomersManager getCustomersManager() {
        return customersManager;
    }
    
    
    
    
    public void writeItems() throws IOException{
        itemsReaderWriter.writeItems(itemsPath, stockItems.getItemsAsList());
    }
    
    public void writeOrder() throws IOException{
        orderReaderWriter.writeOrders(ordersPath, orderManager.getOrders());
    }
    
    public void writeCustomers() throws IOException{
        customersReaderWriter.writeCustomers(customersPath, customersManager.getCustomersAsList());
    }
    
    
}
