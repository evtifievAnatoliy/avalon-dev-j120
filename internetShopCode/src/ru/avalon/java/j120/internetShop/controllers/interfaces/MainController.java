/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.avalon.java.j120.internetShop.controllers.interfaces;

import java.io.IOException;
import java.text.ParseException;
import ru.avalon.java.j120.internetShop.controllers.CustomersManager;
import ru.avalon.java.j120.internetShop.controllers.CustomersReaderWriter;
import ru.avalon.java.j120.internetShop.controllers.ItemsReaderWriter;
import ru.avalon.java.j120.internetShop.controllers.OrderReaderWriter;
import ru.avalon.java.j120.internetShop.controllers.Orders;
import ru.avalon.java.j120.internetShop.controllers.StockItems;

/**
 *
 * @author eag
 */
public interface MainController {
    

    public StockItems getStockItems();
    
    public CustomersManager getCustomersManager();

    public Orders getOrders();  
    
    public void writeItems() throws IOException;
    
    public void writeOrder() throws IOException;
    
    public void writeCustomers() throws IOException;
    
}
