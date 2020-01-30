package com.mazvile.menuappweb.unit;

import com.mazvile.menuappweb.model.Menu;
import com.mazvile.menuappweb.model.enums.RecipeType;
import com.mazvile.menuappweb.service.menu.MenuServiceImpl;
import com.mazvile.menuappweb.service.recipe.RecipeService;
import com.mazvile.menuappweb.service.supply.SupplyService;
import com.mazvile.menuappweb.repository.entity.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MenuServiceTest {

    @Mock
    private RecipeService recipeBook;

    @Mock
    private SupplyService supplies;

    @InjectMocks
    private MenuServiceImpl service;

    @Test
    public void makeRandomMenuShouldReturnEmptyListIfNumberOfDishWasZero() {
        Menu result = service.makeRandomMenu(0);

        assertEquals(0, result.getMenuRecipes().size());
    }

    @Test
    public void makeRandomMenuShouldReturnEmptyListIfAllNumberOfDishesWereZeros() {
        Menu result = service.makeRandomMenu(0, 0, 0, 0);

        assertEquals(0, result.getMenuRecipes().size());
    }

    @Test
    public void makeRandomMenuShouldTreatNegativeNumberOfDishesAsZero() {
        Menu result = service.makeRandomMenu(-5);

        assertEquals(0, result.getMenuRecipes().size());
    }

    @Test
    public void makeRandomMenuShouldTreatNegativeNumbersOfDishesAsZeros() {
        List<Recipe> meatDishes = Arrays.asList(Recipe.RecipeBuilder.aRecipe().withDishType(RecipeType.MEAT).build());
        List<Recipe> fishDishes = Arrays.asList(Recipe.RecipeBuilder.aRecipe().withDishType(RecipeType.FISH).build());

        when(recipeBook.getRecipeByType(RecipeType.MEAT)).thenReturn(meatDishes);
        when(recipeBook.getRecipeByType(RecipeType.FISH)).thenReturn(fishDishes);

        Menu result = service.makeRandomMenu(-5, 2, 0, -7);

        assertEquals(2, result.getMenuRecipes().size());
    }

    @Test
    public void makeRandomMenuShouldReturnMenuWithFiveDishesIfFiveDishesWereRequestedInTotal() {
        List<Recipe> meatDishes = Arrays.asList(Recipe.RecipeBuilder.aRecipe().withDishType(RecipeType.MEAT).build());

        when(recipeBook.getRecipeByType(RecipeType.MEAT)).thenReturn(meatDishes);

        Menu result = service.makeRandomMenu(0, 5, 0, 0);
        assertEquals(5, result.getMenuRecipes().size());
    }


    @Test
    public void makeRandomMenuShouldReturnMenuWithCorrectNumberOfDishesByType() {
        List<Recipe> fishDishes = Arrays.asList(Recipe.RecipeBuilder.aRecipe().withDishType(RecipeType.FISH).build());
        List<Recipe> meatDishes = Arrays.asList(Recipe.RecipeBuilder.aRecipe().withDishType(RecipeType.MEAT).build());
        List<Recipe> poultryDishes = Arrays.asList(Recipe.RecipeBuilder.aRecipe().withDishType(RecipeType.POULTRY).build());
        List<Recipe> veggieDishes = Arrays.asList(Recipe.RecipeBuilder.aRecipe().withDishType(RecipeType.VEGETARIAN).build());

        when(recipeBook.getRecipeByType(RecipeType.FISH)).thenReturn(fishDishes);
        when(recipeBook.getRecipeByType(RecipeType.MEAT)).thenReturn(meatDishes);
        when(recipeBook.getRecipeByType(RecipeType.POULTRY)).thenReturn(poultryDishes);
        when(recipeBook.getRecipeByType(RecipeType.VEGETARIAN)).thenReturn(veggieDishes);

        Menu result = service.makeRandomMenu(1, 2, 3, 4);
        assertEquals(10, result.getMenuRecipes().size());

        int poultry = 0;
        int fish = 0;
        int meat = 0;
        int veggie = 0;
        for (Recipe recipe : result.getMenuRecipes()) {
            switch (recipe.getDishType()) {
                case POULTRY:
                    poultry++;
                    break;
                case FISH:
                    fish++;
                    break;
                case MEAT:
                    meat++;
                    break;
                case VEGETARIAN:
                    veggie++;
                    break;
            }
        }
        assertEquals(1, fish);
        assertEquals(2, meat);
        assertEquals(3, poultry);
        assertEquals(4, veggie);
    }

    @Test
    public void getAvailableRecipesShouldReturnRecipesICanMake() {
        List<Recipe> recipes = Arrays.asList(Recipe.RecipeBuilder.aRecipe().build());

        when(supplies.canIMakeThisRecipe(recipes.get(0))).thenReturn(true);
        when(recipeBook.getRecipeRepository()).thenReturn(recipes);

        List<Recipe> result = service.getAvailableRecipes();
        assertEquals(recipes, result);
        assertEquals(1, result.size());
        assertSame(recipes.get(0), result.get(0));
    }

    @Test
    public void getAvailableRecipesShouldReturnEmptyListIfThereIsNotEnoughProducts() {
        List<Recipe> recipes = Arrays.asList(Recipe.RecipeBuilder.aRecipe().build());

        when(supplies.canIMakeThisRecipe(recipes.get(0))).thenReturn(false);
        when(recipeBook.getRecipeRepository()).thenReturn(recipes);

        List<Recipe> result = service.getAvailableRecipes();
        assertEquals(0, result.size());
    }

    @Test
    public void getAvailableRecipesShouldReturnOnlyRecipesWhichAreAvailableWithCurrentSupplies() {
        List<Recipe> recipes = Arrays.asList(Recipe.RecipeBuilder.aRecipe().build(),
                Recipe.RecipeBuilder.aRecipe().build());

        when(supplies.canIMakeThisRecipe(recipes.get(0))).thenReturn(true);
        when(supplies.canIMakeThisRecipe(recipes.get(1))).thenReturn(false);
        when(recipeBook.getRecipeRepository()).thenReturn(recipes);

        List<Recipe> result = service.getAvailableRecipes();
        assertEquals(1, result.size());
    }
}
