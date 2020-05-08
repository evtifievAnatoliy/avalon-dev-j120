package ru.avalon.java.j120.internetShop;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import ru.avalon.java.j120.internetShop.controllers.*;
import ru.avalon.java.j120.internetShop.models.*;
import ru.avalon.java.j120.internetShop.commons.*;
import ru.avalon.java.j120.internetShop.configuration.Configuration;
import ru.avalon.java.j120.internetShop.ui.MainForm;

import java.io.*;
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
        
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        // TODO code application logic here
        
        MainForm mainForm = new MainForm();
        mainForm.setVisible(true);
        
        /*
        JFrame mainForm = new JFrame("internetShop");
        mainForm.setBounds(100, 100, 500, 500);
        mainForm.setVisible(true);
        mainForm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        Container container = mainForm.getContentPane();
        container.setLayout(new BoxLayout( container, BoxLayout.X_AXIS));
        JPanel p = new JPanel();
        p.setLayout(new BoxLayout( container, BoxLayout.Y_AXIS));
        p.add(new JLabel("Label 1"));
        p.add(new JLabel("Label 2"));        
        p.add(new JLabel("Label 3"));
        
        container.add(p);
        
        p = new JPanel();
        p.setLayout(new BoxLayout( container, BoxLayout.Y_AXIS));
        p.add(new JLabel("Label 5"));
        p.add(new JLabel("Label 6"));        
        p.add(new JLabel("Label 7"));
        p.setBorder((BorderFactory.createEtchedBorder()));
        
        container.add(p);
        */
        /*
        JLabel jLabel = new JLabel("internetShop", SwingConstants.LEFT);
        mainForm.add(jLabel);
        */
        mainForm.setVisible(true);
        
        /*
        Configuration configuration = Configuration.getInstance();
        final String  itemsPath = configuration.getProperties().getProperty("items.Path");
        final String  ordersPath = configuration.getProperties().getProperty("orders.Path");
        final String  customersPath= configuration.getProperties().getProperty("customers.Path");
        
        
        
        //общий объект работающий с товарами и заказами
        MainController mainController = new MainController(itemsPath, ordersPath, customersPath);
        
        // методы и объекты работающие с товарами
        StockItems stockItems = mainController.getStockItems();
        //stockItems.addItem(new Item("7000002001007", "Платье трикотажное", "Зеленый", 1800, 9));
        //mainController.writeItems();
        //--------------------------------
       
        //методы и объекты работающие с заказами
        
        OrderManager orderManager = mainController.getOrderManager();
        
        orderManager.addOrderPosition(stockItems.getItemsAsList().get(0), 1);
        orderManager.addOrderPosition(stockItems.getItemsAsList().get(1), 2);
        orderManager.addOrderPosition(stockItems.getItemsAsList().get(2), 10);
        orderManager.addOrder( 
                new Person("Анатолий", 
                        new Address("РФ", "Санкт-Петрербург", "ул. Ваиловых", "дом11/1", 1),
                        "89219910012"),
        (byte)0, StatusOrder.ГОТОВИТСЯ);
        //mainController.writeOrder();
        
                
        //--------------------------------
        
        
        //методы и объекты работающие с базой заказчиков
        
        CustomersManager customersManager = mainController.getCustomersManager();
        
        
        customersManager.addCustomer(
                new Person("Анатолий", 
                        new Address("РФ", "Санкт-Петрербург", "ул. Ваиловых", "дом11/1", 1),
                        "89219910010"));
        //mainController.writeCustomers();
        
                
        //--------------------------------
        
        System.out.println("Приложение закончило свою работу.");*/
    }
     

   
}
