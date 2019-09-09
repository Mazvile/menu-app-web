package com.mazvile.menuappweb.service.recipe;

import com.mazvile.menuappweb.entity.Product;
import com.mazvile.menuappweb.entity.Recipe;
import com.mazvile.menuappweb.model.RecipeType;

import java.util.List;

public interface RecipeService {

    List<Recipe> getRecipeRepository();

    List<Recipe> getRecipeByType(RecipeType type);

    boolean addRecipe(String name, String description, RecipeType type, List<Product> products);

    boolean addRecipe(Recipe recipe);
}
