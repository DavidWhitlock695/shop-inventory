package com.shop.shop_inventory;

import java.util.List;

public interface SpringLayerControllerInterface {
    //Create
    public void addItem(String json);
    //Read
    public List<Item> getItemByID(int id);
    public List<Item> getAllItems();
    public List<Item> getItemsByPrice(int min, int max);
    public List<Item> getItemsByExpiry(long earliest, long latest);
    public List<Item> getItemsByName(String name);
    public List<Item> getItemsByNameContaining(String name);
    //Update
    public void updateItem(Item item);
    //Delete
    public void deleteItemByID(int id);
}
