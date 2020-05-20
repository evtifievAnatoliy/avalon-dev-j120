/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.avalon.java.j120.internetShop.ui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.text.ParseException;
import java.util.*;
import javax.swing.*;
import ru.avalon.java.j120.internetShop.commons.*;
import ru.avalon.java.j120.internetShop.controllers.*;
import ru.avalon.java.j120.internetShop.models.*;

/**
 *
 * @author user
 */
public class MainForm extends JFrame{
    
    MainController mainController;
    StockItems stockItems;
    CustomersManager customersManager;
    ArrayList<OrderPosition> orderItems = new ArrayList<>();
    OrderManager orderManager;
    Orders orders;
    Order[] ordersArray;
    
    OrderPositionTableModel orderPositionTableModel = new OrderPositionTableModel();
    
    private JButton addbtn;
    private JButton itemsbtn;
    private JButton customersbtn;
    private JButton delbtn;
    private JButton exbtn;
    
    private JTextField orderNumber;
    private JTextField dateTheOrderWasGreated;
    private JTextField name;  
    private JTextField contry;
    private JTextField region;
    private JTextField street;
    private JTextField house; 
    private JTextField flat; 
    private JTextField phoneNumber;
    private JTextField disconte; 
    private JTextField statusOfOrder;
    
    
    private JList<String> listOrders;
    private JTable orderPositionTable;
            
    
    public MainForm() throws IOException, ClassNotFoundException, ParseException {
        
        super("InternetShop"); //название формы
        setBounds(300, 200, 1100, 800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        
        
        //общий объект работающий с товарами и заказами
        mainController = new MainController();
        stockItems = mainController.getStockItems();
        customersManager = mainController.getCustomersManager();
        orders = mainController.getOrders();
        orderManager = mainController.getOrderManager();
         
        
        
        
        Container c = getContentPane();
        c.setLayout(new BoxLayout(c, BoxLayout.Y_AXIS));
        
        // отрисовываем и наполняем элементами toolBar
        JPanel jPanelLeft = new JPanel();
        jPanelLeft.setLayout(new FlowLayout(FlowLayout.LEFT));
        JPanel jPanelRight = new JPanel();
        jPanelRight.setLayout(new FlowLayout(FlowLayout.RIGHT));
        
        addbtn = new JButton("Add...");
        addbtn.addActionListener(e -> {
            OrderModalDialog orderModalDialog = new OrderModalDialog(this, stockItems.getItemsAsList(), mainController);
            orderModalDialog.setVisible(true);
            if (orderModalDialog.isSuccess())
            {
                //добавляем заказ
            }
            
        });
        
        
        
        delbtn = new JButton("Delite");
        
        jPanelLeft.add(addbtn);
        jPanelLeft.add(delbtn);
        
        
        itemsbtn = new JButton("Items...");
        itemsbtn.addActionListener(e -> {
            ItemsModalDialog itemsModalDialog = new ItemsModalDialog(this, "Таблица товаров", stockItems.getItemsAsList(), mainController);
            itemsModalDialog.setVisible(true);
            if (itemsModalDialog.isSuccess())
            {
                //добавляем заказ
            }
            
        });
        
        customersbtn = new JButton("Customers...");
        customersbtn.addActionListener(e -> {
            CustomersModalDialog customersModalDialog = new CustomersModalDialog(this, "Таблица товаров", customersManager.getCustomersAsList(), mainController);
            customersModalDialog.setVisible(true);
            
            
        });
        
        exbtn = new JButton("Exit");
        exbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        
        jPanelRight.add(itemsbtn);
        jPanelRight.add(customersbtn);
        jPanelRight.add(exbtn);
        
        
        JToolBar toolBar = new JToolBar();
        toolBar.add(jPanelLeft);
        toolBar.add(jPanelRight);
        
        JPanel jPanelToolBar = new JPanel();
        jPanelToolBar.setLayout(new BoxLayout(jPanelToolBar, BoxLayout.X_AXIS));
        jPanelToolBar.add(toolBar);
        //-----------------------------------------
        
        
        // отрисовываем и наполняем элементами JSplitPane Orders
        listOrders = new JList<>();
        convertOrdersListToStringArray();
        listOrders.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listOrders.addListSelectionListener(e -> ordersChoosen(listOrders.getSelectedIndex()));
       
        
        
        // отрисовываем и наполняем элементами JSplitPane Items in Order 
        
        orderNumber = new JTextField("№ заказа", 10);
        orderNumber.setEditable(false);
        dateTheOrderWasGreated = new JTextField("Дата заказа", 15);
        name = new JTextField("Имя клиента", 30);  
        phoneNumber = new JTextField("Телефон", 15);
        contry = new JTextField("Страна", 15);
        region = new JTextField("Регион", 20);
        street = new JTextField("Улица", 20);
        house = new JTextField("Дом", 5); 
        flat = new JTextField("Кв.", 3); 
        disconte = new JTextField("Скидка", 5); 
        statusOfOrder = new JTextField("Статус заказа", 20);
        
        JPanel jPanelOrderNumberDatePerson = new JPanel();
        jPanelOrderNumberDatePerson.setLayout(new FlowLayout(FlowLayout.LEFT));
        jPanelOrderNumberDatePerson.add(orderNumber);
        jPanelOrderNumberDatePerson.add(dateTheOrderWasGreated);
        jPanelOrderNumberDatePerson.add(name);
        jPanelOrderNumberDatePerson.add(phoneNumber);
        
        JPanel jPanelOrderAdress = new JPanel();
        jPanelOrderAdress.setLayout(new FlowLayout(FlowLayout.LEFT));
        jPanelOrderAdress.add(contry);
        jPanelOrderAdress.add(region);
        jPanelOrderAdress.add(street);
        jPanelOrderAdress.add(house);
        jPanelOrderAdress.add(flat);
        
        JPanel jPanelOrderDiscountStatus = new JPanel();
        jPanelOrderDiscountStatus.setLayout(new FlowLayout(FlowLayout.LEFT));
        jPanelOrderDiscountStatus.add(disconte);
        jPanelOrderDiscountStatus.add(statusOfOrder);
        
        
        
        JPanel jPanelOrder = new JPanel();
        jPanelOrder.setLayout(new BoxLayout(jPanelOrder, BoxLayout.Y_AXIS));
        jPanelOrder.add(jPanelOrderNumberDatePerson);
        jPanelOrder.add(jPanelOrderAdress);
        jPanelOrder.add(jPanelOrderDiscountStatus);
        
       
        orderPositionTable = new JTable(orderPositionTableModel);
        
        JSplitPane splitPaneItems = new JSplitPane(JSplitPane.VERTICAL_SPLIT,
            jPanelOrder,  
            new JScrollPane(orderPositionTable)); // добавлена прокрутка
        splitPaneItems.setDividerLocation(100);
        
        // --------------------------------------------------
        
        
        
        
        JSplitPane splitPaneOrders = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
            new JScrollPane(listOrders),  // добавлена прокрутка
            splitPaneItems);
        splitPaneOrders.setDividerLocation(200);
                
        JPanel jPanelJSplitPaneOrders = new JPanel();
        jPanelJSplitPaneOrders.setLayout(new BoxLayout(jPanelJSplitPaneOrders, BoxLayout.X_AXIS));
        jPanelJSplitPaneOrders.add(splitPaneOrders);
        
        c.add(jPanelToolBar);
        c.add(jPanelJSplitPaneOrders);
        //---------------------------------------------------
        
               
    }
    
