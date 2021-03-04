package com.example.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import com.example.dao.SubscribePool;
import com.example.model.Product;
import com.example.model.ProductCategory;
import com.example.model.User;
import com.example.service.BackToStockService;
import com.example.service.StockService;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BackToStokeServiceImplTest {
    private static final User bob = new User();
    private static final User alice = new User();
    private static final User jhon = new User();
    private static final Product firstProduct = new Product("1", ProductCategory.MEDICAL);
    private static final BackToStockService backToStockService = new BackToStokeServiceImpl();
    private static final int CORRECT_SIZE_AFTER_SUBSCRIBING = 3;
    private static final StockService stockService = new StockServiceImpl();

    @BeforeAll
    public static void init() {
        bob.setAge(20);
        bob.setName("Bob");
        bob.setPremium(false);
        alice.setAge(70);
        alice.setName("Alice");
        alice.setPremium(false);
        jhon.setAge(40);
        jhon.setName("Jhon");
        jhon.setPremium(true);
        stockService.addNewProduct(firstProduct, 2);
        backToStockService.addNewProductToSubscribePool(firstProduct);
    }

    @BeforeEach
    void beforeEachTest_Ok() {
        SubscribePool.getProductStock().clear();
        backToStockService.addNewProductToSubscribePool(firstProduct);
    }

    @Test
    void subscribe_Ok() {
        localSubscribing();

        assertEquals(SubscribePool.getProductStock()
                .get(firstProduct).size(), CORRECT_SIZE_AFTER_SUBSCRIBING);
    }

    @Test
    void subscribedUsers_Ok() {
        localSubscribing();

        List<User> expectedList = new ArrayList<>(List.of(alice, jhon, bob));
        List<User> actualList = backToStockService.subscribedUsers(firstProduct);
        for (int i = 0; i < expectedList.size(); i++) {
            assertEquals(expectedList.get(i), (actualList.get(i)));
        }
    }

    @Test
    void notifyUser_Ok() {
        localSubscribing();
        List<String> expectedLetters = new ArrayList<>(List
                .of(alice.notifySubscriber(), jhon.notifySubscriber()));
        List<String> actualLetters = backToStockService.notifyUser(firstProduct);
        for (int i = 0; i < expectedLetters.size(); i++) {
            assertEquals(expectedLetters.get(i), actualLetters.get(i));
        }
    }

    @Test
    void addNewProductToSubscribePool_Ok() {

        backToStockService.addNewProductToSubscribePool(firstProduct);

        assertEquals(1, SubscribePool.getProductStock().size());
    }

    private void localSubscribing() {
        backToStockService.subscribe(bob, firstProduct);
        backToStockService.subscribe(alice, firstProduct);
        backToStockService.subscribe(jhon, firstProduct);
    }
}