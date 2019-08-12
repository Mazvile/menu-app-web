package com.mazvile.menuappweb.service.supply;

import com.mazvile.menuappweb.repository.SupplyRepository;
import com.mazvile.menuappweb.model.Menu;
import com.mazvile.menuappweb.model.Product;
import com.mazvile.menuappweb.model.Recipe;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SupplyServiceImpl implements SupplyService {

    private final SupplyRepository supplyRepository;

    public SupplyServiceImpl(final SupplyRepository supplyRepository) {
        this.supplyRepository = supplyRepository;
    }

    @Override
    public void addSupply(final Product product) {
        this.supplyRepository.addProduct(product);
    }

    @Override
    public void removeSupply(final Product product) {
        this.supplyRepository.removeProduct(product);
    }

    @Override
    public List<Product> getAllSupplies() {
        return supplyRepository.getAllSupplies();
    }

    @Override
    public boolean canIMakeThisRecipe(final Recipe recipe) {
        final List<Product> requiredProducts = recipe.getProducts();
        int counter = 0;
        for (Product product : requiredProducts) {
            for (Product supply : supplyRepository.getAllSupplies()) {
                if ((product.getId().equals(supply.getId())) &&
                        (product.getQuantity().getValue() <= supply.getQuantity().getValue())) {
                    counter++;
                }
            }
        }
        return counter == requiredProducts.size();
    }

    @Override
    public List<Product> productsToBuy(final Menu menu) {
        final List<Recipe> menuRecipes = menu.getMenuRecipes();
        final List<Product> productsNeeded = new ArrayList<>();
        for (Recipe recipe : menuRecipes) {
            productsNeeded.addAll(recipe.getProducts());
        }
        final List<Product> optimizedProducts = sumSameProducts(productsNeeded);

        return subtractSupplies(optimizedProducts);
    }

    private List<Product> subtractSupplies(final List<Product> productsNeeded) {
        final List<Product> result = new ArrayList<>();

        for (Product product : productsNeeded) {
            for (Product supply : supplyRepository.getAllSupplies()) {
                if (product.getId().equals(supply.getId())) {
                    final int val1 = product.getQuantity().getValue();
                    final int val2 = supply.getQuantity().getValue();

                    product.getQuantity().setValue(val1 - val2);
                }
            }
            if (product.getQuantity().getValue() > 0) {
                result.add(product);
            }
        }
        return result;
    }

    private List<Product> sumSameProducts(final List<Product> products) {
        final List<Product> optimizedProductList = new ArrayList<>();

        while (!products.isEmpty()) {
            final Product productTaken = products.remove(0);
            boolean alreadyInList = false;
            for (Product optProduct : optimizedProductList) {
                if (productTaken.getId().equals(optProduct.getId())) {
                    final int value1 = optProduct.getQuantity().getValue();
                    final int value2 = productTaken.getQuantity().getValue();

                    optProduct.getQuantity().setValue(value1 + value2);
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
