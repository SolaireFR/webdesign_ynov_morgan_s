package com.example.exercices.ex13.entities;

import org.springframework.context.annotation.Profile;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "products")
@Profile("ex13")
public class Product {
    @Id
    private String id;
    private String name;
    private Double price;
    private Integer stock;

    public Product() {
    }

    public Product(String name, Double price, Integer stock) {
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String toString() {
        return "Product{id='" + id + "', name='" + name + "', price=" + price + ", stock=" + stock + "}";
    } 
}