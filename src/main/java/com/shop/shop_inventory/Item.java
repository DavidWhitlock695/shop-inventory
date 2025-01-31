package com.shop.shop_inventory;

import java.util.Currency;
import java.util.Date;

public abstract class Item {
    //Fields
    private int id;
    private String name;
    private Date expiry;
    private int quantity;
    private Currency currency;
    private int price;
}
