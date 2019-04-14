package com.mazvile.menuappweb.logic;

import com.mazvile.menuappweb.dao.RecipeDAO;
import com.mazvile.menuappweb.model.Product;
import com.mazvile.menuappweb.model.Recipe;
import com.mazvile.menuappweb.model.RecipeType;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RecipeService {

    @Autowired
    private RecipeDAO recipes;

    public List<Recipe> getRecipes() {
        return recipes.getAllRecipes();
    }

    public List<Recipe> getRecipeByType(RecipeType type) {
        List<Recipe> filteredRecipes = new ArrayList<>();
        for (Recipe recipe : recipes.getAllRecipes()) {
            if (recipe.getDishType().equals(type)) {
                filteredRecipes.add(recipe);
            }
        }
        return filteredRecipes;
    }

    public boolean addRecipe(String name, String description, RecipeType type, List<Product> products) {
        if (!StringUtils.isBlank(name)) {
            Recipe recipe = new Recipe(name, description, type, products);
            recipes.addRecipe(recipe);
            return true;
        }
        return false;
    }

    public boolean addRecipe(Recipe recipe) {
        if (!StringUtils.isBlank(recipe.getName())) {
            recipes.addRecipe(recipe);
            return true;
        }
        return false;
    }
}
