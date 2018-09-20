package com.codecool.helpers;

public enum CoinTypes {
    NICKLE(21.21, 5.0),
    DIME(17.91, 2.268),
    QUARTER(24.26, 5.67),
    PENCE(21.4, 5.0)
    ;

    private final double size;
    private final double weight;

    CoinTypes(double size, double weight) {
        this.size = size;
        this.weight = weight;
    }

    public double getSize() {
        return size;
    }

    public double getWeight() {
        return weight;
    }
}
