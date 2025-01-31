package com.shop.shop_inventory;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.Date;

public interface Repository extends JpaRepository<Item,Integer> {
    public ArrayList<Item> findByExpiryBetween(Date min, Date max);
    public ArrayList<Item> findByPriceBetween(int min, int max);
    public ArrayList<Item> findByName(String name);
    public ArrayList<Item> findByNameContaining(String name);
}
