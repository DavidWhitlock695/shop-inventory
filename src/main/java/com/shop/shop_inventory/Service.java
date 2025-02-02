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
            e.printStackTrace();
            System.out.println("Error reading initialisation data file.");
        }
    }

    //Create
    public void addItem(Item newItem){
        repository.save(newItem);
    };
    //Read
    public String getItemByID(int id){
        //A bit crude, but a workaround for only returning one item here but still an array
        List<Item> items = new ArrayList<>();
        items.add(repository.findById(id).orElse(null));
        return this.tableTransformer.getTableHtmlString(items);
    };
    public String getAllItems(){
        return this.tableTransformer.getTableHtmlString(repository.findAll());
    };
    public String getItemsByPrice(int min, int max){
        //Convert to Pence before querying database
        return this.tableTransformer.getTableHtmlString(repository.findByPriceBetween(min * 100, max * 100));
    }
    public String getItemsByExpiry(long earliest, long latest){
        return this.tableTransformer.getTableHtmlString(repository.findByExpiryBetween(new Date(earliest),new Date(latest)));
    };
    public String getItemsByName(String name){
        return this.tableTransformer.getTableHtmlString(repository.findByName(name));
    };
    public String getItemsByNameContaining(String name){
        return this.tableTransformer.getTableHtmlString(repository.findByNameContaining(name));
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
