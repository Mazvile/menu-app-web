package com.mazvile.menuappweb.logic;

import com.mazvile.menuappweb.dao.RecipeDAO;
import com.mazvile.menuappweb.model.Product;
import com.mazvile.menuappweb.model.Recipe;
import com.mazvile.menuappweb.model.RecipeType;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RecipeService {

    @Autowired
    private RecipeDAO recipes;

    public List<Recipe> getRecipes() {
        return recipes.getAllRecipes();
    }

    public List<Recipe> getRecipeByType(RecipeType type) {
        return recipes.getAllRecipes()
                .stream()
                .filter(recipe -> recipe.getDishType().equals(type))
                .collect(Collectors.toList());

    }

    public boolean addRecipe(String name, String description, RecipeType type, List<Product> products) {
        if (!StringUtils.isBlank(name)) {
            Recipe recipe = Recipe.RecipeBuilder.aRecipe()
                    .withName(name)
                    .withDescription(description)
                    .withDishType(type)
                    .withProducts(products)
                    .build();
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
