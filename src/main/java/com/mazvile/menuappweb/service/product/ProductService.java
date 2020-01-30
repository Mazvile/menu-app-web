package com.mazvile.menuappweb.service.product;

import com.mazvile.menuappweb.repository.entity.Product;
import com.mazvile.menuappweb.model.enums.Units;

import java.util.List;

public interface ProductService {

    Product addProduct(String name, Units units);

    Product addProduct(Product product);

    void deleteProduct(Product product);

    List<Product> getAllProducts();
}
