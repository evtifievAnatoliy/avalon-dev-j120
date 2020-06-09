/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.avalon.java.j120.internetShop.controllers.sql;
import java.io.File;
import ru.avalon.java.j120.internetShop.models.*;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;



import java.util.ArrayList;
import ru.avalon.java.j120.internetShop.configuration.Configuration;
import ru.avalon.java.j120.internetShop.controllers.AbstractOrderReaderWriter;


/**
 *
 * @author user
 */
// класс который работает с записью и чтением каталогов заказов из файла

public class OrderReaderWriterSql implements AbstractOrderReaderWriter{
    
    
    // метод записи в файл. На входе путь записи и  коллекция Товаров
    public void writeOrders(ArrayList<Order> orders, Order newOrder, Order delOrder, Order updateOrder) throws IOException{
        	
        
    // пробуем записатьколлекцию заказов 
        try(Connection connection = DriverManager.getConnection(Configuration.getInstance().getProperty("url.Db"), 
                Configuration.getInstance().getProperty("user.Db"),
                Configuration.getInstance().getProperty("password.Db"))){
            
            if (newOrder != null){
                
                final int adreesId = findLastId (connection, "ADDRESSES") + 1;
                final String reportAddress = "INSERT INTO ADDRESSES (ADDRESS_ID, CONTRY, REGION, STREET, HOUSE, FLAT) VALUES (?, ?, ?, ?, ?, ?)";
                try(PreparedStatement predStat = connection.prepareStatement(reportAddress)){
                    predStat.setObject(1, adreesId);
                    predStat.setObject(2, newOrder.getContactPerson().getAdressToDelivery().getContry());
                    predStat.setObject(3, newOrder.getContactPerson().getAdressToDelivery().getRegion());
                    predStat.setObject(4, newOrder.getContactPerson().getAdressToDelivery().getStreet());
                    predStat.setObject(5, newOrder.getContactPerson().getAdressToDelivery().getHouse());
                    predStat.setObject(6, newOrder.getContactPerson().getAdressToDelivery().getFlat());
                    predStat.execute();
                }
                
                final int personId = findLastId (connection, "PERSONS") + 1;
                final String reportPerson = "INSERT INTO PERSONS (PERSON_ID, PERSON_NAME, PHONE_NUMBER, ADDRESS_TO_DELIVERY) VALUES (?, ?, ?, ?)";
                try(PreparedStatement predStat = connection.prepareStatement(reportPerson)){
                    predStat.setObject(1, personId);
                    predStat.setObject(2, newOrder.getContactPerson().getName());
                    predStat.setObject(3, newOrder.getContactPerson().getPhoneNumber());
                    predStat.setObject(4, adreesId);
                    predStat.execute();
                }
                
                final String reportOrders = "INSERT INTO ORDERS (ORDER_NUMBER, DATE_THE_ORDER_WAS_GREATED, CONTACT_PERSON, DISCONTE, STATUS_OF_ORDER) VALUES (?, ?, ?, ?, ?)";
                try(PreparedStatement predStat = connection.prepareStatement(reportOrders)){
                    predStat.setObject(1, newOrder.getOrderNumber());
                    predStat.setObject(2, Timestamp.valueOf(newOrder.getDateTheOrderWasGreated()));
                    predStat.setObject(3, personId);
                    predStat.setObject(4, (int)newOrder.getDisconte());
                    predStat.setObject(5, newOrder.getStatusOfOrder().name());
                    predStat.execute();
                }
                
                
                for(OrderPosition orderPosition : newOrder.getOrderManager().getOrderItems()){
                    String reportOrderPosition = "INSERT INTO ORDER_POSITIONS (ORDER_ID, ITEM_ID, NUMBER_OF_ITEMS, AMOUNT_OF_ITEMS, DISCONTE) VALUES (?, ?, ?, ?, ?)";
                    try(PreparedStatement predStat = connection.prepareStatement(reportOrderPosition)){
                        predStat.setObject(1, newOrder.getOrderNumber());
                        predStat.setObject(2, orderPosition.getItem().getArticle());
                        predStat.setObject(3, orderPosition.getNumberOfItems());
                        predStat.setObject(4, orderPosition.getAmountOfItems());
                        predStat.setObject(5, newOrder.getDisconte());
                        predStat.execute();
                    }
                      
                }
                
            
            }/*
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
            }*/
        }
        catch(SQLException ex){
            throw new IllegalArgumentException("Error. Записать заказы не удалось!!!\n" + ex.getMessage());
        }    
    }
    
    // метод чтения из файла. На входе путь записи и  коллекция Товаров
    public ArrayList<Order> readOrders() throws IOException, ClassNotFoundException{
                
        File file = new File(Configuration.getInstance().getProperty("orders.Path"));
        if(!file.exists()){
            ArrayList<Order> orders = new ArrayList<Order>();
            return orders;
        }
        
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))){
            // пробуем десериализовать коллекцию заказов
            ArrayList<Order> orders = (ArrayList<Order>) ois.readObject();
                        
            return orders;
            
        }
            
        catch (IOException e){
            throw new IllegalArgumentException("Error. Ошибка чтения файла списка заказов. ");
        }
    }
    
    
    private int findLastId (Connection connection, String nameOfTheBase) throws SQLException{
        try (Statement st = connection.createStatement()){
                    String query = "select * from " + nameOfTheBase;
                    try(ResultSet rs = st.executeQuery(query)){
                        int i = 0;
                        while(rs.next()){
                            i = rs.getInt(1);
                        }
                        return i;
                    }
                }
    } 
}
