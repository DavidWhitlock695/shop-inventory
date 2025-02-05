package com.shop.shop_inventory;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin
public class Controller implements SpringLayerControllerInterface {

    @Autowired
    private Service service;

    //Create
    @PostMapping("/shop-inventory/addItem/")
    public void addItem(@RequestBody String body){
        try {
            JSONObject json = new JSONObject(body);
            Item item = new Item();
            item.setName(json.getString("itemName"));
            item.setExpiry(new Date(json.getLong("expiryDateddmmyyyy")));
            item.setQuantity(json.getInt("quantity"));
            item.setPrice(json.getInt("priceInPence"));
            service.addItem(item);
        } catch (Exception e) {
            e.printStackTrace();
        }
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
    @PutMapping("/shop-inventory/updateItem/{id}")
    public void updateItem(@PathVariable("id") int id, @RequestBody String body){
        try {
            JSONObject json = new JSONObject(body);
            Item item = new Item(
                    id,
                    json.getString("itemName"),
                    new Date(json.getLong("expiryDateddmmyyyy")),
                    json.getInt("quantity"),
                    json.getInt("priceInPence"));
            service.addItem(item);
        } catch (Exception e) {
            e.printStackTrace();
        }
    };
    //Delete
    @DeleteMapping("/shop-inventory/deleteItem/{id}")
    public void deleteItemByID(@PathVariable("id") int id){
        service.deleteItemByID(id);
    };
}
