package com.mazvile.menuappweb.service.menu;


import com.mazvile.menuappweb.model.Menu;
import com.mazvile.menuappweb.entity.Recipe;
import com.mazvile.menuappweb.model.RecipeType;
import com.mazvile.menuappweb.service.recipe.RecipeServiceImpl;
import com.mazvile.menuappweb.service.supply.SupplyServiceImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class MenuServiceImpl implements MenuService {

    private final RecipeServiceImpl recipeServiceImpl;

    private final SupplyServiceImpl supplyService;

    private final Random rn = new Random();

    public MenuServiceImpl(final RecipeServiceImpl recipeServiceImpl, final SupplyServiceImpl supplyService) {
        this.recipeServiceImpl = recipeServiceImpl;
        this.supplyService = supplyService;
    }

    @Override
    public Menu makeRandomMenu(
            final int numberOfFishDishes,
            final int numberOfMeatDishes,
            final int numberOfPoultryDishes,
            final int numberOfVeggieDishes) {

        final List<Recipe> selectedFishDishes =
                getRandomDishes(recipeServiceImpl.getRecipeByType(RecipeType.FISH), numberOfFishDishes);
        final List<Recipe> selectedMeatDishes =
                getRandomDishes(recipeServiceImpl.getRecipeByType(RecipeType.MEAT), numberOfMeatDishes);
        final List<Recipe> selectedPoultryDishes =
                getRandomDishes(recipeServiceImpl.getRecipeByType(RecipeType.POULTRY), numberOfPoultryDishes);
        final List<Recipe> selectedVeggieDishes =
                getRandomDishes(recipeServiceImpl.getRecipeByType(RecipeType.VEGETARIAN), numberOfVeggieDishes);

        final List<Recipe> allMenuRecipes = new ArrayList<>();

        allMenuRecipes.addAll(selectedFishDishes);
        allMenuRecipes.addAll(selectedMeatDishes);
        allMenuRecipes.addAll(selectedPoultryDishes);
        allMenuRecipes.addAll(selectedVeggieDishes);

        return new Menu(allMenuRecipes);
    }

    @Override
    public Menu makeRandomMenu(final int numberOfDishes) {
        final List<Recipe> randomDishes = getRandomDishes(recipeServiceImpl.getRecipeRepository(), numberOfDishes);
        return new Menu(randomDishes);
    }

    @Override
    public List<Recipe> getAvailableRecipes() {
        return recipeServiceImpl.getRecipeRepository()
                .stream()
                .filter(recipe -> supplyService.canIMakeThisRecipe(recipe))
                .collect(Collectors.toList());
    }

    private List<Recipe> getRandomDishes(final List<Recipe> choices, final int numberOfDishes) {
        final List<Recipe> allDishes = new ArrayList<>();
        final List<Recipe> selectedDishes = new ArrayList<>();

        for (int i = 0; i < numberOfDishes; i++) {
            if (allDishes.isEmpty()) {
                allDishes.addAll(choices);
            }
            final int randomIndex = rn.nextInt(allDishes.size());
            selectedDishes.add(allDishes.remove(randomIndex));
        }
        return selectedDishes;
    }

}
