package com.example.service.impl;

import com.example.dao.SubscribePool;
import com.example.model.Product;
import com.example.model.ProductCategory;
import com.example.model.User;
import com.example.service.BackToStockService;
import com.example.service.StockService;
import java.util.ArrayList;
import java.util.List;

public class BackToStokeServiceImpl implements BackToStockService {
    private final StockService stockService = new StockServiceImpl();

    @Override
    public void subscribe(User user, Product product) {
        List<User> users = new ArrayList<>(SubscribePool.getProductStock().get(product));
        users.add(user);
        SubscribePool.getProductStock().replace(product, userSort(users, product));
    }

    @Override
    public List<User> subscribedUsers(Product product) {
        return SubscribePool.getProductStock().get(product);
    }

    @Override
    public List<String> notifyUser(Product product) {
        List<String> lettersForUser = new ArrayList<>();
        for (int i = 0; i < stockService.getAmountOfProduct(product.getId())
                && i < SubscribePool.getProductStock().get(product).size(); i++) {
            lettersForUser.add(SubscribePool.getProductStock()
                    .get(product).get(i).notifySubscriber());
        }
        return lettersForUser;
    }

    @Override
    public void addNewProductToSubscribePool(Product product) {
        SubscribePool.getProductStock().put(product, List.of());
    }

    private List<User> userSort(List<User> users, Product product) {
        List<User> premiumUser = new ArrayList<>();
        for (int i = 0; i < users.size(); i++) {
            User currentUser = users.get(i);
            if (currentUser.isPremium()
                    || (product.getCategory().equals(ProductCategory.MEDICAL)
                    && currentUser.getAge() >= 70)) {
                premiumUser.add(currentUser);
                users.remove(currentUser);
            }
        }
        List<User> sortedUser = new ArrayList<>();
        sortedUser.addAll(premiumUser);
        sortedUser.addAll(users);
        return sortedUser;
    }
}
