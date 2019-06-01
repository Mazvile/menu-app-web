package com.mazvile.menuappweb.unit;

import com.mazvile.menuappweb.logic.MenuService;
import com.mazvile.menuappweb.logic.RecipeService;
import com.mazvile.menuappweb.logic.SuppliesService;
import com.mazvile.menuappweb.model.Menu;
import com.mazvile.menuappweb.model.Recipe;
import com.mazvile.menuappweb.model.RecipeType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MenuServiceTest {

    @Mock
    private RecipeService recipeBook;

    @Mock
    private SuppliesService supplies;

    @InjectMocks
    private MenuService service;

    @Test
    public void makeRandomMenuShoudReturnOneFishDish() {
        List<Recipe> fishDishes = Arrays.asList(Recipe.RecipeBuilder.aRecipe().withDishType(RecipeType.FISH).build());

        when(recipeBook.getRecipeByType(RecipeType.FISH)).thenReturn(fishDishes);

        Menu result = service.makeRandomMenu(1, 0, 0, 0);

        assertEquals(1, result.getMenuRecipes().size());
        assertEquals(RecipeType.FISH, result.getMenuRecipes().get(0).getDishType());
    }

    @Test
    public void makeRandomMenuShouldReturnSameFishDishTwice() {
        List<Recipe> fishDishes = Arrays.asList(Recipe.RecipeBuilder.aRecipe().withDishType(RecipeType.FISH).build());

        when(recipeBook.getRecipeByType(RecipeType.FISH)).thenReturn(fishDishes);

        Menu result = service.makeRandomMenu(2, 0, 0, 0);

        assertEquals(2, result.getMenuRecipes().size());
        assertEquals(result.getMenuRecipes().get(0), result.getMenuRecipes().get(1));
    }

    @Test
    public void makeRandomMenuShouldReturnDifferentFishDishes() {
        List<Recipe> fishDishes = Arrays.asList(Recipe.RecipeBuilder.aRecipe().withDishType(RecipeType.FISH).build(),
                Recipe.RecipeBuilder.aRecipe().withDishType(RecipeType.FISH).build());

        when(recipeBook.getRecipeByType(RecipeType.FISH)).thenReturn(fishDishes);

        Menu result = service.makeRandomMenu(2, 0, 0, 0);

        assertEquals(2, result.getMenuRecipes().size());
        assertNotEquals(result.getMenuRecipes().get(0), result.getMenuRecipes().get(1));
    }

}
