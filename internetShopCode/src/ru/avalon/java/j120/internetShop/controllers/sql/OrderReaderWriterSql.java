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
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;



import java.util.ArrayList;
import ru.avalon.java.j120.internetShop.commons.Address;
import ru.avalon.java.j120.internetShop.commons.Person;
import ru.avalon.java.j120.internetShop.configuration.Configuration;
import ru.avalon.java.j120.internetShop.controllers.AbstractOrderReaderWriter;
import ru.avalon.java.j120.internetShop.controllers.OrderManager;


/**
 *
 * @author user
 */
// класс который работает с записью и чтением каталогов заказов из файла

public class OrderReaderWriterSql implements AbstractOrderReaderWriter{
    
    private Object monitorWriteOrders = new Object();
    private Object monitorReadOrders = new Object();
    
    // метод записи в файл. На входе путь записи и  коллекция Товаров
    public void writeOrders(ArrayList<Order> orders, Order newOrder, Order delOrder, Order updateOrder) throws IOException{
        	
        synchronized(monitorWriteOrders){
        // пробуем записатьколлекцию заказов 
            try(Connection connection = DriverManager.getConnection(Configuration.getInstance().getProperty("url.Db"), 
                Configuration.getInstance().getProperty("user.Db"),
                Configuration.getInstance().getProperty("password.Db"))){
            
                if (newOrder != null)
                    addNewOrderToSqlBase(connection, newOrder);
            
                if (delOrder != null)
                    delOrderFromSqlBase(connection, delOrder);
                        
                if (updateOrder != null){
                    updateOrderInSqlBase(connection, updateOrder);
                                
                }
            }
            catch(SQLException ex){
                throw new IllegalArgumentException("Error. Записать заказы не удалось!!!\n" + ex.getMessage());
            }    
        }
    }
    
