package com.mazvile.menuappweb.api.dto.response;

import com.mazvile.menuappweb.model.enums.RecipeType;

import java.util.Set;

public final class RecipeResponseDtoBuilder {
    private String name;
    private String description;
    private RecipeType dishType;
    private Set<ProductResponseDto> ingredients;

    private RecipeResponseDtoBuilder() {
    }

    public static RecipeResponseDtoBuilder aRecipeResponseDto() {
        return new RecipeResponseDtoBuilder();
    }

    public RecipeResponseDtoBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public RecipeResponseDtoBuilder withDescription(String description) {
        this.description = description;
        return this;
    }

    public RecipeResponseDtoBuilder withDishType(RecipeType dishType) {
        this.dishType = dishType;
        return this;
    }

    public RecipeResponseDtoBuilder withIngredients(Set<ProductResponseDto> ingredients) {
        this.ingredients = ingredients;
        return this;
    }

    public RecipeResponseDto build() {
        RecipeResponseDto recipeResponseDto = new RecipeResponseDto();
        recipeResponseDto.setName(name);
        recipeResponseDto.setDescription(description);
        recipeResponseDto.setDishType(dishType);
        recipeResponseDto.setIngredients(ingredients);
        return recipeResponseDto;
    }

}
