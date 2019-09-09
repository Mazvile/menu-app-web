package com.mazvile.menuappweb.service.recipe;

import com.mazvile.menuappweb.entity.Ingredient;
import com.mazvile.menuappweb.entity.Recipe;
import com.mazvile.menuappweb.model.RecipeType;
import com.mazvile.menuappweb.repository.RecipeRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RecipeServiceImpl implements RecipeService {

    private final RecipeRepository recipeRepository;

    public RecipeServiceImpl(final RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @Override
    public List<Recipe> getRecipeRepository() {
        return recipeRepository.findAll();
    }

    @Override
    public List<Recipe> getRecipeByType(final RecipeType type) {
        return recipeRepository.findAll()
                .stream()
                .filter(recipe -> recipe.getDishType().equals(type))
                .collect(Collectors.toList());

    }

    @Override
    public Recipe addRecipe(final String name, final String description,
                            final RecipeType type, final List<Ingredient> ingredients) {
        if (!StringUtils.isBlank(name)) {
            Recipe recipe = Recipe.RecipeBuilder.aRecipe()
                    .withName(name)
                    .withDescription(description)
                    .withDishType(type)
                    .withProducts(ingredients)
                    .build();
            return recipeRepository.save(recipe);
        }
        throw new Error("Name not valid");
    }

    @Override
    public Recipe addRecipe(final Recipe recipe) {
        if (!StringUtils.isBlank(recipe.getName())) {
            return recipeRepository.save(recipe);
        }
        throw new Error("Name not valid");
    }
}
