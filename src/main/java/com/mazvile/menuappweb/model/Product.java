package com.mazvile.menuappweb.model;

public class Product {

    private String name;
    private Quantity quantity;

    public Product(String name, int value, Units units) {
        this.name = name;
        this.quantity = new Quantity();
        quantity.setValue(value);
        quantity.setUnit(units);
    }

    public String getName() {
        return name;
    }

    public Quantity getQuantity() {
        return quantity;
    }
}
