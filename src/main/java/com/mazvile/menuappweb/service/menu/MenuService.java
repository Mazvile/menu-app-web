package com.mazvile.menuappweb.service.menu;

import com.mazvile.menuappweb.model.Menu;
import com.mazvile.menuappweb.entity.Recipe;

import java.util.List;

public interface MenuService {

    Menu makeRandomMenu(
            int numberOfFishDishes,
            int numberOfMeatDishes,
            int numberOfPoultryDishes,
            int numberOfVeggieDishes);

    Menu makeRandomMenu(int numberOfDishes);

    List<Recipe> getAvailableRecipes();
}
