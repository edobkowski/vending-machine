package com.codecool.model;

import com.codecool.helpers.CoinTypes;
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
    private static Map<CoinTypes, Integer> initialMachineCoins;
    private static Map<SnackTypes, Integer> initialMachineSnacks;

    @BeforeAll
    static void init() {
        initialMachineCoins = new HashMap<>();
        initialMachineCoins.put(CoinTypes.DIME, 20);
        initialMachineCoins.put(CoinTypes.NICKLE, 20);
        initialMachineCoins.put(CoinTypes.QUARTER, 20);
        initialMachineSnacks.put(SnackTypes.CANDY, 10);
        initialMachineSnacks.put(SnackTypes.CHIPS, 5);
        initialMachineSnacks.put(SnackTypes.COLA, 20);
    }

    @BeforeEach
    void initForTest() {
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
    void getChangeTest() {
        vendingMachine.setSelectedSnack(SnackTypes.CANDY);
        vendingMachine.acceptCoin(CoinTypes.DIME);
        vendingMachine.acceptCoin(CoinTypes.QUARTER);
        vendingMachine.acceptCoin(CoinTypes.NICKLE);
        vendingMachine.acceptCoin(CoinTypes.DIME);
        vendingMachine.acceptCoin(CoinTypes.QUARTER);
        vendingMachine.acceptCoin(CoinTypes.NICKLE);

        double expectedChange = 0.15;
        double actualChange = vendingMachine.getChange();

        assertEquals(expectedChange, actualChange);
    }
}