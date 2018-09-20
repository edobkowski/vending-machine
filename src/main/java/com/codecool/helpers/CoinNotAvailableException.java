package com.codecool.helpers;

public class CoinNotAvailableException extends Exception {
    public CoinNotAvailableException(String message) {
        super(message);
    }
}