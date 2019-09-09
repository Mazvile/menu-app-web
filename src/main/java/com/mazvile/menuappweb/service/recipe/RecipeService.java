package com.mazvile.menuappweb.service.recipe;

import com.mazvile.menuappweb.entity.Ingredient;
import com.mazvile.menuappweb.entity.Recipe;
import com.mazvile.menuappweb.model.RecipeType;

import java.util.List;

public interface RecipeService {

    List<Recipe> getRecipeRepository();

    List<Recipe> getRecipeByType(RecipeType type);

    Recipe addRecipe(String name, String description, RecipeType type, List<Ingredient> ingredients);

    Recipe addRecipe(Recipe recipe);
}
