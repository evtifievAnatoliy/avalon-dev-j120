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
import java.util.*;
import javax.swing.*;

/**
 *
 * @author user
 */
public class MainForm extends JFrame{
    
    private JButton add;
    private JButton del;
    private JButton ex;
    
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
    
    
    private JList<String> list;
    private JTextArea content;
        
    
    public MainForm() {
        
        super("InternetShop"); //название формы
        setBounds(300, 200, 1100, 800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        Container c = getContentPane();
        c.setLayout(new BoxLayout(c, BoxLayout.Y_AXIS));
        
        // отрисовываем и наполняем элементами toolBar
        JPanel jPanelLeft = new JPanel();
        jPanelLeft.setLayout(new FlowLayout(FlowLayout.LEFT));
        JPanel jPanelRight = new JPanel();
        jPanelRight.setLayout(new FlowLayout(FlowLayout.RIGHT));
        
        add = new JButton("Add...");
        del = new JButton("Delite");
        jPanelLeft.add(add);
        jPanelLeft.add(del);
                
        ex = new JButton("Exit");
        ex.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        
        jPanelRight.add(ex);
        
        JToolBar toolBar = new JToolBar();
        toolBar.add(jPanelLeft);
        toolBar.add(jPanelRight);
        
        JPanel jPanelToolBar = new JPanel();
        jPanelToolBar.setLayout(new BoxLayout(jPanelToolBar, BoxLayout.X_AXIS));
        jPanelToolBar.add(toolBar);
        //-----------------------------------------
        
        
        // отрисовываем и наполняем элементами JSplitPane Orders
        list = new JList<>();
        //list.addListSelectionListener(p->fileChousen(p.getFirstIndex()));// лямбда разобраться
        
        content = new JTextArea();
        content.setEditable(false);// запрет на редактирование
        
        // отрисовываем и наполняем элементами JSplitPane Items in Order 
        
        orderNumber = new JTextField("№ заказа", 10);
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
        
        
        JSplitPane splitPaneItems = new JSplitPane(JSplitPane.VERTICAL_SPLIT,
            jPanelOrder,  
            new JScrollPane(list)); // добавлена прокрутка
        splitPaneItems.setDividerLocation(100);
        
        // --------------------------------------------------
        
        JSplitPane splitPaneOrders = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
            new JScrollPane(list),  // добавлена прокрутка
            splitPaneItems);
        splitPaneOrders.setDividerLocation(200);
                
        JPanel jPanelJSplitPaneOrders = new JPanel();
        jPanelJSplitPaneOrders.setLayout(new BoxLayout(jPanelJSplitPaneOrders, BoxLayout.X_AXIS));
        jPanelJSplitPaneOrders.add(splitPaneOrders);
        
        c.add(jPanelToolBar);
        c.add(jPanelJSplitPaneOrders);
        //---------------------------------------------------
        
       
    }
    
    
}
