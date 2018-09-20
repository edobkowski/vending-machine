package com.codecool.model;

import com.codecool.helpers.CoinTypes;
import com.codecool.helpers.SnackTypes;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class VendingMachine {

    private Map<CoinTypes, Integer> coinsAmount;
    private Map<SnackTypes, Integer> snacksAmount;
    private SnackTypes selectedSnack;
    private double currentSum;

    public VendingMachine(Map<CoinTypes, Integer> coinsAmount) {
        this.coinsAmount = coinsAmount;
        this.currentSum = 0.0;
    }

    public boolean acceptCoin(CoinTypes coin) {
        if(coin.getSize() == CoinTypes.NICKLE.getSize()
                && coin.getWeight() == CoinTypes.NICKLE.getWeight()) {
            this.coinsAmount.putIfAbsent(coin, 1);
            this.coinsAmount.computeIfPresent(coin, (key, value) -> value++);
            this.currentSum += CoinTypes.NICKLE.getValue();
            return true;
        }
        if(coin.getSize() == CoinTypes.DIME.getSize()
                && coin.getWeight() == CoinTypes.DIME.getWeight()) {
            this.coinsAmount.computeIfPresent(coin, (key, value) -> value++);
            this.currentSum += CoinTypes.DIME.getValue();
            return true;
        }
        if(coin.getSize() == CoinTypes.QUARTER.getSize()
                && coin.getWeight() == CoinTypes.QUARTER.getWeight()) {
            this.coinsAmount.computeIfPresent(coin, (key, value) -> value++);
            this.currentSum += CoinTypes.QUARTER.getValue();
            return true;
        }
        return false;
    }

    public double getSum() {
        return round(this.currentSum);
    }

    public SnackTypes getSelectedSnack() {
        return selectedSnack;
    }

    public void setSelectedSnack(SnackTypes selectedSnack) {
        this.selectedSnack = selectedSnack;
    }

    public double getChange() {
        return round(this.currentSum-this.selectedSnack.getPrice());
    }

    private double round(double number) {
        return Math.round(number*100.0)/100.0;
    }
}
