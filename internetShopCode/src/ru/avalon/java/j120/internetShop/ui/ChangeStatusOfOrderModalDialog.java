/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.avalon.java.j120.internetShop.ui;

import java.awt.Frame;
import java.io.IOException;
import javax.swing.JComboBox;
import ru.avalon.java.j120.internetShop.controllers.MainController;
import ru.avalon.java.j120.internetShop.controllers.StockItems;
import ru.avalon.java.j120.internetShop.models.Order;
import ru.avalon.java.j120.internetShop.models.StatusOfOrder;

/**
 *
 * @author eag
 */
public class ChangeStatusOfOrderModalDialog extends AbstractModalDialog{
    JComboBox statusOfOrder;
    MainController mainController;
    
    public ChangeStatusOfOrderModalDialog(Frame owner, String title, StatusOfOrder status, MainController mainController) {
        super(owner, title);
        this.mainController = mainController;
        
        setBounds(600, 500, 250, 100);
        statusOfOrder = new JComboBox(StatusOfOrder.values());
        statusOfOrder.setSelectedItem(status);
        add(statusOfOrder);
        
    }
    public StatusOfOrder getStatusCimbobox(){
        return (StatusOfOrder) statusOfOrder.getSelectedItem();
    }
    
    public void setStatusOrder(String numberOfOrder) throws IOException{
        mainController.getOrders().setStatusOfOrder(numberOfOrder, (StatusOfOrder) statusOfOrder.getSelectedItem(),  mainController);
        /*if(statusOfOrder.getSelectedItem() == StatusOfOrder.ОТГРУЖЕН){
            mainController.getStockItems().reduceTheStockBalance(mainController.getOrders().getOrder(numberOfOrder).getOrderItems());
           
        
        }*/
        
        mainController.writeOrder();
        
    }
}
