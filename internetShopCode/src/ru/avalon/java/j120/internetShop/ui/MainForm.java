/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.avalon.java.j120.internetShop.ui;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;

/**
 *
 * @author user
 */
public class MainForm extends JFrame{
    
    private JList<String> list;
    private JTextArea content;
    private File[] files;
    
    public MainForm() {
        setBounds(300, 200, 900, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        list = new JList<>();
        list.addListSelectionListener(p->fileChousen(p.getFirstIndex()));// лямбда разобраться
        content = new JTextArea();
        content.setEditable(false);// запрет на редактирование
        
        JSplitPane sp = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
            new JScrollPane(list),  // добавлена прокрутка
            new JScrollPane(content));
        sp.setDividerLocation(300);
        add(sp);
        
        gotoDir(new File(System.getProperty("user.dir")));
    }
    public void fileChousen(int indx){
        if (files[indx].isDirectory()){
            // clean up content
            content.setText("");
                    
            return;
        }
        try(BufferedReader br = new BufferedReader(new FileReader(files[indx]))){
            StringBuilder sb = new StringBuilder();
            char[] buf = new char [4_896];
            int n;
            while((n = br.read(buf)) != -1){
                sb.append(buf, 0 , n);
            }
            content.setText(sb.toString());
            content.setCaretPosition(0);// поднимает скрол наверх
        } 
        catch (IOException e) {
            content.setText("Error reading file" + files[indx].getAbsolutePath());
        }
        
        
    }
    
    public void gotoDir(File path){
        
        setTitle(path.getAbsolutePath());
        
        files = path.listFiles();
        
        Arrays.sort(files, MainForm::compareFiles); //реализован ананимный класс только для одного метода в интерфейсе (референс)
            
        String[] fileNames = new String[files.length];
        for (int i =0; i<files.length; i++){
            fileNames[i]= files[i].getName();
        }
        
        list.setListData(fileNames);
        
    } 
    private static int compareFiles(File f1, File f2){
        if(f1.isDirectory() && !f2.isDirectory())
            return -1;
        if(!f1.isDirectory() && f2.isDirectory())
            return 1;
        else
            return f1.getName().compareTo(f2.getName());
        }
    
}
