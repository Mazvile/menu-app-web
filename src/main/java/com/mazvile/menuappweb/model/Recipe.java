package com.mazvile.menuappweb.model;

import java.util.List;

public class Recipe {

    private Integer id;
    private String name;
    private String description;
    private RecipeType dishType;
    private List<Product> products;

    public Recipe(String name, RecipeType type, List<Product> products) {
        this.name = name;
        this.dishType = type;
        this.products = products;
    }

    public Recipe(Integer id, String name, String description, RecipeType type, List<Product> products) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.dishType = type;
        this.products = products;
    }

    public RecipeType getDishType() {
        return dishType;
    }

    public String getName() {
        return name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Product> getProducts() {
        return products;
    }
}
