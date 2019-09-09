package com.mazvile.menuappweb.service.product;

import com.mazvile.menuappweb.entity.Product;
import com.mazvile.menuappweb.model.Units;
import com.mazvile.menuappweb.repository.ProductRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(final ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public boolean addProduct(final String name, final Units units) {
        if (units == null) {
            throw new IllegalArgumentException("Units cannot be null");
        }
        if (name == null || StringUtils.isBlank(name)) {
            throw new IllegalArgumentException("Name cannot be null or blank");
        }
        for (Product product : productRepository.getAllProducts()) {
            if (product.getName().equalsIgnoreCase(name.trim())) {
                return false;
            }
        }

        final int result = productRepository.insertProduct(Product.ProductBuilder.aProduct()
                .withName(name)
                .withUnits(units)
                .build());

        return result != 0;
    }


    @Override
    public boolean deleteProduct(final Product product) {
        final int result = productRepository.deleteProduct(product);
        return result != 0;
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.getAllProducts();
    }

    private String beautifyName(String uglyName) {
        String newName = "";

        final String[] parts = uglyName.split(" ");
        final List<String> words = new ArrayList<>(Arrays.asList(parts));

        for (String word : words) {
            if (!word.equals("")) {
                newName = newName.concat(word + " ");
            }
        }

        return newName.substring(0, 1).toUpperCase() + newName.trim().toLowerCase().substring(1);
    }
}
