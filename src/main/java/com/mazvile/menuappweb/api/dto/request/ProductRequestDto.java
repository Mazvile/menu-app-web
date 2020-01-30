package com.mazvile.menuappweb.api.dto.request;

import com.mazvile.menuappweb.model.enums.Units;

public class ProductRequestDto {
    private String name;
    private Units units;

    public String getName() {
        return name;
    }

    public Units getUnits() {
        return units;
    }
}
