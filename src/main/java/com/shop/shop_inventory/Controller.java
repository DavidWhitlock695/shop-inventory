package com.shop.shop_inventory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
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
    public List<Item> getAllItems(){
        return service.getAllItems();
    };
    @GetMapping("/shop-inventory/getItem/{id}")
    public List<Item> getItemByID(@PathVariable("id") int id){
        return service.getItemByID(id);
    };
    @GetMapping("/shop-inventory/getItemsByPrice/min{min}/max{max}")
    public List<Item> getItemsByPrice(@PathVariable("min") int min, @PathVariable("max") int max){
        return service.getItemsByPrice(min, max);
    };
    @GetMapping("/shop-inventory/getItemsByExpiry/min{min}/max{max}")
    public List<Item> getItemsByExpiry(@PathVariable("min") long earliest, @PathVariable("max") long latest){
        return service.getItemsByExpiry(earliest, latest);
    };
    @GetMapping("/shop-inventory/getItemsByName/{name}")
    public List<Item> getItemsByName(@PathVariable("name") String name){
        return service.getItemsByName(name);
    };
    @GetMapping("/shop-inventory/getItemsByNameContaining/{name}")
    public List<Item> getItemsByNameContaining(@PathVariable("name") String name){
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
