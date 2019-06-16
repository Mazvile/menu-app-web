package com.mazvile.menuappweb.model;

public class Product {

    private Integer id;
    private String name;
    private Quantity quantity;

    public String getName() {
        return name;
    }

    public Quantity getQuantity() {
        return quantity;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setQuantityValue(int value) {
        this.quantity.setValue(value);
    }

    public static final class ProductBuilder {
        private Integer id;
        private String name;
        private Quantity quantity = new Quantity();

        private ProductBuilder() {
        }

        public static ProductBuilder aProduct() {
            return new ProductBuilder();
        }

        public ProductBuilder withId(Integer id) {
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
            product.quantity = this.quantity;
            product.name = this.name;
            return product;
        }
    }
}
