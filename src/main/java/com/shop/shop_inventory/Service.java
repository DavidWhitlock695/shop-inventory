package com.shop.shop_inventory;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.util.*;

@org.springframework.stereotype.Service
public class Service implements SpringLayerInterface {
    @Autowired
    Repository repository;

    // Constructor with json parsing
    @PostConstruct
    public void init() {
        System.out.println("Service created");
        try {
            ObjectMapper mapper = new ObjectMapper();
            List<Item> myObjects = Arrays.asList(mapper.readValue(new File("src/main/resources/static/starterDatabase.json"), Item[].class));
            repository.saveAll(myObjects);
            System.out.println("Database created");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error reading initialisation data file.");
        }
    }

    //Create
    public void addItem(Item newItem){
        repository.save(newItem);
    };
    //Read
    public Item getItemByID(int id){
        return repository.findById(id).orElse(null);
    };
    public String getAllItems(){
        TableTransformer transformer = new TableTransformer(repository.findAll());
        return transformer.getTableHtmlString();
    };
    public ArrayList<Item> getItemsByPrice(int min, int max){
        return repository.findByPriceBetween(min, max);
    }
    public ArrayList<Item> getItemsByExpiry(Date earliest, Date latest){
        return repository.findByExpiryBetween(earliest,latest);
    };
    public ArrayList<Item> getItemsByName(String name){
        return repository.findByName(name);
    };
    public ArrayList<Item> getItemsByNameContaining(String name){
        return repository.findByNameContaining(name);
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
