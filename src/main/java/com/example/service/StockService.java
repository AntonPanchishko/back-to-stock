package com.example.service;

import com.example.dao.Stock;
import com.example.model.Product;

public interface StockService {
    void addNewProduct(Product product, Integer amount);

    int getAmountOfProduct(String id);

    Stock getAllProducts();
}
