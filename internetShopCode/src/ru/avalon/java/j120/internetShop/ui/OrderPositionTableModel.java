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
import ru.avalon.java.j120.internetShop.controllers.StockItems;
import ru.avalon.java.j120.internetShop.models.*;

/**
 *
 * @author eag
 */
public class OrderPositionTableModel implements TableModel{
    
    private List<OrderPosition> orderItems;
    private final String[] columnNames = {"Артикул", "Название", "Цвет", "Цена за шт.", "Кол-во ед. в заказе", "Цена"};
    private final Class<?>[] columnTypes = {
        String.class,
        String.class,
        String.class,
        Integer.class,
        Integer.class,
        Integer.class,
        Integer.class
    };
    private List<TableModelListener> listeners = new ArrayList<>();
    
    
    
    public OrderPositionTableModel() {
        this.orderItems = new ArrayList<>();
        
    }
    
    @Override
    public int getRowCount() {
        return orderItems.size();
    }

    @Override
    public int getColumnCount() {
        return 6;
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
        OrderPosition orderPosition = orderItems.get(rowIndex);
        switch(columnIndex)
        {
            case 0: return orderPosition.getItem().getArticle();
            case 1: return orderPosition.getItem().getName();
            case 2: return orderPosition.getItem().getColor();
            case 3: return orderPosition.getItem().getPrice();
            case 4: return orderPosition.getNumberOfItems();
            case 5: return orderPosition.getAmountOfItems();
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
    
    public void setSelectedOrderPositions(ArrayList<OrderPosition> positions){
        this.orderItems = positions;
        TableModelEvent e = new TableModelEvent(this); 
        for (TableModelListener l: listeners)
            l.tableChanged(e);
    }
    
}
