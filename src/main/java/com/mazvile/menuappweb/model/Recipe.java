package com.mazvile.menuappweb.model;

import java.util.List;

public class Recipe {

    private Integer id;
    private String name;
    private String description;
    private RecipeType dishType;
    private List<Product> products;
    

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


    public static final class RecipeBuilder {
        private Integer id;
        private String name;
        private String description;
        private RecipeType dishType;
        private List<Product> products;

        private RecipeBuilder() {
        }

        public static RecipeBuilder aRecipe() {
            return new RecipeBuilder();
        }

        public RecipeBuilder withId(Integer id) {
            this.id = id;
            return this;
        }

        public RecipeBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public RecipeBuilder withDescription(String description) {
            this.description = description;
            return this;
        }

        public RecipeBuilder withDishType(RecipeType dishType) {
            this.dishType = dishType;
            return this;
        }

        public RecipeBuilder withProducts(List<Product> products) {
            this.products = products;
            return this;
        }

        public Recipe build() {
            Recipe recipe = new Recipe();
            recipe.setId(id);
            recipe.setDescription(description);
            recipe.products = this.products;
            recipe.dishType = this.dishType;
            recipe.name = this.name;
            return recipe;
        }
    }
}
