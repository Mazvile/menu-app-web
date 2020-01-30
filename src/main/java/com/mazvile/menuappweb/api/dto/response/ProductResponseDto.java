package com.mazvile.menuappweb.api.dto.response;

import com.mazvile.menuappweb.model.enums.Units;

import java.util.UUID;

public class ProductResponseDto {
    private UUID uuid;
    private String name;
    private Units units;

    public UUID getUuid() {
        return uuid;
    }

    public String getName() {
        return name;
    }

    public Units getUnits() {
        return units;
    }

    public static final class ProductResponseDtoBuilder {
        private UUID uuid;
        private String name;
        private Units units;

        private ProductResponseDtoBuilder() {
        }

        public static ProductResponseDtoBuilder aProductResponseDto() {
            return new ProductResponseDtoBuilder();
        }

        public ProductResponseDtoBuilder withUuid(UUID uuid) {
            this.uuid = uuid;
            return this;
        }

        public ProductResponseDtoBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public ProductResponseDtoBuilder withUnits(Units units) {
            this.units = units;
            return this;
        }

        public ProductResponseDto build() {
            ProductResponseDto productResponseDto = new ProductResponseDto();
            productResponseDto.name = this.name;
            productResponseDto.uuid = this.uuid;
            productResponseDto.units = this.units;
            return productResponseDto;
        }
    }

}
