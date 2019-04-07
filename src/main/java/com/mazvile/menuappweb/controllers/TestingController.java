package com.mazvile.menuappweb.controllers;

import com.mazvile.menuappweb.dao.ProductsDAO;
import com.mazvile.menuappweb.dao.SuppliesDAO;
import com.mazvile.menuappweb.model.Product;
import com.mazvile.menuappweb.model.Units;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TestingController {

    @Autowired
    private ProductsDAO productsDAO;

    @Autowired
    private SuppliesDAO suppliesDAO;

    @RequestMapping("/hello")
    public String hello() {
        Product paprika = new Product("Paprika", 2, Units.PCS);
        int result = productsDAO.insertProduct(paprika);
        if (result == 1) {
            return "Pridėjau";
        } else {
            return "Nepridėjau";
        }
    }

    @RequestMapping("/products")
    public List<Product> products() {
        return productsDAO.getAllProducts();
    }

    @RequestMapping("/suppliesList")
    public List<Product> suppliesList() {
        return suppliesDAO.getAllSupplies();
    }

    @RequestMapping("/supplies")
    public int supplies() {
        List<Product> products = productsDAO.getAllProducts();
        products.get(0).setQuantityValue(3);
        int result = suppliesDAO.removeProduct(products.get(0));
        return result;
    }
}
