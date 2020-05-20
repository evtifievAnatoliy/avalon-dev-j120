package ru.avalon.java.j120.internetShop;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import ru.avalon.java.j120.internetShop.controllers.*;
import ru.avalon.java.j120.internetShop.models.*;
import ru.avalon.java.j120.internetShop.commons.*;
import ru.avalon.java.j120.internetShop.configuration.Configuration;
import ru.avalon.java.j120.internetShop.ui.*;

import java.io.*;
import java.text.ParseException;
import java.util.Properties;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileView;







public class Main extends JFrame{

    /**
     * @param args the command line arguments
     */
        
    public static void main(String[] args) throws IOException, ClassNotFoundException, ParseException {
        // TODO code application logic here
        
       
        
        MainForm mainForm = new MainForm();
        mainForm.setVisible(true);
        
       
        
        
        //Configuration configuration = Configuration.getInstance();
        /*
        //общий объект работающий с товарами и заказами
        MainController mainController = new MainController();
        
        // методы и объекты работающие с товарами
        StockItems stockItems = mainController.getStockItems();
        stockItems.addItem(new Item("7000002001001", "Платье трикотажное", "Зеленый", 1800, 9));
        mainController.writeItems();
        //--------------------------------
        /*
        //методы и объекты работающие с заказами
        /*
        OrderManager orderManager = mainController.getOrderManager();
        
        orderManager.addOrderPosition(stockItems.getItemsAsList().get(0), 1);
        orderManager.addOrderPosition(stockItems.getItemsAsList().get(1), 2);
        orderManager.addOrderPosition(stockItems.getItemsAsList().get(2), 7);
        
        Orders orders = mainController.getOrders();
        
        orders.addOrder( 
                new Person("Анатолий", 
                        new Address("РФ", "Санкт-Петрербург", "ул. Ваиловых", "дом11/1", 1),
                        "89219910012"),
        (byte)0, StatusOfOrder.ГОТОВИТСЯ, orderManager.getOrderItems());
        mainController.writeOrder();
        */
                
        //--------------------------------
        
        
        //методы и объекты работающие с базой заказчиков
        /*
        CustomersManager customersManager = mainController.getCustomersManager();
        
        
        customersManager.addCustomer(
                new Person("Анатолий", 
                        new Address("РФ", "Санкт-Петрербург", "ул. Ваиловых", "дом11/1", 1),
                        "89219910012"));
        mainController.writeCustomers();
        */
                
        //--------------------------------
        
        System.out.println("Приложение закончило свою работу.");
    }
     

   
}
