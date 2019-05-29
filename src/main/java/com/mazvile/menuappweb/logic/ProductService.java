package com.mazvile.menuappweb.logic;

import com.mazvile.menuappweb.dao.ProductsDAO;
import com.mazvile.menuappweb.model.Product;
import com.mazvile.menuappweb.model.Units;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    ProductsDAO productsDAO;

    public boolean addProduct(String name, Units units) {
        for (Product product : productsDAO.getAllProducts()) {
            if (product.getName().equalsIgnoreCase(name.trim())) {
                return false;
            }
        }

        int result = productsDAO.insertProduct(Product.ProductBuilder.aProduct()
                .withName(name)
                .withUnits(units)
                .build());
        return result != 0;
    }

    private String beautifyName(String uglyName) { //TODO
        return uglyName;
    }

    public boolean deleteProduct(Product product) {
        int result = productsDAO.deleteProduct(product);
        return result != 0;
    }

    public List<Product> getAllProducts() {
        return productsDAO.getAllProducts();
    }

}
