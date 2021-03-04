package com.example.dao;

import java.util.HashMap;

public class Stock {
    private static HashMap<String, Integer> stock = new HashMap<>();

    public static HashMap<String, Integer> getStock() {
        return stock;
    }
}
