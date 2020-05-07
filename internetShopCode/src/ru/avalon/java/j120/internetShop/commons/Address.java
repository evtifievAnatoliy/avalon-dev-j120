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
    private int flat; // квартира

    public Address(String contry, String region, String street, String house, int flat) {
        this.contry = contry;
        this.region = region;
        this.street = street;
        this.house = house;
        this.flat = flat;
    }

    @Override
    public String toString() {
        return contry + ", " + region + ", " + street + ", дом " + house + ", квартира " + flat + ".";
    }
}
