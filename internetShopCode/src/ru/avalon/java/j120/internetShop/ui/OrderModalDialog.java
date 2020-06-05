/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.avalon.java.j120.internetShop.ui;

import java.awt.FlowLayout;
import java.awt.Frame;
import java.io.IOException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
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
import ru.avalon.java.j120.internetShop.commons.Address;
import ru.avalon.java.j120.internetShop.commons.Person;
import ru.avalon.java.j120.internetShop.configuration.Configuration;
import ru.avalon.java.j120.internetShop.controllers.*;
import ru.avalon.java.j120.internetShop.controllers.interfaces.MainControllerInterface;
import ru.avalon.java.j120.internetShop.models.Item;
import ru.avalon.java.j120.internetShop.models.Order;
import ru.avalon.java.j120.internetShop.models.OrderPosition;
import ru.avalon.java.j120.internetShop.models.StatusOfOrder;

/**
 *
 * @author eag
 */
public class OrderModalDialog extends AbstractModalDialog{
    
    private MainControllerInterface mainController;
    private OrderManager orderManager;
    private CustomersManager customersManager;
    private Orders orders;
    private StockItems stockItems;
    private ArrayList<Item> items;
    private ArrayList<OrderPosition> orderItems;
    private Order editOrder;
    
    private JTextField name;  
    private JTextField contry;
    private JTextField region;
    private JTextField street;
    private JTextField house; 
    private JFormattedTextField flat; 
    private JTextField phoneNumber;
    private JFormattedTextField disconte; 
    private OrderPositionTableModel orderPositionTableModel;
    
    JButton addItembtn;
    JButton delItembtn;
    private JTable orderPositionTable;
    
