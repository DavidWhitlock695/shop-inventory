package com.shop.shop_inventory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;

@RestController
public class Controller implements SpringLayerInterface {

    @Autowired
    private Service service;

    //Create
    @PostMapping("/shop-inventory/addItem")
    public void addItem(@RequestBody Item newItem){
        service.addItem(newItem);
    };
    //Read
    @GetMapping("/shop-inventory/")
    public String getHomePage(){
        return service.getAllItems().toString();
    };
    @GetMapping("/shop-inventory/getItem/{id}")
    public Item getItemByID(@PathVariable("id") int id){
        return service.getItemByID(id);
    };
    @GetMapping("/shop-inventory/getAllItems")
    public ArrayList<Item> getAllItems(){
        return service.getAllItems();
    };
    @GetMapping("/shop-inventory/getItemsByPrice/min{min}/max{max}")
    public ArrayList<Item> getItemsByPrice(@PathVariable("min") int min, @PathVariable("max") int max){
        return service.getItemsByPrice(min, max);
    };
    @GetMapping("/shop-inventory/getItemsByExpiry/earliest{earliest}/latest{latest}")
    public ArrayList<Item> getItemsByExpiry(@PathVariable("earliest") Date earliest, @PathVariable("latest") Date latest){
        return service.getItemsByExpiry(earliest, latest);
    };
    @GetMapping("/shop-inventory/getItemsByName/{name}")
    public ArrayList<Item> getItemsByName(@PathVariable("name") String name){
        return service.getItemsByName(name);
    };
    @GetMapping("/shop-inventory/getItemsByNameContaining/{name}")
    public ArrayList<Item> getItemsByNameContaining(@PathVariable("name") String name){
        return service.getItemsByNameContaining(name);
    };
    //Update
    @PutMapping("/shop-inventory/updateItem")
    public void updateItem(@RequestBody Item item){
        service.updateItem(item);
    };
    //Delete
    @DeleteMapping("/shop-inventory/deleteItem/{id}")
    public void deleteItemByID(@PathVariable("id") int id){
        service.deleteItemByID(id);
    };
}
