/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.avalon.java.j120.internetShop.ui;

import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import ru.avalon.java.j120.internetShop.controllers.*;
import ru.avalon.java.j120.internetShop.controllers.interfaces.MainController;
import ru.avalon.java.j120.internetShop.models.Item;


/**
 *
 * @author eag
 */
public class ItemsModalDialog extends JDialog {
    
    private MainController mainController;
    private StockItems stockItems;
    private ArrayList<Item> items;
    
    
    private JPanel controlPane;
    private JTable itemsTable;
    private boolean addPressed;
    
    
    ItemsTableModel itemsTableModel = new ItemsTableModel();
    
    public ItemsModalDialog(Frame owner, String title, MainController mainController){
        super(owner, title, true);
        this.mainController = mainController;
        stockItems = mainController.getStockItems();
        this.items = stockItems.getItemsAsList();
        
        controlPane = new JPanel();
        controlPane.setLayout(new BoxLayout(controlPane, BoxLayout.Y_AXIS));
        add(controlPane, BorderLayout.CENTER);
        
        itemsTableModel.setStockItems(this.items);
        itemsTable = new JTable(itemsTableModel);
        add(new JScrollPane(itemsTable));
        itemsTableModel.addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent tme) {
                try{
                    mainController.writeItems();
                }
                catch(Exception ex){
                    JOptionPane.showMessageDialog(null, 
                            "Не удалось сохранить изменения",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });
      
        
        JPanel botton = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton btnAdd = new JButton("AddToStockItems...");
        botton.add(btnAdd);
        btnAdd.addActionListener(e ->{
            addPressed = true;
            AddNewItemModalDialog addNewItemModalDialog = new AddNewItemModalDialog(owner);
            addNewItemModalDialog.setVisible(true);
            
            if(addNewItemModalDialog.isSuccess()){
                try
                    { 
                    stockItems.addItem(addNewItemModalDialog.addNewItem());
                    itemsTableModel.eventAddNewItem(items);
                    mainController.writeItems();
                    JOptionPane.showMessageDialog(this, 
                            "Товар:\n" + addNewItemModalDialog.addNewItem().getName() + 
                                    "\nс артикулом \n" + addNewItemModalDialog.addNewItem().getArticle() + 
                                    "\nдобавлен на склад.",
                            "Добавление товара на склад",
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
        
        JButton btnCancel = new JButton("Cancel");
        botton.add(btnCancel);
        btnCancel.addActionListener(e ->{
            addPressed = false;
            setVisible(false);
        });
        
        add(botton, BorderLayout.SOUTH);
        pack();
    }
    
    protected JPanel getControlsPane(){
        return controlPane;
    }
    
    public boolean isSuccess(){
        return addPressed;
    }
    
    
    
}
