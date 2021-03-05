package com.example.dao;

import com.example.lib.Dao;
import java.util.HashMap;

@Dao
public class Stock {
    private HashMap<String, Integer> stock = new HashMap<>();

    public HashMap<String, Integer> getStock() {
        return stock;
    }
}