    public OrderModalDialog(Frame owner, String nameModalDialog, MainControllerInterface mainController, Order editOrder) throws IOException {
        
        super(owner, nameModalDialog);
        this.mainController = mainController;
        this.editOrder = editOrder;
        stockItems = mainController.getStockItems();
        orders = mainController.getOrders();
        customersManager = mainController.getCustomersManager();
        orderManager = mainController.getOrderManager();
        
        orderItems = orderManager.getOrderItems();
        this.items = stockItems.getItemsAsList();
        
        orderPositionTableModel = new OrderPositionTableModel();
        
        
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
        disconte.setText(Configuration.getInstance().getProperty("max.Discount"));
        lbl = new JLabel("Скидка: ");
        lbl.setLabelFor(disconte);
        jPanel.add(lbl);
        jPanel.add(disconte);
        jPanelOrder.add(jPanel);
        
        
        disconte.addActionListener(e ->{
            try {
                NumberFormat numberFormatDiscount =  NumberFormat.getIntegerInstance();
                orderManager.changeAmountOfItems(numberFormatDiscount.parse(disconte.getText()).byteValue());
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, 
                            ex.getMessage(),
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
            }
            orderPositionTableModel.eventChangeItemsInOrderPositions(orderItems);
        }
        );
        
        JPanel jPanelOrderButton = new JPanel();
        jPanelOrderButton.setLayout(new FlowLayout(FlowLayout.LEFT));
        addItembtn = new JButton("Add Item...");
        addItembtn.addActionListener(e -> {
            AddNewItemToOrderModalDialog addNewItemToOrderModalDialog = new AddNewItemToOrderModalDialog(owner, "Таблица товаров", items, mainController);
            addNewItemToOrderModalDialog.setVisible(true);
            if (addNewItemToOrderModalDialog.isSuccess())
            {
                try{
                NumberFormat numberFormatDisconte =  NumberFormat.getIntegerInstance();
                orderManager.addOrderPosition (addNewItemToOrderModalDialog.getItem(), 1, numberFormatDisconte.parse(disconte.getText()).byteValue(), editOrder);
                orderPositionTableModel.eventChangeItemsInOrderPositions(orderItems);
                JOptionPane.showMessageDialog(this, 
                            "Товар:\n" + addNewItemToOrderModalDialog.getItem().getName() + 
                                    "\nс артикулом \n" + addNewItemToOrderModalDialog.getItem().getArticle() + 
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
            
            try{
                String numberRemoveOrderItem = orderItems.get(orderPositionTable.getSelectedRow()).getItem().getName();
                String articleRemoveOrderItem = orderItems.get(orderPositionTable.getSelectedRow()).getItem().getArticle();
                
                orderManager.removeOrderPosition(orderPositionTable.getSelectedRow(), editOrder);
                orderPositionTableModel.eventChangeItemsInOrderPositions(orderItems);
                JOptionPane.showMessageDialog(this, 
                    "Товар: " + numberRemoveOrderItem + 
                        "\nс артикулом \n" + articleRemoveOrderItem + 
                        "\nудален из заказа.",
                        "Удаление товара из заказа.",
                        JOptionPane.INFORMATION_MESSAGE);
            }
            catch(Exception ex){
                if (ex.getMessage().equals("-1"))
                        JOptionPane.showMessageDialog(this, "Не выбран товар!!!", "Error",  JOptionPane.ERROR_MESSAGE);
                else
                    JOptionPane.showMessageDialog(this, ex.getMessage(), "Error",  JOptionPane.ERROR_MESSAGE);
            }
            
        });
        jPanelOrderButton.add(addItembtn);
        jPanelOrderButton.add(delItembtn);
        
        jPanelOrder.add(jPanelOrderButton);
        
        
        orderPositionTable = new JTable(orderPositionTableModel);
                
        JSplitPane splitPaneItems = new JSplitPane(JSplitPane.VERTICAL_SPLIT,
            jPanelOrder,  
            new JScrollPane(orderPositionTable)); // добавлена прокрутка
        splitPaneItems.setDividerLocation(160);
        
        controlsPane.add(splitPaneItems);
        pack(); // сформировать правильные размеры окна
        
        if (editOrder != null)
            fillOrderMaodalDialog();
    }
    
    public void newOrder() throws ParseException, IOException{
        
        NumberFormat numberFormat =  NumberFormat.getIntegerInstance();
        orders.addOrder( 
            new Person(name.getText(), 
                    new Address(contry.getText(), region.getText(), street.getText(), house.getText(), flat.getText()),
                        phoneNumber.getText()),
                        numberFormat.parse(disconte.getText()).byteValue(), StatusOfOrder.ГОТОВИТСЯ, orderManager.getOrderItems());
        mainController.writeOrder();
                    
        customersManager.addCustomer(
            new Person(name.getText(), 
                new Address(contry.getText(), region.getText(), street.getText(), house.getText(), flat.getText()),
                    phoneNumber.getText()));
        mainController.writeCustomers();
   
    }
    
    
    
    public void fillOrderMaodalDialog(){
        name.setText(this.editOrder.getContactPerson().getName());
        phoneNumber.setText(this.editOrder.getContactPerson().getPhoneNumber());
        
        contry.setText(this.editOrder.getContactPerson().getAdressToDelivery().getContry());
        region.setText(this.editOrder.getContactPerson().getAdressToDelivery().getRegion());
        street.setText(this.editOrder.getContactPerson().getAdressToDelivery().getStreet());
        house.setText(this.editOrder.getContactPerson().getAdressToDelivery().getHouse());
        flat.setText(this.editOrder.getContactPerson().getAdressToDelivery().getFlat());
        disconte.setText(this.editOrder.getDisconte().toString());
        
        orderManager.setOrderItems(this.editOrder.getOrderItems());
        orderItems = orderManager.getOrderItems();
        orderPositionTableModel.eventChangeItemsInOrderPositions(orderItems);
    }
    
    public void editOrder() throws ParseException, IOException{
        
        NumberFormat numberFormat =  NumberFormat.getIntegerInstance();
        orders.editOrder(editOrder.getOrderNumber(),  
                new Person(name.getText(), 
                    new Address(contry.getText(), region.getText(), street.getText(), house.getText(), flat.getText()),
                        phoneNumber.getText()),
                        numberFormat.parse(disconte.getText()).byteValue(), orderManager.getOrderItems()
        );
        mainController.writeOrder();
        
    }
}
