/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.avalon.java.j120.internetShop.ui;

import java.awt.Frame;

/**
 *
 * @author eag
 */
public class ConfirmationModalDialog extends AbstractModalDialog{
    
    public ConfirmationModalDialog(Frame owner, String title) {
        super(owner, title);
      
        setBounds(600, 500, 350, 75);
    }
    
}
