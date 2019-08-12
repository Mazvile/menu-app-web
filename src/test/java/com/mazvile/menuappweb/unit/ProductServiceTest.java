package com.mazvile.menuappweb.unit;

import com.mazvile.menuappweb.repository.ProductRepository;
import com.mazvile.menuappweb.service.product.ProductServiceImpl;

import com.mazvile.menuappweb.model.Product;
import com.mazvile.menuappweb.model.Units;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl service;

    @Test
    public void addProductWithNewNameShouldReturnTrue() {
        when(productRepository.insertProduct(any(Product.class))).thenReturn(1);
        assertTrue(service.addProduct("Flour", Units.GRAMS));
    }

    @Test
    public void addProductWithExistingNameShouldReturnFalse() {
        List<Product> products = Arrays.asList(Product.ProductBuilder.aProduct().withName("Flour").build());
        when(productRepository.getAllProducts()).thenReturn(products);
        assertFalse(service.addProduct("Flour", Units.GRAMS));
        assertFalse(service.addProduct("flour", Units.GRAMS));
        assertFalse(service.addProduct(" Flour ", Units.GRAMS));
    }

    @Test(expected = IllegalArgumentException.class)
    public void addProductWithNullUnitsShouldThrowException() {
        service.addProduct("Flour", null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void addProductWithNullNameShouldThrowException() {
        service.addProduct(null, Units.PCS);
    }

    @Test(expected = IllegalArgumentException.class)
    public void addProductWithBlankNameShouldThrowException() {
        service.addProduct(" ", Units.PCS);
    }

    @Test
    public void addProductShouldReturnFalseIfProductWasNotInserted() {

    }
}
