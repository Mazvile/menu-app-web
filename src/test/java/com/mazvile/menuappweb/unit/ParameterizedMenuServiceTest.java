package com.mazvile.menuappweb.unit;

import com.mazvile.menuappweb.service.menu.MenuServiceImpl;
import com.mazvile.menuappweb.service.recipe.RecipeServiceImpl;
import com.mazvile.menuappweb.service.supply.SupplyServiceImpl;
import com.mazvile.menuappweb.model.Menu;
import com.mazvile.menuappweb.repository.entity.Recipe;
import com.mazvile.menuappweb.model.enums.RecipeType;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.Mockito.when;

@RunWith(Parameterized.class)
public class ParameterizedMenuServiceTest {

    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    @Mock
    private RecipeServiceImpl recipeBook;

    @Mock
    private SupplyServiceImpl supplies;

    @InjectMocks
    private MenuServiceImpl service;

    @Parameter(0)
    public RecipeType dishType;
    @Parameter(1)
    public int numberOfFishDishes;
    @Parameter(2)
    public int numberOfMeatDishes;
    @Parameter(3)
    public int numberOfPoultryDishes;
    @Parameter(4)
    public int numberOfVeggieDishes;

    @Parameters
    public static Collection<Object[]> data() {
        Object[] fishData = new Object[]{RecipeType.FISH, 2, 0, 0, 0};
        Object[] meatData = new Object[]{RecipeType.MEAT, 0, 2, 0, 0};
        Object[] poultryData = new Object[]{RecipeType.POULTRY, 0, 0, 2, 0};
        Object[] veggieData = new Object[]{RecipeType.VEGETARIAN, 0, 0, 0, 2};
        Object[][] data = new Object[][]{fishData, meatData, poultryData, veggieData};
        return Arrays.asList(data);
    }

    @Test
    public void makeRandomMenuShouldReturnTwoRequiredDishes() {
        List<Recipe> dishes = Arrays.asList(Recipe.RecipeBuilder.aRecipe().withDishType(dishType).build());

        when(recipeBook.getRecipeByType(dishType)).thenReturn(dishes);

        Menu result = service.makeRandomMenu(
                numberOfFishDishes,
                numberOfMeatDishes,
                numberOfPoultryDishes,
                numberOfVeggieDishes);

        assertEquals(2, result.getMenuRecipes().size());
        assertEquals(dishType, result.getMenuRecipes().get(0).getDishType());
    }

    @Test
    public void makeRandomMenuShouldReturnSameDishTwiceIfThereIsOnlyOneDishInRecipeBook() {
        List<Recipe> dishes = Arrays.asList(Recipe.RecipeBuilder.aRecipe().withDishType(dishType).build());

        when(recipeBook.getRecipeByType(dishType)).thenReturn(dishes);

        Menu result = service.makeRandomMenu(
                numberOfFishDishes,
                numberOfMeatDishes,
                numberOfPoultryDishes,
                numberOfVeggieDishes);

        assertEquals(result.getMenuRecipes().get(0), result.getMenuRecipes().get(1));
    }

    @Test
    public void makeRandomMenuShouldReturnDifferentDishesIfThereAreMoreThanOneDishInRecipeBook() {
        List<Recipe> dishes = Arrays.asList(Recipe.RecipeBuilder.aRecipe().withDishType(dishType).build(),
                Recipe.RecipeBuilder.aRecipe().withDishType(dishType).build());

        when(recipeBook.getRecipeByType(dishType)).thenReturn(dishes);

        Menu result = service.makeRandomMenu(
                numberOfFishDishes,
                numberOfMeatDishes,
                numberOfPoultryDishes,
                numberOfVeggieDishes);

        assertEquals(2, result.getMenuRecipes().size());
        assertNotEquals(result.getMenuRecipes().get(0), result.getMenuRecipes().get(1));
    }
}
