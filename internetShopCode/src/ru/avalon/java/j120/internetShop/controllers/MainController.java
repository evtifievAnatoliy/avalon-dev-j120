/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.avalon.java.j120.internetShop.controllers;

import java.util.ArrayList;
import java.util.Scanner;
import ru.avalon.java.j120.internetShop.models.Item;

/**
 *
 * @author user
 */
// Класс который инициализирует заказы и товары
// Считывает коллекции из файлов и записывает их в файл

public class MainController {
    
    ArrayList<Item> items;
    String itemsPath;

    ItemsReaderWriter itemsReaderWriter = new ItemsReaderWriter();
    
    public MainController(String itemsPath) {
        this.itemsPath = itemsPath;
        this.items = itemsReaderWriter.readItems(itemsPath);
    }
    
    // метод добавление нового товара в коллекцию товаров
    public void addItem(){
        
        String article; // артикул товара
        String name; // название
        String color; //цвет
        int price; // цена
        int stockBalance; //остаток на складе
        
        Scanner in = new Scanner(System.in, "Cp866");
        System.out.println("Добавление нового товара");
        System.out.println("Введите артикул товара: ");
        article = in.nextLine();
        System.out.println("Введите название товара: ");
        name = in.nextLine();
        System.out.println("Введите цвет товара: ");
        color = in.nextLine();
        System.out.println("Введите цену товара: ");
        price = in.nextInt();
        System.out.println("Введите остаток товара на складе: ");
        stockBalance = in.nextInt();
        in.close();
        
        try{
            Item item = new Item (new String(article.getBytes(), "UTF-8"), new String(name.getBytes(), "UTF-8"), new String(color.getBytes(), "UTF-8"), price, stockBalance);
            items.add(item);
            System.out.println("Товар: " + item.toString() + " добавлен.");
        }
        catch(Exception ex){
            System.out.println("Error. Не удалось прочитать товар с артикулом: " + article + ". Exeption: " + ex.getMessage());
            
        }
        
    }
    
    public void writeItems(){
        itemsReaderWriter.writeItems(itemsPath, items);
    }
    
}
