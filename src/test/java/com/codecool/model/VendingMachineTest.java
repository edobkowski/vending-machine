package com.codecool.model;

import com.codecool.helpers.CoinNotAvailableException;
import com.codecool.helpers.CoinTypes;
import com.codecool.helpers.SnackNotAvailableException;
import com.codecool.helpers.SnackTypes;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class VendingMachineTest {

    private VendingMachine vendingMachine;
    private Map<CoinTypes, Integer> initialMachineCoins;
    private Map<SnackTypes, Integer> initialMachineSnacks;

    @BeforeEach
    void initForTest() {
        initialMachineCoins = new HashMap<>();
        initialMachineCoins.put(CoinTypes.DIME, 20);
        initialMachineCoins.put(CoinTypes.NICKLE, 20);
        initialMachineCoins.put(CoinTypes.QUARTER, 20);
        initialMachineSnacks = new HashMap<>();
        initialMachineSnacks.put(SnackTypes.CANDY, 10);
        initialMachineSnacks.put(SnackTypes.CHIPS, 0);
        initialMachineSnacks.put(SnackTypes.COLA, 20);

        this.vendingMachine = new VendingMachine(initialMachineCoins,
                                                initialMachineSnacks);
    }

    @ParameterizedTest
    @EnumSource(value = CoinTypes.class, names = {"DIME", "QUARTER", "NICKLE"})
    void acceptCoinTest_Accepted(CoinTypes coin) {
        assertTrue(vendingMachine.acceptCoin(coin));
    }

    @Test
    void acceptCoinTest_NotAccepted() {
        assertFalse(vendingMachine.acceptCoin(CoinTypes.PENCE));
    }

    @Test
    void acceptCoinTest_SummingValues() {
        vendingMachine.acceptCoin(CoinTypes.DIME);
        vendingMachine.acceptCoin(CoinTypes.QUARTER);
        vendingMachine.acceptCoin(CoinTypes.NICKLE);
        double expectedSum = 0.4;
        double actualSum = vendingMachine.getSum();

        assertEquals(expectedSum, actualSum);
    }

    @Test
    void getChangeTest() throws CoinNotAvailableException {
        vendingMachine.setSelectedSnack(SnackTypes.CANDY);
        System.out.println(vendingMachine.getCoinsSum());
        vendingMachine.acceptCoin(CoinTypes.QUARTER);
        vendingMachine.acceptCoin(CoinTypes.NICKLE);
        vendingMachine.acceptCoin(CoinTypes.DIME);
        vendingMachine.acceptCoin(CoinTypes.QUARTER);
        vendingMachine.acceptCoin(CoinTypes.NICKLE);

        double expectedChange = 0.15;
        double actualChange = vendingMachine.returnChange();

        double expectedMachineCoinsSum = 8.65;
        double actualMachineCoinsSum = vendingMachine.getCoinsSum();

        assertEquals(expectedChange, actualChange);
        assertEquals(expectedMachineCoinsSum, actualMachineCoinsSum);
    }

    @Test
    void productAvailabilityTest() {
        assertTrue(vendingMachine.isAvailable(SnackTypes.CANDY));
        assertTrue(vendingMachine.isAvailable(SnackTypes.COLA));
        assertFalse(vendingMachine.isAvailable(SnackTypes.CHIPS));
    }

    @Test
    void decrementSnackAmountTest() throws SnackNotAvailableException {
        vendingMachine.decrementSelectedSnackAmount(SnackTypes.CANDY);
        int expectedValue = 9;
        int actualValue = vendingMachine.getSnacksAmount().get(SnackTypes.CANDY);

        assertEquals(expectedValue, actualValue);
        assertThrows(SnackNotAvailableException.class, () ->
                vendingMachine.decrementSelectedSnackAmount(SnackTypes.CHIPS));
    }
}