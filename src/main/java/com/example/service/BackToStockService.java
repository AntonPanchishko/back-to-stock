package com.example.service;

import com.example.dao.SubscribePool;
import com.example.model.Product;
import com.example.model.User;
import java.util.List;

public interface BackToStockService {

    void subscribe(User user, Product product);

    List<User> subscribedUsers(Product product);

    List<String> notifyUser(Product product);

    void addNewProductToSubscribePool(Product product);

    SubscribePool getAllSubscriber();
}