    // метод чтения из базы данных коллекции Заказов
    public ArrayList<Order> readOrders() throws IOException, ClassNotFoundException{
           
        synchronized(monitorReadOrders){
            try(Connection connection = DriverManager.getConnection(Configuration.getInstance().getProperty("url.Db"), 
                    Configuration.getInstance().getProperty("user.Db"),
                    Configuration.getInstance().getProperty("password.Db"))){
            
                try(Statement st = connection.createStatement()){
                    final String report = "SELECT * FROM ORDERS ord " +
                        "inner join PERSONS per on ord.CONTACT_PERSON = per.PERSON_ID " +
                        "inner join ADDRESSES adr on per.ADDRESS_TO_DELIVERY = adr.ADDRESS_ID";
                    try (ResultSet rs = st.executeQuery(report)){
                        // создаем коллекцию заказов
                        ArrayList<Order> orders = new ArrayList<Order>();
                        while (rs.next()) {
                            // пробуем создать объект заказ и добавить его в коллекцию
                            // заказ создается без товаров
                            Order order = new Order(rs.getString("ORDER_NUMBER"), rs.getDate("DATE_THE_ORDER_WAS_GREATED").toLocalDate(), 
                                new Person(rs.getString("PERSON_NAME"),
                                    new Address(rs.getString("CONTRY"), rs.getString("REGION"), rs.getString("STREET"), rs.getString("HOUSE"), 
                                            rs.getString("FLAT")),
                                rs.getString("PHONE_NUMBER")),
                                rs.getByte("DISCONTE"), 
                                StatusOfOrder.valueOf(rs.getString("STATUS_OF_ORDER")), 
                                        null);
                            orders.add(order);
                        }
                        // добавляем товары в заказы
                        for (Order order: orders){
                        
                            String reportOrderPositions = "SELECT * FROM ORDER_POSITIONS ordPos " +
                                " inner join ITEMS item on ordPos.ITEM_ID = item.ARTICLE" +
                                " where  ordPos.ORDER_ID = '" + order.getOrderNumber() + "'";
                            OrderManager orderManager = null;
                            try (ResultSet rsOrderPositions = st.executeQuery(reportOrderPositions)){
                                ArrayList<OrderPosition> orderItems = new ArrayList<OrderPosition>();
                                while (rsOrderPositions.next()){
                                    OrderPosition Item = new OrderPosition(
                                        new Item(rsOrderPositions.getString("ARTICLE"), rsOrderPositions.getString("NAME"), 
                                                rsOrderPositions.getString("COLOR"), rsOrderPositions.getInt("PRICE"),
                                                rsOrderPositions.getInt("STOCK_BALANCE")), 
                                        rsOrderPositions.getInt("NUMBER_OF_ITEMS"), 
                                        rsOrderPositions.getByte("DISCONTE"));
                                    orderItems.add(Item);
                                }
                                orderManager = new OrderManager(orderItems);
                                order.setOrderManager(orderManager);
                                }
                            }
                            return orders;
                        }
                }
            }   
            catch(SQLException ex){
                throw new IllegalArgumentException("Error. Прочитать заказы не удалось!!!\n" + ex.getMessage());
            } 
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
    
    private void addNewOrderToSqlBase(Connection connection, Order newOrder) throws SQLException{
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
                    predStat.setObject(2, Date.valueOf(newOrder.getDateTheOrderWasGreated()));
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
    
    }
    private void delOrderFromSqlBase(Connection connection, Order delOrder) throws SQLException{
        
        
        try(Statement st = connection.createStatement()){
            final String reportOrderPosition = "DELETE FROM ORDER_POSITIONS WHERE ORDER_ID = '"
                                            + delOrder.getOrderNumber() + "'";
            st.executeUpdate(reportOrderPosition);
        
            final String reportSelectOrder = "SELECT * FROM ORDERS ord " +
                        "inner join PERSONS per on ord.CONTACT_PERSON = per.PERSON_ID " +
                        "inner join ADDRESSES adr on per.ADDRESS_TO_DELIVERY = adr.ADDRESS_ID " +
                        "WHERE ord.ORDER_NUMBER = '" + delOrder.getOrderNumber() + "'";
            int addressId = 0;
            int personId = 0;
            try (ResultSet rs = st.executeQuery(reportSelectOrder)){
                while(rs.next()){
                    addressId = rs.getInt("ADDRESS_ID");
                    personId = rs.getInt("PERSON_ID");
                }
            }
            
            final String reportOrder = "DELETE FROM ORDERS WHERE ORDER_NUMBER = '"
                                            + delOrder.getOrderNumber() + "'";
            st.executeUpdate(reportOrder);
            
            final String reportPerson = "DELETE FROM PERSONS WHERE PERSON_ID = "
                                            + personId;
            st.executeUpdate(reportPerson);
            
            final String reportAdress = "DELETE FROM ADDRESSES WHERE ADDRESS_ID = "
                                            + addressId;
            st.executeUpdate(reportAdress);
            
        }
         
               
    }
    
    private void updateOrderInSqlBase(Connection connection, Order updateOrder) throws SQLException{
                
        try(Statement st = connection.createStatement()){
            
            final String reportSelectOrder = "SELECT * FROM ORDERS ord " +
                        "inner join PERSONS per on ord.CONTACT_PERSON = per.PERSON_ID " +
                        "inner join ADDRESSES adr on per.ADDRESS_TO_DELIVERY = adr.ADDRESS_ID " +
                        "WHERE ord.ORDER_NUMBER = '" + updateOrder.getOrderNumber() + "'";
            int addressId = 0;
            int personId = 0;
            try (ResultSet rs = st.executeQuery(reportSelectOrder)){
                while(rs.next()){
                    addressId = rs.getInt("ADDRESS_ID");
                    personId = rs.getInt("PERSON_ID");
                }
            }
            
            final String reportOrder = "UPDATE ORDERS SET DISCONTE = ?, STATUS_OF_ORDER = ? WHERE ORDER_NUMBER = ?";
            try(PreparedStatement predStat = connection.prepareStatement(reportOrder)){
                        predStat.setObject(1, (int)updateOrder.getDisconte());
                        predStat.setObject(2, updateOrder.getStatusOfOrder().name());
                        predStat.setObject(3, updateOrder.getOrderNumber());
                        predStat.execute();
                    }
            
            final String reportPerson = "UPDATE PERSONS SET PERSON_NAME = ?, PHONE_NUMBER = ? WHERE PERSON_ID = ?";
            try(PreparedStatement predStat = connection.prepareStatement(reportPerson)){
                        predStat.setObject(1, updateOrder.getContactPerson().getName());
                        predStat.setObject(2, updateOrder.getContactPerson().getPhoneNumber());
                        predStat.setObject(3, personId);
                        predStat.execute();
                    }
            
            final String reportAdress = "UPDATE ADDRESSES SET CONTRY = ?, REGION = ?, STREET = ?, HOUSE = ?, FLAT = ? WHERE ADDRESS_ID = ?";
            try(PreparedStatement predStat = connection.prepareStatement(reportAdress)){
                        predStat.setObject(1, updateOrder.getContactPerson().getAdressToDelivery().getContry());
                        predStat.setObject(2, updateOrder.getContactPerson().getAdressToDelivery().getRegion());
                        predStat.setObject(3, updateOrder.getContactPerson().getAdressToDelivery().getStreet());
                        predStat.setObject(4, updateOrder.getContactPerson().getAdressToDelivery().getHouse());
                        predStat.setObject(5, updateOrder.getContactPerson().getAdressToDelivery().getFlat());
                        predStat.setObject(6, addressId);
                        predStat.execute();
                    }
            
            final String reportDelOrderPositions = "DELETE FROM ORDER_POSITIONS WHERE ORDER_ID = '"
                                            + updateOrder.getOrderNumber() + "'";
            st.executeUpdate(reportDelOrderPositions);
            
            for(OrderPosition orderPosition : updateOrder.getOrderManager().getOrderItems()){
                    String reportAddOrderPosition = "INSERT INTO ORDER_POSITIONS (ORDER_ID, ITEM_ID, NUMBER_OF_ITEMS, AMOUNT_OF_ITEMS, DISCONTE) VALUES (?, ?, ?, ?, ?)";
                    try(PreparedStatement predStat = connection.prepareStatement(reportAddOrderPosition)){
                        predStat.setObject(1, updateOrder.getOrderNumber());
                        predStat.setObject(2, orderPosition.getItem().getArticle());
                        predStat.setObject(3, orderPosition.getNumberOfItems());
                        predStat.setObject(4, orderPosition.getAmountOfItems());
                        predStat.setObject(5, updateOrder.getDisconte());
                        predStat.execute();
                    }
                      
                }
            
        }          
    }
}
