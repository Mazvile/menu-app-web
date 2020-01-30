package com.mazvile.menuappweb.repository.entity;

import com.mazvile.menuappweb.model.enums.Units;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

@Entity
public class Product {

    @Id
    @GeneratedValue
    @Type(type = "pg-uuid")
    @Column(name = "id", columnDefinition = "uuid")
    private UUID id;

    @Column(name = "name", nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "units", nullable = false)
    private Units units;

    public String getName() {
        return name;
    }


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Units getUnits() {
        return units;
    }

    public static final class ProductBuilder {
        private UUID id;
        private String name;
        private Units units;

        private ProductBuilder() {
        }

        public static ProductBuilder aProduct() {
            return new ProductBuilder();
        }

        public ProductBuilder withId(UUID id) {
            this.id = id;
            return this;
        }

        public ProductBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public ProductBuilder withUnits(Units units) {
            this.units = units;
            return this;
        }

        public Product build() {
            Product product = new Product();
            product.setId(id);
            product.name = this.name;
            product.units = this.units;
            return product;
        }
    }

}
