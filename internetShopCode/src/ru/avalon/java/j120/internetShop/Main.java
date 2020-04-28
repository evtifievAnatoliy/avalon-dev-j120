package ru.avalon.java.j120.internetShop;


import ru.avalon.java.j120.internetShop.models.*;
import ru.avalon.java.j120.internetShop.controllers.*;

import java.util.ArrayList;
import java.io.*;
import java.util.Arrays;
import java.util.Scanner;


public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        final String  itemsPath= "D:\\Толик учеба\\Java\\avalon-dev-j120\\internetShopCode\\items.csv";
        final String  orderPath= "D:\\Толик учеба\\Java\\avalon-dev-j120\\internetShopCode\\order.dat";
        
        MainController mainController = new MainController(itemsPath);
        mainController.addItem();
        mainController.writeItems();
                
        System.out.println("Hello");
        
    }

    
}
