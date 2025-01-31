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
    private Currency currency;
    private int price;
    public Item(){};

    //Methods
    public Item(String name, Date expiry, int quantity, Currency currency, int price) {
        this.name = name;
        this.expiry = expiry;
        this.quantity = quantity;
        this.currency = currency;
        this.price = price;
    }
}
