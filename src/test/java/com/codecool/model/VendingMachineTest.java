package com.codecool.model;

import com.codecool.helpers.CoinTypes;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class VendingMachineTest {

    private VendingMachine vendingMachine;
    private static Map<CoinTypes, Integer> initialMachineCoins;

    @BeforeAll
    static void init() {
        initialMachineCoins = new HashMap<>();
        initialMachineCoins.put(CoinTypes.DIME, 20);
        initialMachineCoins.put(CoinTypes.NICKLE, 20);
        initialMachineCoins.put(CoinTypes.QUARTER, 20);
    }

    @BeforeEach
    void initForTest() {
        this.vendingMachine = new VendingMachine(initialMachineCoins);
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
}