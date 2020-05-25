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
import ru.avalon.java.j120.internetShop.controllers.*;
import ru.avalon.java.j120.internetShop.models.*;

/**
 *
 * @author user
 */
public class MainForm extends JFrame{
    
    MainController mainController;
    ArrayList<OrderPosition> orderItems = new ArrayList<>();
    OrderManager orderManager;
    Orders orders;
    Order[] ordersArray;
    
    OrderPositionTableModelWithOnlyReadRights orderPositionTableModel = new OrderPositionTableModelWithOnlyReadRights();
    
    private JButton addbtn;
    private JButton delbtn;
    private JButton editbtn;
    private JButton itemsbtn;
    private JButton customersbtn;
    private JButton exbtn;
    private JButton statusOfOrderbtn;
    
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
        orders = mainController.getOrders();
        
        
        Container c = getContentPane();
        c.setLayout(new BoxLayout(c, BoxLayout.Y_AXIS));
        
        // отрисовываем и наполняем элементами toolBar
        JPanel jPanelLeft = new JPanel();
        jPanelLeft.setLayout(new FlowLayout(FlowLayout.LEFT));
        JPanel jPanelRight = new JPanel();
        jPanelRight.setLayout(new FlowLayout(FlowLayout.RIGHT));
        
        addbtn = new JButton("Add...");
        addbtn.addActionListener(e -> {
            try{
                OrderModalDialog orderModalDialog = new OrderModalDialog(this,"Новый заказ", mainController, null);
                orderModalDialog.setVisible(true);
                if (orderModalDialog.isSuccess())
                {
                
                    orderModalDialog.newOrder();
                    convertOrdersListToStringArray();
                    listOrders.setSelectedIndex(listOrders.getModel().getSize()-1);
                    String numberOfOrder = orders.getOrders().get(orders.getOrders().size()-1).getOrderNumber();
                    JOptionPane.showMessageDialog(this, 
                        "Новый заказ " + numberOfOrder +
                            "\n добавлен в список заказов.",
                            "Добавление заказа.",
                        JOptionPane.INFORMATION_MESSAGE);
                
                }
            }
            catch(Exception ex){
                    JOptionPane.showMessageDialog(this, ex.getMessage(), "Error",  JOptionPane.ERROR_MESSAGE);
            }
        });
        
                
        
        
        
        delbtn = new JButton("Delite");
        delbtn.addActionListener(e -> {
            ConfirmationModalDialog confirmationModalDialog = new ConfirmationModalDialog(this, "Вы действительно хотите удалить заказ.");
            confirmationModalDialog.setVisible(true);
            if (confirmationModalDialog.isSuccess()){
                try{
                    String numberOfSelectedOrder = orders.getOrders().get(listOrders.getSelectedIndex()).getOrderNumber();
                    orders.removeOrder(listOrders.getSelectedIndex());
                    JOptionPane.showMessageDialog(this, 
                       "Заказ: " + numberOfSelectedOrder  + 
                           "\n удален.",
                            "Удаление заказа.",
                            JOptionPane.INFORMATION_MESSAGE);
                    convertOrdersListToStringArray();
                    if(listOrders.getModel().getSize() > 0)
                        listOrders.setSelectedIndex(listOrders.getModel().getSize()-1);
                                    
                
                
                    mainController.writeOrder();
                }
                catch(Exception ex){
                    JOptionPane.showMessageDialog(this, ex.getMessage(), "Error",  JOptionPane.ERROR_MESSAGE);
                }       
            }
        });
               
        editbtn = new JButton("Edit...");
        editbtn.addActionListener(e -> {
            try{
                Order editOrder = orders.getOrder(listOrders.getSelectedIndex());
                String numberOfOrder = orders.getOrders().get(listOrders.getSelectedIndex()).getOrderNumber();
                Integer numberOfSelectedOrder = listOrders.getSelectedIndex();
                OrderModalDialog orderModalDialog = new OrderModalDialog(this,"Изменение заказа", mainController, editOrder);
                orderModalDialog.setVisible(true);
                if (orderModalDialog.isSuccess())
                {
                
                    orderModalDialog.editOrder();
                    convertOrdersListToStringArray();
                    listOrders.setSelectedIndex(numberOfSelectedOrder);
                    JOptionPane.showMessageDialog(this, 
                        "Заказ " + numberOfOrder +
                            " изменен.",
                            "Изменение заказа.",
                        JOptionPane.INFORMATION_MESSAGE);
                }
            }
                catch(Exception ex){
                    JOptionPane.showMessageDialog(this, ex.getMessage(), "Error",  JOptionPane.ERROR_MESSAGE);
                   
                    
            }
            
        });
        
