/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.avalon.java.j120.internetShop.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.util.ArrayList;
import javafx.scene.control.SelectionMode;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import ru.avalon.java.j120.internetShop.controllers.MainController;
import ru.avalon.java.j120.internetShop.models.Item;

/**
 *
 * @author user
 */
public class AddNewItemToOrderModalDialog extends JDialog{
    
    private MainController mainController;
    private ArrayList<Item> items;
    private Item item;
    
    private JPanel controlPane;
    private JTable itemsTable;
    private boolean addPressed;
    
    
    ItemsTableModelWithOnlyReadRights itemsTableModel = new ItemsTableModelWithOnlyReadRights();
    
    public AddNewItemToOrderModalDialog(Frame owner, String title, ArrayList<Item> items, MainController mainController){
        super(owner, title, true);
        this.items = items;
        this.mainController = mainController;
        
        controlPane = new JPanel();
        controlPane.setLayout(new BoxLayout(controlPane, BoxLayout.Y_AXIS));
        add(controlPane, BorderLayout.CENTER);
        
        itemsTableModel.setStockItems(this.items);
        itemsTable = new JTable(itemsTableModel);
        itemsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        add(new JScrollPane(itemsTable));
        
      
        
        JPanel botton = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton btnAdd = new JButton("AddToOrder");
        botton.add(btnAdd);
        btnAdd.addActionListener(e ->{
            if (itemsTable.getSelectedRow() >= 0){
                item = items.get(itemsTable.getSelectedRow());
                addPressed = true;
                
                setVisible(false);
            }
            else{
                JOptionPane.showMessageDialog(this, 
                            "Пожалуйста, выберите товар. ",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
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

    public Item getItem() {
        return item;
    }
    
    
    
}
