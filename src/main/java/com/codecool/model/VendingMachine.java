package com.codecool.model;

import com.codecool.helpers.CoinNotAvailableException;
import com.codecool.helpers.CoinTypes;
import com.codecool.helpers.SnackNotAvailableException;
import com.codecool.helpers.SnackTypes;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class VendingMachine {

    private Map<CoinTypes, Integer> coinsAmount;
    private Map<SnackTypes, Integer> snacksAmount;
    private SnackTypes selectedSnack;
    private double currentSum;

    public VendingMachine(Map<CoinTypes, Integer> coinsAmount, Map<SnackTypes, Integer> snacksAmount) {
        this.coinsAmount = coinsAmount;
        this.snacksAmount = snacksAmount;
        this.currentSum = 0.0;
    }

    public boolean acceptCoin(CoinTypes coin) {
        if(coin.getSize() == CoinTypes.NICKLE.getSize()
                && coin.getWeight() == CoinTypes.NICKLE.getWeight()) {
            this.coinsAmount.putIfAbsent(coin, 1);
            this.coinsAmount.computeIfPresent(coin, (key, value) -> value + 1);
            this.currentSum += CoinTypes.NICKLE.getValue();
            return true;
        }
        if(coin.getSize() == CoinTypes.DIME.getSize()
                && coin.getWeight() == CoinTypes.DIME.getWeight()) {
            this.coinsAmount.computeIfPresent(coin, (key, value) -> value + 1);
            this.currentSum += CoinTypes.DIME.getValue();
            return true;
        }
        if(coin.getSize() == CoinTypes.QUARTER.getSize()
                && coin.getWeight() == CoinTypes.QUARTER.getWeight()) {
            this.coinsAmount.computeIfPresent(coin, (key, value) -> value + 1);
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

    public double returnChange() throws CoinNotAvailableException {
        double change = getChange();
        double changeToDispense = change;
        while(changeToDispense > 0) {
            if(changeToDispense >= CoinTypes.QUARTER.getValue()
                    && coinsAmount.containsKey(CoinTypes.QUARTER)
                    && coinsAmount.get(CoinTypes.QUARTER) > 0) {
                decrementCoinsAmount(CoinTypes.QUARTER);
                changeToDispense = round(changeToDispense-CoinTypes.QUARTER.getValue());
            } else if(changeToDispense >= CoinTypes.DIME.getValue()
                    && coinsAmount.containsKey(CoinTypes.DIME)
                    && coinsAmount.get(CoinTypes.DIME) > 0) {
                decrementCoinsAmount(CoinTypes.DIME);
                changeToDispense = round(changeToDispense-CoinTypes.DIME.getValue());
            } else if(changeToDispense >= CoinTypes.NICKLE.getValue()
                    && coinsAmount.containsKey(CoinTypes.NICKLE)
                    && coinsAmount.get(CoinTypes.NICKLE) > 0) {
                decrementCoinsAmount(CoinTypes.NICKLE);
                changeToDispense = round(changeToDispense-CoinTypes.NICKLE.getValue());
            }
        }

        return change;
    }

    public double getChange() {
        return round(this.currentSum-this.selectedSnack.getPrice());
    }

    public boolean isAvailable(SnackTypes snack) {
        Integer snackAmount = this.snacksAmount.get(snack);
        return  (snackAmount != null && !snackAmount.equals(0));
    }

    public void decrementSelectedSnackAmount(SnackTypes snack) throws SnackNotAvailableException {
        Integer currentAmount = this.snacksAmount.get(snack);
        if(currentAmount == null || currentAmount.equals(0)) {
            throw new SnackNotAvailableException("Snack not available");
        }
        this.snacksAmount.put(snack, currentAmount-1);
    }

    public Map<CoinTypes, Integer> getCoinsAmount() {
        return coinsAmount;
    }

    public void setCoinsAmount(Map<CoinTypes, Integer> coinsAmount) {
        this.coinsAmount = coinsAmount;
    }

    public Map<SnackTypes, Integer> getSnacksAmount() {
        return snacksAmount;
    }

    public void setSnacksAmount(Map<SnackTypes, Integer> snacksAmount) {
        this.snacksAmount = snacksAmount;
    }

    public double getCoinsSum() {
        Integer dimesAmount = this.coinsAmount.get(CoinTypes.DIME);
        Integer nicklesAmount = this.coinsAmount.get(CoinTypes.NICKLE);
        Integer quartersAmount = this.coinsAmount.get(CoinTypes.QUARTER);
        double sum = nicklesAmount*0.05 +
                       dimesAmount*0.10 +
                     quartersAmount*0.25;

        return round(sum);
    }

    public double getCurrentSum() {
        return currentSum;
    }

    private double round(double number) {
        return Math.round(number*100.0)/100.0;
    }

    private void decrementCoinsAmount(CoinTypes coin) throws CoinNotAvailableException {
        Integer currentAmount = this.coinsAmount.get(coin);
        if(currentAmount == null || currentAmount.equals(0)) {
            throw new CoinNotAvailableException("Coin not available");
        }
        this.coinsAmount.put(coin, currentAmount-1);
    }
}
