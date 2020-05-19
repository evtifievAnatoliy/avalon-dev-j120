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
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import ru.avalon.java.j120.internetShop.controllers.MainController;
import ru.avalon.java.j120.internetShop.commons.*;

/**
 *
 * @author eag
 */
public class CustomersModalDialog extends JDialog{
    MainController mainController;
    private ArrayList<Person> customers;
    
    
    private JPanel controlPane;
    private JTable customersTable;
    
    
    CustomersTableModel customersTableModel = new CustomersTableModel();
    
    public CustomersModalDialog(Frame owner, String title, ArrayList<Person> customers, MainController mainController){
        super(owner, title, true);
        this.customers = customers;
        this.mainController = mainController;
        
        controlPane = new JPanel();
        controlPane.setLayout(new BoxLayout(controlPane, BoxLayout.Y_AXIS));
        add(controlPane, BorderLayout.CENTER);
        
        customersTableModel.setCustomers(this.customers);
        customersTable = new JTable(customersTableModel);
        add(new JScrollPane(customersTable));
        
      
        
        JPanel botton = new JPanel(new FlowLayout(FlowLayout.CENTER));
        
        JButton btnCancel = new JButton("Cancel");
        botton.add(btnCancel);
        btnCancel.addActionListener(e ->{
            setVisible(false);
        });
        
        add(botton, BorderLayout.SOUTH);
        pack();
    }
    
    protected JPanel getControlsPane(){
        return controlPane;
    }
}
