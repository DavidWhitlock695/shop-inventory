package com.shop.shop_inventory;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.springframework.stereotype.Component;

import java.util.Currency;
import java.util.Date;

@Component
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    //Fields
    public int id;
    private String name;
    private Date expiry;
    private int quantity;
    private String currencySymbol;
    private int price;
    public Item(){};

    //Methods
    public Item(String name, Date expiry, int quantity, String currencySymbol, int price) {
        this.name = name;
        this.expiry = expiry;
        this.quantity = quantity;
        this.currencySymbol = currencySymbol;
        this.price = price;
    }
    @Override
    public String toString(){
        try {
            return "Item=[" + this.name + " x" + this.quantity + ", price: " + this.currencySymbol + this.price + ", Expiry: " + this.expiry.toString() + "]";
        }
        catch (Exception e) {
            return "Incomplete Item";
        }
    }
    //Getters
    public int getId() {
        return this.id;
    }
    public String getName() {
        return this.name;
    }
    public Date getExpiry() {
        return this.expiry;
    }
    public int getQuantity() {
        return this.quantity;
    }
    public String getCurrency() {
        return this.currencySymbol;
    }
    public int getPrice() {
        return this.price;
    }
}
