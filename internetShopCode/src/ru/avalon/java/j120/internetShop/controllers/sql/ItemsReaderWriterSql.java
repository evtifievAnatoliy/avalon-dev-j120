/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.avalon.java.j120.internetShop.controllers.sql;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import ru.avalon.java.j120.internetShop.configuration.Configuration;
import ru.avalon.java.j120.internetShop.controllers.AbstractItemsReaderWriter;
import ru.avalon.java.j120.internetShop.models.Item;
import ru.avalon.java.j120.internetShop.models.OrderPosition;

/**
 *
 * @author user
 */
// класс который работает с записью и чтением каталогов товаров из файла

public class ItemsReaderWriterSql implements AbstractItemsReaderWriter{
    
               
    // метод записи в файл. На входе путь записи и  коллекция Товаров
    public void writeItems(ArrayList<Item> items, Item newItem, ArrayList<Item> updateItems) throws IOException{
        	
        try(Connection connection = DriverManager.getConnection(Configuration.getInstance().getProperty("url.Db"), 
                    Configuration.getInstance().getProperty("user.Db"),
                    Configuration.getInstance().getProperty("password.Db"))){
            
            if (newItem != null){
                String report = "INSERT INTO ITEMS (ARTICLE, NAME, COLOR, PRICE, STOCK_BALANCE) VALUES (?, ?, ?, ?, ?)";
                try(PreparedStatement predStat = connection.prepareStatement(report)){
                    predStat.setObject(1, newItem.getArticle());
                    predStat.setObject(2, newItem.getName());
                    predStat.setObject(3, newItem.getColor());
                    predStat.setObject(4, newItem.getPrice());
                    predStat.setObject(5, newItem.getStockBalance());
                    predStat.execute();
                }
            
            }
            if (updateItems != null){
                for(Item i : updateItems){
                    String report = "UPDATE ITEMS SET NAME = ?, COLOR = ?, PRICE = ?, STOCK_BALANCE = ? WHERE ARTICLE = ?";
                    try(PreparedStatement predStat = connection.prepareStatement(report)){
                        predStat.setObject(1, i.getName());
                        predStat.setObject(2, i.getColor());
                        predStat.setObject(3, i.getPrice());
                        predStat.setObject(4, i.getStockBalance());
                        predStat.setObject(5, i.getArticle());
                        predStat.execute();
                    }
                }
            }
                
        }
        
        catch(SQLException ex){
            throw new IllegalArgumentException("Error. Записать товары не удалось!!!\n" + ex.getMessage());
        }
    }
    
    // метод чтения из файла. На входе путь записи и  коллекция Товаров
    
    
    public ArrayList<Item> readItems() throws IOException, ParseException{
        
        // создаем коллекцию товаров
        try(Connection connection = DriverManager.getConnection(Configuration.getInstance().getProperty("url.Db"), 
                    Configuration.getInstance().getProperty("user.Db"),
                    Configuration.getInstance().getProperty("password.Db"))){
            try(Statement st = connection.createStatement()){
                final String report = "SELECT * FROM ITEMS";
                try (ResultSet rs = st.executeQuery(report)){
                    // создаем коллекцию товаров
                    ArrayList<Item> items = new ArrayList<Item>();
                    while (rs.next()) {
                        // пробуем создать объект товар и добавить его в коллекцию
                        Item item = new Item(rs.getString(1), rs.getString(2), rs.getString(3), 
                                rs.getInt(4), rs.getInt(5));
                        items.add(item);
                    }
                    return items;
                }
            }
            
        }      
        
        catch(SQLException ex){
            throw new IllegalArgumentException("Error. Прочитать товары не удалось!!!\n" + ex.getMessage());
        }
    }
}
