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
import ru.avalon.java.j120.internetShop.models.*;

/**
 *
 * @author eag
 */
public class ItemsTableModel implements TableModel{
    private List<Item> items;
    private final String[] columnNames = {"Артикул", "Название", "Цвет", "Цена", "Остаток на складе"};
    private final Class<?>[] columnTypes = {
        String.class,
        String.class,
        String.class,
        Integer.class,
        Integer.class
    };
    private List<TableModelListener> listeners = new ArrayList<>();
    
    
    
    public ItemsTableModel() {
        this.items = new ArrayList<>();
        
    }
    
    @Override
    public int getRowCount() {
        return items.size();
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
        Item item = items.get(rowIndex);
        switch(columnIndex)
        {
            case 0: return item.getArticle();
            case 1: return item.getName();
            case 2: return item.getColor();
            case 3: return item.getPrice();
            case 4: return item.getStockBalance();
            default: 
                throw new Error ("Unreachable place.");
        }
               
    }
                
    @Override // метод разрешающий редактирования ячеек
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
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
    
    public void setStockItems(ArrayList<Item> items){
        this.items = items;
        TableModelEvent e = new TableModelEvent(this); 
        for (TableModelListener l: listeners)
            l.tableChanged(e);
    }
    
    public void eventAddNewItemEvent(ArrayList<Item> items){
        this.items = items;
        TableModelEvent e = new TableModelEvent(this); 
        for (TableModelListener l: listeners)
            l.tableChanged(e);
    }
    
}
