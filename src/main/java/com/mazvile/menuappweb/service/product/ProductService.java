package com.mazvile.menuappweb.service.product;

import com.mazvile.menuappweb.model.Product;
import com.mazvile.menuappweb.model.Units;

import java.util.List;

public interface ProductService {

    boolean addProduct(String name, Units units);

    boolean deleteProduct(Product product);

    List<Product> getAllProducts();
}
