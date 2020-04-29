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
        final String  itemsPath= "D:\\Java\\avalon-dev-j120\\internetShopCode\\items.csv";
        
        MainController mainController = new MainController(itemsPath);
        /*Scanner in = new Scanner(System.in, "Cp866");
        System.out.println("KEY");
        in.next();
        in.close();*/
        //mainController.addItem();
        System.out.println("KEY");
        mainController.writeItems();
        
        System.out.println("Hello");
    }

   
}
