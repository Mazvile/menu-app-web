package com.mazvile.menuappweb.logic;


import com.mazvile.menuappweb.model.Menu;
import com.mazvile.menuappweb.model.Recipe;
import com.mazvile.menuappweb.model.RecipeType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
public class MenuGenerator {

    @Autowired
    private RecipeBook recipeBook;

    @Autowired
    private Supplies supplies;

    public Menu makeRandomMenu(
            int numberOfFishDishes,
            int numberOfMeatDishes,
            int numberOfPoultryDishes,
            int numberOfVeggieDishes) {
        List<Recipe> selectedFishDishes = getRandomDishes(RecipeType.FISH, numberOfFishDishes);
        List<Recipe> selectedMeatDishes = getRandomDishes(RecipeType.MEAT, numberOfMeatDishes);
        List<Recipe> selectedPoultryDishes = getRandomDishes(RecipeType.POULTRY, numberOfPoultryDishes);
        List<Recipe> selectedVeggieDishes = getRandomDishes(RecipeType.VEGETARIAN, numberOfVeggieDishes);

        List<Recipe> allMenuRecipes = new ArrayList<>();
        allMenuRecipes.addAll(selectedFishDishes);
        allMenuRecipes.addAll(selectedMeatDishes);
        allMenuRecipes.addAll(selectedPoultryDishes);
        allMenuRecipes.addAll(selectedVeggieDishes);

        return new Menu(allMenuRecipes);
    }

    private List<Recipe> getRandomDishes(RecipeType recipeType, int numberOfDishes) {
        List<Recipe> allDishes = recipeBook.getRecipeByType(recipeType);
        Random rn = new Random();
        List<Recipe> selectedDishes = new ArrayList<>();
        for (int i = 0; i < numberOfDishes; i++) {
            if (allDishes.isEmpty()) {
                allDishes.addAll(recipeBook.getRecipeByType(recipeType));
            }
            int randomIndex = rn.nextInt(allDishes.size());
            selectedDishes.add(allDishes.remove(randomIndex));
        }
        return selectedDishes;
    }

    public List<Recipe> getRecipesFromProductsFromSupplies() {
        List<Recipe> possibleRecipes = new ArrayList<>();
        for (Recipe recipe : recipeBook.getRecipes()) {
            if (supplies.canIMakeThisRecipe(recipe)) {
                possibleRecipes.add(recipe);
            }
        }
        return possibleRecipes;
    }
}
