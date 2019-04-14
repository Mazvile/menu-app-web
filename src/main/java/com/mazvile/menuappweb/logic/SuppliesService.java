package com.mazvile.menuappweb.logic;

import com.mazvile.menuappweb.dao.SuppliesDAO;
import com.mazvile.menuappweb.model.Menu;
import com.mazvile.menuappweb.model.Product;
import com.mazvile.menuappweb.model.Recipe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SuppliesService {

    @Autowired
    private SuppliesDAO supplies;

    public void addSupply(Product product) {
        this.supplies.addProduct(product);
    }

    public void removeSupply(Product product) {
        this.supplies.removeProduct(product);
    }

    public boolean canIMakeThisRecipe(Recipe recipe) {
        List<Product> requiredProducts = recipe.getProducts();
        int counter = 0;
        for (Product product : requiredProducts) {
            for (Product supply : supplies.getAllSupplies()) {
                if ((product.getName().equals(supply.getName())) && (product.getQuantity().getValue() <= supply.getQuantity().getValue())) {
                    counter++;
                }
            }
        }
        return counter == requiredProducts.size();
    }

    public List<Product> productsToBuy(Menu menu) {
        List<Recipe> menuRecipes = menu.getMenuRecipes();
        List<Product> productsNeeded = new ArrayList<>();
        for (Recipe recipe : menuRecipes) {
            for (Product product : recipe.getProducts()) {
                Product copy = new Product(product.getName(), product.getQuantity().getValue(), product.getQuantity().getUnit());
                productsNeeded.add(copy);
            }
        }
        List<Product> optimizedProducts = sumSameProducts(productsNeeded);
        return subtractSupplies(optimizedProducts);
    }

    private List<Product> subtractSupplies(List<Product> productsNeeded) {
        List<Product> result = new ArrayList<>();
        for (Product product : productsNeeded) {
            for (Product supply : supplies.getAllSupplies()) {
                if (product.getName().equals(supply.getName())) {
                    int val1 = product.getQuantity().getValue();
                    int val2 = supply.getQuantity().getValue();

                    product.getQuantity().setValue(val1 - val2);
                }
            }
            if (product.getQuantity().getValue() > 0) {
                result.add(product);
            }
        }
        return result;
    }

    private List<Product> sumSameProducts(List<Product> products) {
        List<Product> optimizedProductList = new ArrayList<>();
        while (!products.isEmpty()) {
            Product productTaken = products.remove(0);
            boolean alreadyInList = false;
            for (Product optProduct : optimizedProductList) {
                if (productTaken.getName().equals(optProduct.getName())) {
                    int val1 = optProduct.getQuantity().getValue();
                    int val2 = productTaken.getQuantity().getValue();
                    optProduct.getQuantity().setValue(val1 + val2);
                    alreadyInList = true;
                }
            }
            if (!alreadyInList) {
                optimizedProductList.add(productTaken);
            }
        }
        return optimizedProductList;
    }
}
