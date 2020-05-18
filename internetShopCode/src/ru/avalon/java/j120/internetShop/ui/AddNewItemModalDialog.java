/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.avalon.java.j120.internetShop.ui;

import java.awt.FlowLayout;
import java.awt.Frame;
import java.text.NumberFormat;
import javax.swing.BoxLayout;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author eag
 */
public class AddNewItemModalDialog extends AbstractModalDialog{
    
    private JTextField article;
    private JTextField name;
    private JTextField color;  
    private JTextField price;
    private JTextField stockBalance;
       
    
    public AddNewItemModalDialog(Frame owner) {
        super(owner, "Новый товар");
        
        JPanel controlsPane = getControlsPane();
        
        JPanel panel = new JPanel();
        article = new JTextField(30);
        JLabel lbl = new JLabel("Артикул товара: ");
        lbl.setLabelFor(article);
        panel.add(lbl);
        panel.add(article);
        controlsPane.add(panel);
        
        panel = new JPanel();
        name = new JTextField(30);
        lbl = new JLabel("Название товара: ");
        lbl.setLabelFor(name);
        panel.add(lbl);
        panel.add(name);
        controlsPane.add(panel);
        
        panel = new JPanel();
        color = new JTextField(30);
        lbl = new JLabel("Цвет товара: ");
        lbl.setLabelFor(color);
        panel.add(lbl);
        panel.add(color);
        controlsPane.add(panel);
        
        panel = new JPanel();
        price = new JFormattedTextField(NumberFormat.getIntegerInstance());
        price.setColumns(20);
        lbl = new JLabel("Цена товара: ");
        lbl.setLabelFor(price);
        panel.add(lbl);
        panel.add(price);
        controlsPane.add(panel);

        panel = new JPanel();
        stockBalance = new JFormattedTextField(NumberFormat.getIntegerInstance());
        stockBalance.setColumns(20);
        lbl = new JLabel("Цена товара: ");
        lbl.setLabelFor(stockBalance);
        panel.add(lbl);
        panel.add(stockBalance);
        controlsPane.add(panel);

        
        pack(); // сформировать правильные размеры окна
        
    }

    
}