        jPanelLeft.add(addbtn);
        jPanelLeft.add(delbtn);
        jPanelLeft.add(editbtn);
        
        
        itemsbtn = new JButton("Items...");
        itemsbtn.addActionListener(e -> {
            ItemsModalDialog itemsModalDialog = new ItemsModalDialog(this, "Таблица товаров", mainController);
            itemsModalDialog.setVisible(true);
                        
        });
        
        customersbtn = new JButton("Customers...");
        customersbtn.addActionListener(e -> {
            CustomersModalDialog customersModalDialog = new CustomersModalDialog(this, "Таблица клиентов.", mainController);
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
        dateTheOrderWasGreated.setEditable(false);
        name = new JTextField("Имя клиента", 30);
        name.setEditable(false);
        phoneNumber = new JTextField("Телефон", 15);
        phoneNumber.setEditable(false);
        contry = new JTextField("Страна", 15);
        contry.setEditable(false);
        region = new JTextField("Регион", 20);
        region.setEditable(false);
        street = new JTextField("Улица", 20);
        street.setEditable(false);
        house = new JTextField("Дом", 5); 
        house.setEditable(false);
        flat = new JTextField("Кв.", 3); 
        flat.setEditable(false);
        disconte = new JTextField("Скидка", 5); 
        disconte.setEditable(false);
        statusOfOrder = new JTextField("Статус заказа", 10); 
        statusOfOrder.setEditable(false);
        
        statusOfOrderbtn = new JButton("Change...");
        statusOfOrderbtn.addActionListener(e -> {
            if(listOrders.getSelectedIndex() >=0)
            {
                StatusOfOrder statusOfOrderBeforeChanging =  orders.getOrders().get(listOrders.getSelectedIndex()).getStatusOfOrder();
                ChangeStatusOfOrderModalDialog changeStatusOfOrderModalDialog = new ChangeStatusOfOrderModalDialog(this,"Изменение статуса заказа", statusOfOrderBeforeChanging, mainController);
                changeStatusOfOrderModalDialog.setVisible(true);
                if (changeStatusOfOrderModalDialog.isSuccess())
                {
                    try{
                        if(statusOfOrderBeforeChanging != changeStatusOfOrderModalDialog.getStatusCimbobox())
                        {
                            Integer numberOfSelectedOrder = listOrders.getSelectedIndex();
                            String numberOfOrder = orders.getOrders().get(listOrders.getSelectedIndex()).getOrderNumber();
                            changeStatusOfOrderModalDialog.setStatusOrder(numberOfOrder);
                            convertOrdersListToStringArray();
                            listOrders.setSelectedIndex(numberOfSelectedOrder);
                            JOptionPane.showMessageDialog(this, 
                                "У заказа " + numberOfOrder +
                                    "\n изменен статус. \nНовый статус заказа: " + changeStatusOfOrderModalDialog.getStatusCimbobox().toString(),
                                "Изменение статуса заказа.",
                                JOptionPane.INFORMATION_MESSAGE);
                        }
                    }
                    catch(Exception ex){
                        JOptionPane.showMessageDialog(this, ex.getMessage(), "Error",  JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
         
          
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
        jPanelOrderDiscountStatus.add(statusOfOrderbtn);
        
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
                ordersNumbers[i] = this.orders.getOrders().get(i).getOrderNumber();
                
            }
            
            listOrders.setListData(ordersNumbers);
        }
    }
    
    private void ordersChoosen (int ndx){
        
        if(ndx >= 0)
        {
            orderNumber.setText(this.orders.getOrders().get(ndx).getOrderNumber()); 
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
    
    
}
