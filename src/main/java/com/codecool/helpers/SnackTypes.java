package com.codecool.helpers;

public enum SnackTypes {
    COLA(1.0),
    CHIPS(0.5),
    CANDY(0.65)
    ;

    private double price;

    SnackTypes(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
