package com.mazvile.menuappweb.logic;


import com.mazvile.menuappweb.model.Menu;
import com.mazvile.menuappweb.model.Recipe;
import com.mazvile.menuappweb.model.RecipeType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class MenuService {

    @Autowired
    private RecipeService recipeBook;

    @Autowired
    private SuppliesService supplies;

    private Random rn = new Random();

    public Menu makeRandomMenu(
            int numberOfFishDishes,
            int numberOfMeatDishes,
            int numberOfPoultryDishes,
            int numberOfVeggieDishes) {
        List<Recipe> selectedFishDishes =
                getRandomDishes(recipeBook.getRecipeByType(RecipeType.FISH), numberOfFishDishes);
        List<Recipe> selectedMeatDishes =
                getRandomDishes(recipeBook.getRecipeByType(RecipeType.MEAT), numberOfMeatDishes);
        List<Recipe> selectedPoultryDishes =
                getRandomDishes(recipeBook.getRecipeByType(RecipeType.POULTRY), numberOfPoultryDishes);
        List<Recipe> selectedVeggieDishes =
                getRandomDishes(recipeBook.getRecipeByType(RecipeType.VEGETARIAN), numberOfVeggieDishes);

        List<Recipe> allMenuRecipes = new ArrayList<>();
        allMenuRecipes.addAll(selectedFishDishes);
        allMenuRecipes.addAll(selectedMeatDishes);
        allMenuRecipes.addAll(selectedPoultryDishes);
        allMenuRecipes.addAll(selectedVeggieDishes);

        return new Menu(allMenuRecipes);
    }

    public Menu makeRandomMenu(int numberOfDishes) {
        List<Recipe> randomDishes = getRandomDishes(recipeBook.getRecipes(), numberOfDishes);
        return new Menu(randomDishes);
    }

    private List<Recipe> getRandomDishes(List<Recipe> choices, int numberOfDishes) {
        List<Recipe> allDishes = new ArrayList<>();
        List<Recipe> selectedDishes = new ArrayList<>();
        for (int i = 0; i < numberOfDishes; i++) {
            if (allDishes.isEmpty()) {
                allDishes.addAll(choices);
            }
            int randomIndex = rn.nextInt(allDishes.size());
            selectedDishes.add(allDishes.remove(randomIndex));
        }
        return selectedDishes;
    }

    public List<Recipe> getRecipesFromProductsFromSupplies() {
        return recipeBook.getRecipes()
                .stream()
                .filter(recipe -> supplies.canIMakeThisRecipe(recipe))
                .collect(Collectors.toList());
    }
}
