package com.mazvile.menuappweb.controllers;

import com.mazvile.menuappweb.repository.ProductRepository;
import com.mazvile.menuappweb.repository.RecipeRepository;
import com.mazvile.menuappweb.repository.SupplyRepository;
import com.mazvile.menuappweb.model.Product;
import com.mazvile.menuappweb.model.Recipe;
import com.mazvile.menuappweb.model.Units;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TestingController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private SupplyRepository supplyRepository;

    @Autowired
    private RecipeRepository recipeRepository;

    @GetMapping("/products")
    public List<Product> products() {
        return productRepository.getAllProducts();
    }

    @RequestMapping("/suppliesList")
    public List<Product> suppliesList() {
        return supplyRepository.getAllSupplies();
    }

    @RequestMapping("/supplies")
    public int supplies() {
        List<Product> products = productRepository.getAllProducts();
        products.get(0).setQuantityValue(3);
        return supplyRepository.removeProduct(products.get(0));
    }

    @GetMapping("/test")
    public String testGetMessage() {
        return productRepository.getAllProducts().get(0).getName();
    }

    @PostMapping("/test")
    public int testPostMessage() {
        return productRepository.insertProduct(Product.ProductBuilder.aProduct().withName("Potato").withUnits(Units.PCS).build());
    }

//    @RequestMapping("/recipe")
//    public int addRecipe() {
//        List<Product> products = productRepository.getAllProducts();
//        products.get(0).setQuantityValue(3);
//        Recipe recipe = new Recipe("Food", "...", RecipeType.DESSERT, products);
//        return recipeRepository.addRecipe(recipe);
//    }
//
//    @RequestMapping("/recipeUpdate")
//    public int updateRecipe() {
//        List<Product> products = productRepository.getAllProducts();
//        products.get(0).setQuantityValue(3);
//        Recipe recipe = new Recipe("Food", "...", RecipeType.DESSERT, products);
//        recipe.setDescription("Very nom nom nom dessert");
//        recipe.setId(2);
//        return recipeRepository.updateRecipe(recipe);
//    }

    @RequestMapping("/allRecipes")
    public List<Recipe> getRecipes() {
        return recipeRepository.getAllRecipes();
    }
}
