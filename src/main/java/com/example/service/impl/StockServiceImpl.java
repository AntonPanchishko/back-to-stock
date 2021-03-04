package com.example.service.impl;

import com.example.dao.Stock;
import com.example.exception.IncorrectValueException;
import com.example.exception.NoSuchProductException;
import com.example.model.Product;
import com.example.service.StockService;

public class StockServiceImpl implements StockService {

    @Override
    public void addNewProduct(Product product, Integer amount) {
        if (product.getId().isEmpty() || !product.getId().matches("[-+]?\\d+")) {
            throw new IncorrectValueException("Trying to add product with incorrect data");
        }
        Stock.getStock().put(product.getId(), amount);
    }

    @Override
    public int getAmountOfProduct(String id) {
        if (!Stock.getStock().containsKey(id)) {
            throw new NoSuchProductException("Can't find product with such id = " + id);
        }
        return Stock.getStock().get(id);
    }
}
