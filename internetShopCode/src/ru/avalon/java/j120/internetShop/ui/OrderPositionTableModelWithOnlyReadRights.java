/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.avalon.java.j120.internetShop.ui;

/**
 *
 * @author eag
 */
public class OrderPositionTableModelWithOnlyReadRights extends OrderPositionTableModel{
    
    @Override // метод разрешающий редактирования ячеек
    public boolean isCellEditable(int rowIndex, int columnIndex) {
         return false;
                
    }
    
}
