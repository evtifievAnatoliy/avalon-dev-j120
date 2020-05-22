/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.avalon.java.j120.internetShop.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Frame;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author eag
 */
public class AbstractModalDialog extends JDialog{
    private JPanel controlPane;
    private boolean okPressed;
    
    public AbstractModalDialog(Frame owner, String title){
        super(owner, title, true);
        
        controlPane = new JPanel();
        controlPane.setLayout(new BoxLayout(controlPane, BoxLayout.Y_AXIS));
        add(controlPane, BorderLayout.CENTER);
        
        JPanel botton = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton btnOk = new JButton("OK");
        botton.add(btnOk);
        btnOk.addActionListener(e ->{
            okPressed = true;
            setVisible(false);
        });
        
        JButton btnCancel = new JButton("Cancel");
        botton.add(btnCancel);
        btnCancel.addActionListener(e ->{
            okPressed = false;
            setVisible(false);
        });
        
        add(botton, BorderLayout.SOUTH);

    }
    
    protected JPanel getControlsPane(){
        return controlPane;
    }
    
    public boolean isSuccess(){
        return okPressed;
    }
}
