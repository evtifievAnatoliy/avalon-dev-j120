/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.avalon.java.j120.internetShop.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.text.NumberFormat;
import java.text.ParseException;
import javax.swing.BoxLayout;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import ru.avalon.java.j120.internetShop.models.Item;

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
       
    private Item item;
    NumberFormat numberFormat;
    
    public AddNewItemModalDialog(Frame owner) {
        super(owner, "Новый товар");
        
        JPanel controlsPane = getControlsPane();
        
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        article = new JTextField(30);
        JLabel lbl = new JLabel("Артикул товара: ");
        lbl.setLabelFor(article);
        panel.add(lbl);
        panel.add(article);
        controlsPane.add(panel);
        
        panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        name = new JTextField(30);
        lbl = new JLabel("Название товара: ");
        lbl.setLabelFor(name);
        panel.add(lbl);
        panel.add(name);
        controlsPane.add(panel);
        
        panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        color = new JTextField(20);
        lbl = new JLabel("Цвет товара: ");
        lbl.setLabelFor(color);
        panel.add(lbl);
        panel.add(color);
        controlsPane.add(panel);
        
        panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        numberFormat = NumberFormat.getIntegerInstance();
        price = new JFormattedTextField(numberFormat);
        price.setColumns(10);
        lbl = new JLabel("Цена товара: ");
        lbl.setLabelFor(price);
        panel.add(lbl);
        panel.add(price);
        controlsPane.add(panel);

        panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        stockBalance = new JFormattedTextField(numberFormat);
        stockBalance.setColumns(10);
        lbl = new JLabel("Кол-во товара на складе: ");
        lbl.setLabelFor(stockBalance);
        panel.add(lbl);
        panel.add(stockBalance);
        controlsPane.add(panel);

        
        pack(); // сформировать правильные размеры окна
        
    }
    
    public Item addNewItem() throws ParseException{
        
        item = new Item(article.getText(), 
            name.getText(), 
            color.getText(), 
            numberFormat.parse(price.getText()).intValue(), 
            numberFormat.parse(stockBalance.getText()).intValue());
        
        return item;
        
    }

    
}
