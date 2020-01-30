package com.mazvile.menuappweb.api.controller;

import com.mazvile.menuappweb.api.dto.request.ProductRequestDto;
import com.mazvile.menuappweb.api.dto.response.ProductResponseDto;
import com.mazvile.menuappweb.repository.entity.Product;
import com.mazvile.menuappweb.service.product.ProductService;
import com.mazvile.menuappweb.api.transformer.ProductTransformer;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ProductResponseDto createProduct(@RequestBody final ProductRequestDto productRequestDto) {
        final Product product = productService.addProduct(ProductTransformer.fromRequest(productRequestDto));
        return ProductTransformer.toResponse(product);
    }

}
