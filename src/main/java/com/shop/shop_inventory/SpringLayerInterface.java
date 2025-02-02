package com.shop.shop_inventory;

import java.util.Date;

public interface SpringLayerInterface {
    //Create
    public void addItem(Item item);
    //Read
    public String getItemByID(int id);
    public String getAllItems();
    public String getItemsByPrice(int min, int max);
    public String getItemsByExpiry(long earliest, long latest);
    public String getItemsByName(String name);
    public String getItemsByNameContaining(String name);
    //Update
    public void updateItem(Item item);
    //Delete
    public void deleteItemByID(int id);
}
