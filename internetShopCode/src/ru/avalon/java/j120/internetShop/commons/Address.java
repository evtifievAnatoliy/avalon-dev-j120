/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.avalon.java.j120.internetShop.commons;

/**
 *
 * @author eag
 */
public class Address {
    
    private String contry;
    private String region;
    private String street;
    private String house;
    private int flat;

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
