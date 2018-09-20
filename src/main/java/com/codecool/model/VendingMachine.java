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
        if(coin.getSize() == 21.21 && coin.getWeight() == 5.0) {
            this.coinsAmount.putIfAbsent(coin, 1);
            this.coinsAmount.computeIfPresent(coin, (key, value) -> value++);
            return true;
        }
        if(coin.getSize() == 17.91 && coin.getWeight() == 2.268) {
            this.coinsAmount.putIfAbsent(coin, 1);
            this.coinsAmount.computeIfPresent(coin, (key, value) -> value++);
            return true;
        }
        if(coin.getSize() == 24.26 && coin.getWeight() == 5.67) {
            this.coinsAmount.putIfAbsent(coin, 1);
            this.coinsAmount.computeIfPresent(coin, (key, value) -> value++);
            return true;
        }
        return false;
    }

    public double getSum() {
    }
}
