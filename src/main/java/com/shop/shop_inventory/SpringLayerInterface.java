package com.shop.shop_inventory;

import java.util.ArrayList;
import java.util.Date;

public interface SpringLayerInterface {
    //Create
    public void addItem(Item item);
    //Read
    public Item getItemByID(int id);
    public ArrayList<Item> getAllItems();
    public ArrayList<Item> getItemsByPrice(int min, int max);
    public ArrayList<Item> getItemsByExpiry(Date earliest, Date latest);
    public ArrayList<Item> getItemsByName(String name);
    //Update
    public void updateItem(Item item);
    //Delete
    public void deleteItemByID(int id);
}
