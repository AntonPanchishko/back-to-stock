package com.example.service.impl;

import com.example.dao.Stock;
import com.example.exception.IncorrectValueException;
import com.example.exception.NoSuchProductException;
import com.example.lib.Service;
import com.example.model.Product;
import com.example.service.StockService;

@Service
public class StockServiceImpl implements StockService {
    private Stock stock = new Stock();

    @Override
    public void addNewProduct(Product product, Integer amount) {
        if (product.getId().isEmpty() || !product.getId().matches("[-+]?\\d+")) {
            throw new IncorrectValueException("Trying to add product with incorrect data");
        }
        stock.getStock().put(product.getId(), amount);
    }

    @Override
    public int getAmountOfProduct(String id) {
        if (!stock.getStock().containsKey(id)) {
            throw new NoSuchProductException("Can't find product with such id = " + id);
        }
        return stock.getStock().get(id);
    }

    @Override
    public Stock getAllProducts() {
        return stock;
    }
}
