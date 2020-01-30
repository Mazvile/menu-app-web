package com.mazvile.menuappweb.service.supply;

import com.mazvile.menuappweb.repository.entity.Recipe;
import com.mazvile.menuappweb.repository.entity.Supply;
import com.mazvile.menuappweb.model.Menu;

import java.util.List;

public interface SupplyService {

    Supply addSupply(Supply supply);

    void removeSupply(Supply supply);

    List<Supply> getAllSupplies();

    boolean canIMakeThisRecipe(Recipe recipe);

    List<Supply> productsToBuy(Menu menu);
}
