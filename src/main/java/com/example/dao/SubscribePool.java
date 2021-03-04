package com.example.dao;

import com.example.model.Product;
import com.example.model.User;
import java.util.HashMap;
import java.util.List;

public class SubscribePool {
    private static HashMap<Product, List<User>> productStock = new HashMap<>();

    public static HashMap<Product, List<User>> getProductStock() {
        return productStock;
    }
}
