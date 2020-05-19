/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.avalon.java.j120.internetShop.ui;

import java.awt.FlowLayout;
import java.awt.Frame;
import java.text.NumberFormat;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import ru.avalon.java.j120.internetShop.controllers.MainController;
import ru.avalon.java.j120.internetShop.models.Item;

/**
 *
 * @author eag
 */
public class OrderModalDialog extends AbstractModalDialog{
    
    private MainController mainController;
    private ArrayList<Item> items;
    
    
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
    
    JButton addItembtn;
    private JTable orderPositionTable;
    
    public OrderModalDialog(Frame owner, ArrayList<Item> items, MainController mainController) {
        super(owner, "Новый заказ");
        this.items = items;
        this.mainController = mainController;
        
        JPanel controlsPane = getControlsPane();
        
        orderNumber = new JFormattedTextField(NumberFormat.getIntegerInstance());
        orderNumber.setEditable(false);
        orderNumber.setColumns(10);
        orderNumber.setText("1");
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
        
        JPanel jPanelOrderButton = new JPanel();
        jPanelOrderButton.setLayout(new FlowLayout(FlowLayout.LEFT));
        addItembtn = new JButton("Add Item...");
        addItembtn.addActionListener(e -> {
            ItemsModalDialog itemsModalDialog = new ItemsModalDialog(owner, "Таблица товаров", items, mainController);
            itemsModalDialog.setVisible(true);
            if (itemsModalDialog.isSuccess())
            {
                //добавляем заказ
            }
            
        });
        jPanelOrderButton.add(addItembtn);
        jPanelOrder.add(jPanelOrderButton);
        
        
        OrderPositionTableModel orderPositionTableModel = new OrderPositionTableModel();
        orderPositionTable = new JTable(orderPositionTableModel);
        
        JSplitPane splitPaneItems = new JSplitPane(JSplitPane.VERTICAL_SPLIT,
            jPanelOrder,  
            new JScrollPane(orderPositionTable)); // добавлена прокрутка
        splitPaneItems.setDividerLocation(130);
        
        controlsPane.add(splitPaneItems);
        pack(); // сформировать правильные размеры окна
        
    }

    
}
