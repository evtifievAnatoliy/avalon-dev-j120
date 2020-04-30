package ru.avalon.java.j120.internetShop;
import ru.avalon.java.j120.internetShop.controllers.MainController;
import ru.avalon.java.j120.internetShop.models.*;


import java.io.*;
import java.util.Scanner;


public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        final String  itemsPath= "D:\\Толик учеба\\Java\\avalon-dev-j120\\internetShopCode\\items.csv";
        
        MainController mainController = new MainController(itemsPath);
        mainController.addItem(new Item("7000002001005", "Платье трикотажное", "Серый", 1900, 9));
        mainController.writeItems();
        
        System.out.println("Hello");
    }

   
}
