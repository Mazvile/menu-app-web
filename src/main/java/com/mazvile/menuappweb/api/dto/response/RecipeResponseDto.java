package com.mazvile.menuappweb.api.dto.response;

import com.mazvile.menuappweb.model.enums.RecipeType;

import java.util.Set;

public class RecipeResponseDto {

    private String name;

    private String description;

    private RecipeType dishType;

    private Set<ProductResponseDto> ingredients;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public RecipeType getDishType() {
        return dishType;
    }

    public void setDishType(RecipeType dishType) {
        this.dishType = dishType;
    }

    public Set<ProductResponseDto> getIngredients() {
        return ingredients;
    }

    public void setIngredients(Set<ProductResponseDto> ingredients) {
        this.ingredients = ingredients;
    }



}
