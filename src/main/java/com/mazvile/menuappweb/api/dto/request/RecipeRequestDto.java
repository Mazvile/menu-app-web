package com.mazvile.menuappweb.api.dto.request;

import com.mazvile.menuappweb.api.dto.IngredientDto;
import com.mazvile.menuappweb.model.enums.RecipeType;

import java.util.Set;

public class RecipeRequestDto {
    private String name;

    private String description;

    private RecipeType dishType;

    private Set<IngredientDto> ingredients;

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public RecipeType getDishType() {
        return dishType;
    }

    public Set<IngredientDto> getIngredients() {
        return ingredients;
    }
}
