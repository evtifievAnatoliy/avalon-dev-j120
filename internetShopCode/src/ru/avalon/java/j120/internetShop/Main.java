package ru.avalon.java.j120.internetShop;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import ru.avalon.java.j120.internetShop.controllers.*;
import ru.avalon.java.j120.internetShop.models.*;
import ru.avalon.java.j120.internetShop.commons.*;
import ru.avalon.java.j120.internetShop.configuration.Configuration;
import ru.avalon.java.j120.internetShop.ui.*;

import java.io.*;
import java.text.ParseException;
import java.util.Properties;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileView;







public class Main extends JFrame{

    /**
     * @param args the command line arguments
     */
        
    public static void main(String[] args) throws IOException, ClassNotFoundException, ParseException {
        
        
        MainForm mainForm = new MainForm();
        mainForm.setVisible(true);
        
        
        System.out.println("Приложение закончило свою работу.");
    }
     

   
}
