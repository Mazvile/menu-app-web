package com.mazvile.menuappweb.logic;

import com.mazvile.menuappweb.dao.ProductsDAO;
import com.mazvile.menuappweb.model.Product;
import com.mazvile.menuappweb.model.Units;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
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

    private String beautifyName(String uglyName) {
        String newName = "";

        String[] parts = uglyName.split(" ");
        List<String> words = new ArrayList<>(Arrays.asList(parts));

        for (String word : words) {
            if (!word.equals("")) {
                newName = newName.concat(word + " ");
            }
        }

        return newName.substring(0, 1).toUpperCase() + newName.trim().toLowerCase().substring(1);
    }

    public boolean deleteProduct(Product product) {
        int result = productsDAO.deleteProduct(product);
        return result != 0;
    }

    public List<Product> getAllProducts() {
        return productsDAO.getAllProducts();
    }

}
