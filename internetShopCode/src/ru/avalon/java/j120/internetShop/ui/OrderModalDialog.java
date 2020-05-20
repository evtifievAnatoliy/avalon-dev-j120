/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.avalon.java.j120.internetShop.ui;

import java.awt.FlowLayout;
import java.awt.Frame;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import ru.avalon.java.j120.internetShop.controllers.MainController;
import ru.avalon.java.j120.internetShop.models.Item;
import ru.avalon.java.j120.internetShop.models.OrderPosition;

/**
 *
 * @author eag
 */
public class OrderModalDialog extends AbstractModalDialog{
    
    private MainController mainController;
    private ArrayList<Item> items;
    private ArrayList<OrderPosition> orderItems;
    
    private JTextField name;  
    private JTextField contry;
    private JTextField region;
    private JTextField street;
    private JTextField house; 
    private JFormattedTextField flat; 
    private JTextField phoneNumber;
    private JFormattedTextField disconte; 
    
    
    JButton addItembtn;
    JButton delItembtn;
    private JTable orderPositionTable;
    
    public OrderModalDialog(Frame owner, ArrayList<Item> items, MainController mainController) {
        super(owner, "Новый заказ");
        this.items = items;
        this.mainController = mainController;
        orderItems = new ArrayList<OrderPosition>();
        OrderPositionTableModel orderPositionTableModel = new OrderPositionTableModel();
        
        
        JPanel controlsPane = getControlsPane();
        JPanel jPanelOrder = new JPanel();
        jPanelOrder.setLayout(new BoxLayout(jPanelOrder, BoxLayout.Y_AXIS));
        JPanel jPanel;
        
        
        NumberFormat numberFormat;
        JLabel lbl;
        
        jPanel = new JPanel();
        jPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        name = new JTextField(30);
        lbl = new JLabel("Имя клиента: ");
        lbl.setLabelFor(name);
        jPanel.add(lbl);
        jPanel.add(name);
        
        phoneNumber = new JTextField(15);
        lbl = new JLabel("Телефон: ");
        lbl.setLabelFor(phoneNumber);
        jPanel.add(lbl);
        jPanel.add(phoneNumber);
        jPanelOrder.add(jPanel);
        
        jPanel = new JPanel();
        jPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        contry = new JTextField("Российская Федерация", 15);
        lbl = new JLabel("Страна: ");
        lbl.setLabelFor(contry);
        jPanel.add(lbl);
        jPanel.add(contry);
        region = new JTextField("Санкт-Петербург", 20);
        lbl = new JLabel("Регион: ");
        lbl.setLabelFor(region);
        jPanel.add(lbl);
        jPanel.add(region);
        jPanelOrder.add(jPanel);
        
        jPanel = new JPanel();
        jPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        street = new JTextField(20);
        lbl = new JLabel("Улица: ");
        lbl.setLabelFor(street);
        jPanel.add(lbl);
        jPanel.add(street);
        house = new JTextField(5); 
        lbl = new JLabel("Дом: ");
        lbl.setLabelFor(house);
        jPanel.add(lbl);
        jPanel.add(house);
        numberFormat = NumberFormat.getIntegerInstance();
        flat = new JFormattedTextField();
        flat.setColumns(3);
        lbl = new JLabel("Квартира: ");
        lbl.setLabelFor(flat);
        jPanel.add(lbl);
        jPanel.add(flat);
        jPanelOrder.add(jPanel);
        
        jPanel = new JPanel();
        jPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        numberFormat = NumberFormat.getIntegerInstance();
        disconte = new JFormattedTextField();
        disconte.setColumns(3);
        lbl = new JLabel("Скидка: ");
        lbl.setLabelFor(disconte);
        jPanel.add(lbl);
        jPanel.add(disconte);
        jPanelOrder.add(jPanel);
        
        
        JPanel jPanelOrderButton = new JPanel();
        jPanelOrderButton.setLayout(new FlowLayout(FlowLayout.LEFT));
        addItembtn = new JButton("Add Item...");
        addItembtn.addActionListener(e -> {
            AddNewItemToOrderModalDialog addNewItemToOrderModalDialog = new AddNewItemToOrderModalDialog(owner, "Таблица товаров", items, mainController);
            addNewItemToOrderModalDialog.setVisible(true);
            if (addNewItemToOrderModalDialog.isSuccess())
            {
                try{
                OrderPosition newPosition = new OrderPosition(addNewItemToOrderModalDialog.getItem(), 1);
                orderItems.add(newPosition);
                orderPositionTableModel.eventChangeItemsInOrderPositions(orderItems);
                JOptionPane.showMessageDialog(this, 
                            "Товар:\n" + newPosition.getItem().getName() + 
                                    "\nс артикулом \n" + newPosition.getItem().getArticle() + 
                                    "\nдобавлен.",
                            "Добавление товара в заказ",
                            JOptionPane.INFORMATION_MESSAGE);
                }
                catch(Exception ex){
                    JOptionPane.showMessageDialog(this, 
                            ex.getMessage(),
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
            
        });
        delItembtn = new JButton("Delete Item");
        delItembtn.addActionListener(e -> {
            if (orderItems.size() > 0)
            {
                if(orderPositionTable.getSelectedRow()>=0){
                orderItems.remove(orderPositionTable.getSelectedRow());
                orderPositionTableModel.eventChangeItemsInOrderPositions(orderItems);
                JOptionPane.showMessageDialog(this, 
                            "Товар: " + orderItems.get(orderPositionTable.getSelectedRow()).getItem().getName() + 
                                    "\nс артикулом \n" + orderItems.get(orderPositionTable.getSelectedRow()).getItem().getArticle() + 
                                    "\nудален из заказа.",
                            "Удаление товара из заказа.",
                            JOptionPane.INFORMATION_MESSAGE);
                }
                else
                    JOptionPane.showMessageDialog(this, 
                            "Товар на удаление из заказа не выбран.",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
            }
            else{
                JOptionPane.showMessageDialog(this, 
                            "Товаров в заказе нет.",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
            }
            
        });
        jPanelOrderButton.add(addItembtn);
        jPanelOrderButton.add(delItembtn);
        
        jPanelOrder.add(jPanelOrderButton);
        
        
        orderPositionTable = new JTable(orderPositionTableModel);
        // Нужно закрыть доступ к редактированию склада!!!!!!
        
        JSplitPane splitPaneItems = new JSplitPane(JSplitPane.VERTICAL_SPLIT,
            jPanelOrder,  
            new JScrollPane(orderPositionTable)); // добавлена прокрутка
        splitPaneItems.setDividerLocation(160);
        
        controlsPane.add(splitPaneItems);
        pack(); // сформировать правильные размеры окна
        
    }

    
}
