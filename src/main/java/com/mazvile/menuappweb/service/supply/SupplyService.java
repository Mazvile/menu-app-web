package com.mazvile.menuappweb.service.supply;

import com.mazvile.menuappweb.model.Menu;
import com.mazvile.menuappweb.model.Product;
import com.mazvile.menuappweb.model.Recipe;

import java.util.List;

public interface SupplyService {

    void addSupply(Product product);

    void removeSupply(Product product);

    List<Product> getAllSupplies();

    boolean canIMakeThisRecipe(Recipe recipe);

    List<Product> productsToBuy(Menu menu);
}
