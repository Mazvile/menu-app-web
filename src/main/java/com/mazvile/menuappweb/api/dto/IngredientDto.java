package com.mazvile.menuappweb.api.dto;

import java.util.UUID;

public class IngredientDto {

    private UUID productUuid;

    private int quantity;

    public UUID getProductUuid() {
        return productUuid;
    }

    public int getQuantity() {
        return quantity;
    }
}
