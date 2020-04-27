package ru.avalon.java.j120.internetShop;

import java.util.ArrayList;
import ru.avalon.java.j120.internetShop.models.*;

import java.io.*;
import java.util.Arrays;


public class main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        final String  itemsPath= "D:\\Толик учеба\\Java\\avalon-dev-j120\\internetShopCode\\items.csv";
        
        //writeItems(itemsPath);
        ArrayList<Item> array = readItems(itemsPath);
        
        
        System.out.println("Hello");
    }

    public static void writeItems(String itemsPath){
        File itemsFile = new File(itemsPath);
        try {
            if (!itemsFile.exists()) 
                itemsFile.createNewFile();
            } 
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
	        
        ArrayList<Item> items = new ArrayList<Item>();
        items.add(new Item("7000002001000", "Платье трикотажное", 1500, 10));
        items.add(new Item("7000002001001", "Платье трикотажное", "Красный", 1500, 10));
        items.add(new Item("7000002001002", "Платье трикотажное", "Синий", 1800, 10));
        items.add(new Item("7000002001003", "Платье трикотажное", "Желтый", 1800, 10));
        items.add(new Item("7000002001010", "Юбка", 1200, 10));
        items.add(new Item("7000002001011", "Юбка", "Красный",  1200, 10));
        items.add(new Item("7000002001011", "Юбка", "Синий",  1400, 10));
        items.add(new Item("7000002001011", "Юбка", "Желтый",  1400, 10));
        
        try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(itemsPath)))
        {
            // перевод строки в байты
            for (Item item : items)
            {
                bufferedWriter.write(item.toString() + "\n");
	    }
                
        }
        catch(IOException ex){
            ex.printStackTrace();
        }
        
        
    }
    
    public static ArrayList<Item> readItems(String itemsPath){
                
        ArrayList<Item> items = new ArrayList<Item>();
        try (BufferedReader br = new BufferedReader(new FileReader(itemsPath))){
            String str;
            while ((str = br.readLine()) != null) {
                
                String[] strSplit=str.split(";");
                try{
                    Item item = new Item(strSplit[0], strSplit[1], strSplit[2], Integer.parseInt(strSplit[3]), Integer.parseInt(strSplit[4]));
                    items.add(item);
                }
                catch(Exception ex){
                    break;
                }
               
            }
                
            
            
        return items;
        }
        catch(IOException ex){
            ex.printStackTrace();
            return null;
        }
    }
}
