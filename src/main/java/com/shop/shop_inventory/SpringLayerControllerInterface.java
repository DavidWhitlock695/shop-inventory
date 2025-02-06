package com.shop.shop_inventory;

import java.util.List;
import java.util.UUID;

public interface SpringLayerControllerInterface {
    //Create
    public void addItem(String json);
    //Read
    public List<Item> getItemByID(UUID id);
    public List<Item> getAllItems();
    public List<Item> getItemsByPrice(int min, int max);
    public List<Item> getItemsByExpiry(long earliest, long latest);
    public List<Item> getItemsByName(String name);
    public List<Item> getItemsByNameContaining(String name);
    //Update
    public void updateItem(UUID id, String body);
    //Delete
    public void deleteItemByID(UUID id);
}
