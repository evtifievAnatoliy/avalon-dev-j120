package ru.avalon.java.j120.internetShop;
import ru.avalon.java.j120.internetShop.controllers.MainController;
import ru.avalon.java.j120.internetShop.models.*;
import ru.avalon.java.j120.internetShop.controllers.StockItems;
import ru.avalon.java.j120.internetShop.controllers.OrderManager;
import ru.avalon.java.j120.internetShop.commons.*;

import java.io.*;
import java.util.Scanner;



public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        // TODO code application logic here
        final String  itemsPath= "D:\\Java\\avalon-dev-j120\\internetShopCode\\items.csv";
        final String  ordersPath= "D:\\Java\\avalon-dev-j120\\internetShopCode\\orders.dat";
        
        //общий объект работающий с товарами и заказами
        MainController mainController = new MainController(itemsPath, ordersPath);
        
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
        mainController.writeOrder();
        
                
        //--------------------------------
        
        System.out.println("Приложение закончило свою работу.");
    }

   
}
