package com.mazvile.menuappweb.service.supply;

import com.mazvile.menuappweb.model.Menu;
import com.mazvile.menuappweb.repository.SupplyRepository;
import com.mazvile.menuappweb.repository.entity.Ingredient;
import com.mazvile.menuappweb.repository.entity.Recipe;
import com.mazvile.menuappweb.repository.entity.Supply;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class SupplyServiceImpl implements SupplyService {

    private final SupplyRepository supplyRepository;

    public SupplyServiceImpl(final SupplyRepository supplyRepository) {
        this.supplyRepository = supplyRepository;
    }

    @Override
    public Supply addSupply(final Supply supply) {
        return supplyRepository.save(supply);
    }

    @Override
    public void removeSupply(final Supply supply) {
        this.supplyRepository.delete(supply);
    }

    @Override
    public List<Supply> getAllSupplies() {
        return supplyRepository.findAll();
    }

    @Override
    public boolean canIMakeThisRecipe(final Recipe recipe) {
        final Set<Ingredient> ingredients = recipe.getIngredients();
        int counter = 0;
        for (Ingredient ingredient : ingredients) {
            for (Supply supply : supplyRepository.findAll()) {
                if ((ingredient.getProduct().getId().equals(supply.getSupply().getId())) &&
                        (ingredient.getQuantity() <= supply.getQuantity())) {
                    counter++;
                }
            }
        }
        return counter == ingredients.size();
    }

    @Override
    public List<Supply> productsToBuy(final Menu menu) {
        final List<Recipe> menuRecipes = menu.getMenuRecipes();
        final List<Supply> productsNeeded = new ArrayList<>();
        for (Recipe recipe : menuRecipes) {
            productsNeeded.addAll(recipe.getIngredients()
                    .stream()
                    .map(ingredient -> Supply.SupplyBuilder
                            .aSupply()
                            .withSupply(ingredient.getProduct())
                            .withQuantity(ingredient.getQuantity())
                            .build())
                    .collect(Collectors.toList()));
        }
        final List<Supply> optimizedProducts = sumSameProducts(productsNeeded);

        return subtractSupplies(optimizedProducts);
    }

    private List<Supply> subtractSupplies(final List<Supply> productsNeeded) {
        final List<Supply> result = new ArrayList<>();

        for (Supply product : productsNeeded) {
            for (Supply supply : supplyRepository.findAll()) {
                if (product.getSupply().getId().equals(supply.getSupply().getId())) {
                    final int val1 = product.getQuantity();
                    final int val2 = supply.getQuantity();

                    product.setQuantity(val1 - val2);
                }
            }
            if (product.getQuantity() > 0) {
                result.add(product);
            }
        }
        return result;
    }

    private List<Supply> sumSameProducts(final List<Supply> supplies) {
        final List<Supply> optimizedSupplies = new ArrayList<>();

        while (!supplies.isEmpty()) {
            final Supply productTaken = supplies.remove(0);
            boolean alreadyInList = false;
            for (Supply supply : optimizedSupplies) {
                if (productTaken.getSupply().getId().equals(supply.getSupply().getId())) {
                    final int value1 = supply.getQuantity();
                    final int value2 = productTaken.getQuantity();

                    supply.setQuantity(value1 + value2);
                    alreadyInList = true;
                }
            }
            if (!alreadyInList) {
                optimizedSupplies.add(productTaken);
            }
        }
        return optimizedSupplies;
    }
}
