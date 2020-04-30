package ru.avalon.java.j120.internetShop;
import ru.avalon.java.j120.internetShop.controllers.MainController;
import ru.avalon.java.j120.internetShop.models.*;
import ru.avalon.java.j120.internetShop.controllers.StockItems;


import java.io.*;
import java.util.Scanner;


public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        final String  itemsPath= "D:\\Толик учеба\\Java\\avalon-dev-j120\\internetShopCode\\items.csv";
        
        MainController mainController = new MainController(itemsPath);
        StockItems stockItems = mainController.getStockItems();
        stockItems.addItem(new Item("7000002001000", "Платье трикотажное", "Зеленый", 1900, 9));
        mainController.writeItems();
        
        System.out.println("Hello");
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(itemsPath));
        bufferedWriter.write("Проверка" + "\n");
    }

   
}
