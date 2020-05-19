/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.avalon.java.j120.internetShop.ui;

import java.util.ArrayList;
import java.util.List;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import ru.avalon.java.j120.internetShop.commons.*;

public class CustomersTableModel implements TableModel{
    private List<Person> customers;
    private final String[] columnNames = {"Имя", "Адрес доставки", "Номер телефона"};
    private final Class<?>[] columnTypes = {
        String.class,
        String.class,
        String.class
    };
    private List<TableModelListener> listeners = new ArrayList<>();
    
    
    
    public CustomersTableModel() {
        this.customers = new ArrayList<>();
        
    }
    
    @Override
    public int getRowCount() {
        return customers.size();
    }

    @Override
    public int getColumnCount() {
        return columnTypes.length;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return columnNames[columnIndex];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return columnTypes[columnIndex];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Person person = customers.get(rowIndex);
        switch(columnIndex)
        {
            case 0: return person.getName();
            case 1: return person.getAdressToDelivery().toString();
            case 2: return person.getPhoneNumber();
            default: 
                throw new Error ("Unreachable place.");
        }
               
    }
                
    @Override // метод разрешающий редактирования ячеек
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        switch(columnIndex)
        {
            case 0: return false;
            case 1: return false;
            case 2: return false;
            default: 
                throw new Error ("Unreachable place.");
        }
        
        
    }

    @Override // метод записи редактируемой ячейки
    public void setValueAt(Object o, int rowIndex, int columnIndex) {
      
    }
    
    @Override
    public void addTableModelListener(TableModelListener tl) {
        listeners.add(tl);
    }
    
    @Override
    public void removeTableModelListener(TableModelListener tl) {
        listeners.remove(tl);
    }
    
    public void setCustomers(ArrayList<Person> customers){
        this.customers = customers;
        TableModelEvent e = new TableModelEvent(this); 
        for (TableModelListener l: listeners)
            l.tableChanged(e);
    }
    
    
    public void eventChangePerson(){
        TableModelEvent e = new TableModelEvent(this); 
        for (TableModelListener l: listeners)
            l.tableChanged(e);
    }
    
}
