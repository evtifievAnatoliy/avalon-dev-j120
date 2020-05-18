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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import ru.avalon.java.j120.internetShop.models.Item;

/**
 *
 * @author eag
 */
public class ItemsModalDialog extends JDialog {
    
    private ArrayList<Item> items;
    
    private JPanel controlPane;
    private JTable itemsTable;
    private boolean addPressed;
    
    ItemsTableModel itemsTableModel = new ItemsTableModel();
    
    public ItemsModalDialog(Frame owner, String title, ArrayList<Item> items){
        super(owner, title, true);
        this.items = items;
        
        controlPane = new JPanel();
        controlPane.setLayout(new BoxLayout(controlPane, BoxLayout.Y_AXIS));
        add(controlPane, BorderLayout.CENTER);
        
        itemsTableModel.setStockItems(this.items);
        itemsTable = new JTable(itemsTableModel);
        add(new JScrollPane(itemsTable));
        
        JPanel botton = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton btnAdd = new JButton("Add...");
        botton.add(btnAdd);
        btnAdd.addActionListener(e ->{
            addPressed = true;
            AddNewItemModalDialog addNewItemModalDialog = new AddNewItemModalDialog(owner);
            addNewItemModalDialog.setVisible(true);
            
            setVisible(false);
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
