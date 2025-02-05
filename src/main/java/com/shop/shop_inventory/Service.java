package com.shop.shop_inventory;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.util.*;

@org.springframework.stereotype.Service
public class Service implements SpringLayerServiceInterface {
    @Autowired
    Repository repository;
    TableTransformer tableTransformer;

    // Constructor with json parsing
    @PostConstruct
    public void init() {
        this.tableTransformer = new TableTransformer();
        System.out.println("Service created");
        try {
            ObjectMapper mapper = new ObjectMapper();
            List<Item> myObjects = Arrays.asList(mapper.readValue(new File("src/main/resources/static/starterDatabase.json"), Item[].class));
            repository.saveAll(myObjects);
            System.out.println("Database created");
        } catch (Exception e) {
            System.out.println("Error reading initialisation data file.");
        }
    }

    //Create
    public void addItem(Item newItem){
        System.out.println(newItem.getName());
        repository.save(newItem);
    };
    //Read
    public List<Item> getItemByID(int id){
        //A bit crude, but a workaround for only returning one item here but still an array
        List<Item> items = new ArrayList<>();
        items.add(repository.findById(id).orElse(null));
        return items;
    };
    public List<Item> getAllItems(){
        return this.repository.findAll();
    };
    public List<Item> getItemsByPrice(int min, int max){
        //Convert to Pence before querying database
        return this.repository.findByPriceBetween(min * 100, max * 100);
    }
    public List<Item> getItemsByExpiry(long earliest, long latest){
        return this.repository.findByExpiryBetween(new Date(earliest),new Date(latest));
    };
    public List<Item> getItemsByName(String name){
        return this.repository.findByName(name);
    };
    public List<Item> getItemsByNameContaining(String name){
        return this.repository.findByNameContaining(name);
    }
    //Update
    public void updateItem(Item item){
        repository.save(item);
    };
    //Delete
    public void deleteItemByID(int id){
        repository.deleteById(id);
    };
}
