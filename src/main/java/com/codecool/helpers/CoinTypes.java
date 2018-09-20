package com.codecool.helpers;

public enum CoinTypes {
    NICKLE(21.21, 5.0, 0.05),
    DIME(17.91, 2.268, 0.1),
    QUARTER(24.26, 5.67, 0.25),
    PENCE(21.4, 5.0, 0.01)
    ;

    private final double size;
    private final double weight;
    private final double value;

    CoinTypes(double size, double weight, double value) {
        this.size = size;
        this.weight = weight;
        this.value = value;
    }

    public double getSize() {
        return size;
    }

    public double getWeight() {
        return weight;
    }

    public double getValue() {
        return value;
    }
}
