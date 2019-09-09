package com.mazvile.menuappweb.entity;

import com.mazvile.menuappweb.model.Quantity;
import com.mazvile.menuappweb.model.Units;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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

    public static final class ProductBuilder {
        private UUID id;
        private String name;
        private Quantity quantity = new Quantity();

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

        public ProductBuilder withQuantity(Quantity quantity) {
            this.quantity = quantity;
            return this;
        }

        public ProductBuilder withUnits(Units units) {
            this.quantity.setUnit(units);
            return this;
        }

        public ProductBuilder withValue(int value) {
            this.quantity.setValue(value);
            return this;
        }

        public Product build() {
            Product product = new Product();
            product.setId(id);
            product.units = this.quantity.getUnit();
            product.name = this.name;
            return product;
        }
    }
}
