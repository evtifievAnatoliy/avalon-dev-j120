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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import ru.avalon.java.j120.internetShop.commons.Person;
import ru.avalon.java.j120.internetShop.configuration.Configuration;
import ru.avalon.java.j120.internetShop.controllers.rwfiles.*;
import ru.avalon.java.j120.internetShop.controllers.sql.*;
import ru.avalon.java.j120.internetShop.models.Item;
import ru.avalon.java.j120.internetShop.models.Order;
import ru.avalon.java.j120.internetShop.models.OrderPosition;

/**
 *
 * @author user
 */
// Класс который инициализирует заказы и товары
// Считывает коллекции из файлов и записывает их в файл

public class MainController{
    
    private StockItems stockItems;
    private Orders orders;
    private CustomersManager customersManager;
    
        
    AbstractItemsReaderWriter itemsReaderWriter;
    AbstractOrderReaderWriter orderReaderWriter;
    AbstractCustomersReaderWriter customersReaderWriter;
    
    public MainController() throws IOException, ClassNotFoundException, ParseException, SQLException {
        
        if (Configuration.getInstance().isProperty("url.Db"))
            try(Connection connection = DriverManager.getConnection(Configuration.getInstance().getProperty("url.Db"), 
                    Configuration.getInstance().getProperty("user.Db"),
                    Configuration.getInstance().getProperty("password.Db"))){
            
                this.itemsReaderWriter = new ItemsReaderWriterSql();
                this.orderReaderWriter = new OrderReaderWriterSql();
                this.customersReaderWriter = new CustomersReaderWriterSql();
            
                stockItems = new StockItems(itemsReaderWriter.readItems());
                orders = new Orders(orderReaderWriter.readOrders());
                customersManager = new CustomersManager(customersReaderWriter.readCustomers());
            }
            catch(SQLException ex){
                throw new IllegalArgumentException("Error. Соединение с базой не установлено установлено!!!\n" + ex.getMessage());
            }
        else 
        {
            this.itemsReaderWriter = new ItemsReaderWriterFiles();
            this.orderReaderWriter = new OrderReaderWriterFiles();
            this.customersReaderWriter = new CustomersReaderWriterFiles();
            
            stockItems = new StockItems(itemsReaderWriter.readItems());
            orders = new Orders(orderReaderWriter.readOrders());
            customersManager = new CustomersManager(customersReaderWriter.readCustomers());
        }
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
    
    
    
    public void writeItems(Item newItem,  ArrayList<Item> updateItems) throws IOException{
        itemsReaderWriter.writeItems(stockItems.getItemsAsList(), newItem, updateItems);
    }
    
    public void writeOrder(Order newOrder, Order delOrder, Order updateOrder) throws IOException{
        orderReaderWriter.writeOrders(orders.getOrders(), newOrder, delOrder, updateOrder);
    }
    
    public void writeCustomers(Person newCustomer) throws IOException{
        customersReaderWriter.writeCustomers(customersManager.getCustomersAsList(), newCustomer);
    }
    
    
}
