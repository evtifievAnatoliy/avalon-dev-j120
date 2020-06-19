/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.avalon.java.j120.internetShop.controllers.sql;
import java.io.File;
import java.io.FileInputStream;
import ru.avalon.java.j120.internetShop.commons.*;


import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import ru.avalon.java.j120.internetShop.configuration.Configuration;
import ru.avalon.java.j120.internetShop.controllers.AbstractCustomersReaderWriter;
import ru.avalon.java.j120.internetShop.models.Item;
import ru.avalon.java.j120.internetShop.models.Order;
import ru.avalon.java.j120.internetShop.models.OrderPosition;




/**
 *
 * @author eag
 */
// класс который работает с записью и чтением базы клиентов SQL
public class CustomersReaderWriterSql implements AbstractCustomersReaderWriter{
    
    private Object monitorWriteCustomers = new Object();
    private Object monitorReadCustomers = new Object();
    
// метод записи в SQL. Используется только объект Person newCustomer
    public void writeCustomers(ArrayList<Person> customers, Person newCustomer) throws IOException{
        	
        synchronized(monitorWriteCustomers){
            try(Connection connection = DriverManager.getConnection(Configuration.getInstance().getProperty("url.Db"), 
                Configuration.getInstance().getProperty("user.Db"),
                Configuration.getInstance().getProperty("password.Db"))){
            
                if (newCustomer != null)
                    addNewCustomerToSqlBase(connection, newCustomer);
            
            }
            catch(SQLException ex){
                throw new IllegalArgumentException("Error. Записать нового клиента в базу клиентов не удалось!!!\n" + ex.getMessage());
            }    
        }
    }
    
    // метод чтения из SQL. 
    public ArrayList<Person> readCustomers() throws IOException, ClassNotFoundException{
                
        synchronized(monitorReadCustomers){
        
            try(Connection connection = DriverManager.getConnection(Configuration.getInstance().getProperty("url.Db"), 
                    Configuration.getInstance().getProperty("user.Db"),
                    Configuration.getInstance().getProperty("password.Db"))){
                try(Statement st = connection.createStatement()){
                    final String report = "SELECT * FROM CUSTOMERS";
                    try (ResultSet rs = st.executeQuery(report)){
                        // создаем коллекцию товаров
                        ArrayList<Person> customers = new ArrayList<Person>();
                        while (rs.next()) {
                            // пробуем создать объект товар и добавить его в коллекцию
                            Person customer = new Person(rs.getString("PERSON_NAME"), 
                                new Address(rs.getString("CONTRY"), rs.getString("REGION"), rs.getString("STREET"), 
                                        rs.getString("HOUSE"), rs.getString("FLAT")), 
                                rs.getString("PHONE_NUMBER"));
                            customers.add(customer);
                        }
                        return customers;
                    }
                }
            }      
            catch(SQLException ex){
                throw new IllegalArgumentException("Error. Прочитать товары не удалось!!!\n" + ex.getMessage());
            }
        }
    }
    
    
    private void addNewCustomerToSqlBase(Connection connection, Person newCustomer) throws SQLException{
        final String report = "INSERT INTO CUSTOMERS (PERSON_NAME, PHONE_NUMBER, CONTRY, REGION, STREET, HOUSE, FLAT) VALUES (?, ?, ?, ?, ?, ?, ?)";
            try(PreparedStatement predStat = connection.prepareStatement(report)){
                predStat.setObject(1, newCustomer.getName());
                predStat.setObject(2, newCustomer.getPhoneNumber());
                predStat.setObject(3, newCustomer.getAdressToDelivery().getContry());
                predStat.setObject(4, newCustomer.getAdressToDelivery().getRegion());
                predStat.setObject(5, newCustomer.getAdressToDelivery().getStreet());
                predStat.setObject(6, newCustomer.getAdressToDelivery().getHouse());
                predStat.setObject(7, newCustomer.getAdressToDelivery().getFlat());
                predStat.execute();
            }
     }
    
}
