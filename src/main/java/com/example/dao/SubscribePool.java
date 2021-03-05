package com.example.dao;

import com.example.model.Product;
import com.example.model.User;
import java.util.HashMap;
import java.util.List;

public class SubscribePool {
    private HashMap<Product, List<User>> subscribePool = new HashMap<>();

    public HashMap<Product, List<User>> getSubscribePool() {
        return subscribePool;
    }
}
