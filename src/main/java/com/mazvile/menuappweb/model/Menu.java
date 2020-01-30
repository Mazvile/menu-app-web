package com.mazvile.menuappweb.model;

import com.mazvile.menuappweb.repository.entity.Recipe;

import java.util.List;

public class Menu {

    private List<Recipe> menuRecipes;

    public Menu(List<Recipe> recipes) {
        this.menuRecipes = recipes;
    }

    public List<Recipe> getMenuRecipes() {
        return menuRecipes;
    }
}
