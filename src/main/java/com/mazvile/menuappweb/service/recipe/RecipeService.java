package com.mazvile.menuappweb.service.recipe;

import com.mazvile.menuappweb.model.enums.RecipeType;
import com.mazvile.menuappweb.repository.entity.Ingredient;
import com.mazvile.menuappweb.repository.entity.Recipe;

import java.util.List;
import java.util.Set;

public interface RecipeService {

    List<Recipe> getRecipeRepository();

    List<Recipe> getRecipeByType(RecipeType type);

    Recipe addRecipe(String name, String description, RecipeType type, Set<Ingredient> ingredients);

    Recipe addRecipe(Recipe recipe);
}
