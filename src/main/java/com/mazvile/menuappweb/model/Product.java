package com.mazvile.menuappweb.model;

public class Product {

    private Integer id;
    private String name;
    private Quantity quantity;

    public Product(String name, int value, Units units) {
        this.name = name;
        this.quantity = new Quantity();
        quantity.setValue(value);
        quantity.setUnit(units);
    }

    public Product(Integer id, String name, Units units) {
        this.name = name;
        this.id = id;
        this.quantity = new Quantity();
        quantity.setUnit(units);
    }

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
}
