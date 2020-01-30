package com.mazvile.menuappweb.api.transformer;

import com.mazvile.menuappweb.api.dto.request.ProductRequestDto;
import com.mazvile.menuappweb.api.dto.response.ProductResponseDto;
import com.mazvile.menuappweb.repository.entity.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductTransformer {

    public static Product fromRequest(final ProductRequestDto productRequestDto) {
        return Product.ProductBuilder.aProduct()
                .withName(productRequestDto.getName())
                .withUnits(productRequestDto.getUnits())
                .build();
    }

    public static ProductResponseDto toResponse(final Product product) {
        return ProductResponseDto.ProductResponseDtoBuilder.aProductResponseDto()
                .withUuid(product.getId())
                .withName(product.getName())
                .withUnits(product.getUnits())
                .build();
    }

}
