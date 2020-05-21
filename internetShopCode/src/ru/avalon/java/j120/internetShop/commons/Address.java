/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.avalon.java.j120.internetShop.commons;

import java.io.Serializable;

/**
 *
 * @author eag
 */
// класс адрес
public class Address implements Serializable{
    
    private String contry; // страна
    private String region; // регион
    private String street; // улица
    private String house; // дом
    private String flat; // квартира

    public Address(String contry, String region, String street, String house, String flat) {
        if(contry.isEmpty() || region.isEmpty() || street.isEmpty() || house.isEmpty())
            throw new IllegalArgumentException("Адрес доставки заполнен не полностью. \nНеобходимо обязательно заполнить Страну, Регион, Улицу и Дом.");
        this.contry = contry;
        this.region = region;
        this.street = street;
        this.house = house;
        this.flat = flat;
    }

    public String getContry() {
        return contry;
    }

    public String getRegion() {
        return region;
    }

    public String getStreet() {
        return street;
    }

    public String getHouse() {
        return house;
    }

    public String getFlat() {
        return flat;
    }
    
    
    @Override
    public String toString() {
        return contry + ", " + region + ", " + street + ", дом " + house + ", квартира " + flat + ".";
    }
}
