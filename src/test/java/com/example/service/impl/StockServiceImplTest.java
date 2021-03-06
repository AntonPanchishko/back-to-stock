package com.example.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import com.example.exception.IncorrectValueException;
import com.example.exception.NoSuchProductException;
import com.example.model.Product;
import com.example.model.ProductCategory;
import com.example.service.StockService;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class StockServiceImplTest {
    private static final StockService stockService = new StockServiceImpl();
    private static final int STOCK_LENGTH_AFTER_ADDING_NEW_ELEMENT = 2;
    private static final String INCORRECT_PRODUCT_ID = "7";
    private static final int AMOUNT_OF_FIRST_PRODUCT = 2;
    private static final Product firstProduct = new Product("1", ProductCategory.MEDICAL);

    @BeforeAll
    public static void init() {
        stockService.addNewProduct(firstProduct, AMOUNT_OF_FIRST_PRODUCT);
    }

    @Test
    void addNewProduct_Ok() {
        Product newProduct = new Product("2", ProductCategory.DIGITAL);
        stockService.addNewProduct(newProduct, AMOUNT_OF_FIRST_PRODUCT);
        assertEquals(STOCK_LENGTH_AFTER_ADDING_NEW_ELEMENT,
                stockService.getAllProducts().getStock().size());
    }

    @Test
    void addNewProduct_NotOk() {
        Product incorrectProduct = new Product("", ProductCategory.MEDICAL);
        assertThrows(IncorrectValueException.class, () ->
        {stockService.addNewProduct(incorrectProduct, AMOUNT_OF_FIRST_PRODUCT);
        });
    }

    @Test
    void getAmountOfProduct_OK() {
        int amountOfProduct = stockService.getAmountOfProduct(firstProduct.getId());
        assertEquals(2 , amountOfProduct);
    }

    @Test
    void getAmountOfProduct_NotOk() {
        assertThrows(NoSuchProductException.class,
                () -> {stockService.getAmountOfProduct(INCORRECT_PRODUCT_ID);
        });
    }

    @AfterAll
    public static void clean() {
        stockService.getAllProducts().getStock().clear();
    }
}