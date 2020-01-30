package com.mazvile.menuappweb.model;

import com.mazvile.menuappweb.model.enums.Units;

public class Quantity {

    private int value;
    private Units unit;

    public Quantity() {}

    public Quantity(int value, Units unit) {
        this.value = value;
        this.unit = unit;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Units getUnit() {
        return unit;
    }

    public void setUnit(Units unit) {
        this.unit = unit;
    }

}
