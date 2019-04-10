package com.mazvile.menuappweb.controllers;

import com.mazvile.menuappweb.dao.ProductsDAO;
import com.mazvile.menuappweb.dao.RecipeDAO;
import com.mazvile.menuappweb.dao.SuppliesDAO;
import com.mazvile.menuappweb.model.Product;
import com.mazvile.menuappweb.model.Recipe;
import com.mazvile.menuappweb.model.RecipeType;
import com.mazvile.menuappweb.model.Units;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TestingController {

    @Autowired
    private ProductsDAO productsDAO;

    @Autowired
    private SuppliesDAO suppliesDAO;

    @Autowired
    private RecipeDAO recipeDAO;

    @RequestMapping("/hello")
    public String hello() {
        Product paprika = new Product("Paprika", 2, Units.PCS);
        int result = productsDAO.insertProduct(paprika);
        if (result == 1) {
            return "Pridėjau";
        } else {
            return "Nepridėjau";
        }
    }

    @RequestMapping("/products")
    public List<Product> products() {
        return productsDAO.getAllProducts();
    }

    @RequestMapping("/suppliesList")
    public List<Product> suppliesList() {
        return suppliesDAO.getAllSupplies();
    }

    @RequestMapping("/supplies")
    public int supplies() {
        List<Product> products = productsDAO.getAllProducts();
        products.get(0).setQuantityValue(3);
        return suppliesDAO.removeProduct(products.get(0));
    }

    @RequestMapping("/recipe")
    public int addRecipe() {
        List<Product> products = productsDAO.getAllProducts();
        products.get(0).setQuantityValue(3);
        Recipe recipe = new Recipe("Food", RecipeType.DESSERT, products);
        return recipeDAO.addRecipe(recipe);
    }

    @RequestMapping("/recipeUpdate")
    public int updateRecipe() {
        List<Product> products = productsDAO.getAllProducts();
        products.get(0).setQuantityValue(3);
        Recipe recipe = new Recipe("Food", RecipeType.DESSERT, products);
        recipe.setDescription("Very nom nom nom dessert");
        recipe.setId(2);
        return recipeDAO.updateRecipe(recipe);
    }

    @RequestMapping("/allRecipes")
    public List<Recipe> getRecipes() {
        return recipeDAO.getAllRecipes();
    }
}