    private void convertOrdersListToStringArray(){
        if(this.orders !=null){
      
            String[] ordersNumbers = new String[this.orders.getOrders().size()];
            
            for (int i=0; i<this.orders.getOrders().size(); i++)
            {
                ordersNumbers[i] = this.orders.getOrders().get(i).getOrderNumber().toString();
                
            }
            
            listOrders.setListData(ordersNumbers);
        }
    }
    
    private void ordersChoosen (int ndx){
        
        orderNumber.setText(this.orders.getOrders().get(ndx).getOrderNumber().toString()); 
        dateTheOrderWasGreated.setText(this.orders.getOrders().get(ndx).getDateTheOrderWasGreated().toLocalDate().toString());
        name.setText(this.orders.getOrders().get(ndx).getContactPerson().getName());;  
        phoneNumber.setText(this.orders.getOrders().get(ndx).getContactPerson().getPhoneNumber());;
        contry.setText(this.orders.getOrders().get(ndx).getContactPerson().getAdressToDelivery().getContry());;
        region.setText(this.orders.getOrders().get(ndx).getContactPerson().getAdressToDelivery().getRegion());;
        street.setText(this.orders.getOrders().get(ndx).getContactPerson().getAdressToDelivery().getStreet());;
        house.setText(this.orders.getOrders().get(ndx).getContactPerson().getAdressToDelivery().getHouse());;
        flat.setText(this.orders.getOrders().get(ndx).getContactPerson().getAdressToDelivery().getFlat().toString());;
        disconte.setText(this.orders.getOrders().get(ndx).getDisconte().toString());
        statusOfOrder.setText(this.orders.getOrders().get(ndx).getStatusOfOrder().toString());
        
        orderPositionTableModel.eventChangeItemsInOrderPositions(this.orders.getOrders().get(ndx).getOrderItems());
        
    }
    
    
}
