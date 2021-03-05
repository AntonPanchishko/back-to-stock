package com.example.service.impl;

import com.example.dao.SubscribePool;
import com.example.lib.Service;
import com.example.model.Product;
import com.example.model.ProductCategory;
import com.example.model.User;
import com.example.service.BackToStockService;
import com.example.service.StockService;
import java.util.ArrayList;
import java.util.List;

@Service
public class BackToStokeServiceImpl implements BackToStockService {
    private final StockService stockService = new StockServiceImpl();
    private final SubscribePool subscribePool = new SubscribePool();

    @Override
    public void subscribe(User user, Product product) {
        List<User> users = new ArrayList<>(subscribePool.getSubscribePool().get(product));
        users.add(user);
        subscribePool.getSubscribePool().replace(product, userSort(users, product));
    }

    @Override
    public List<User> subscribedUsers(Product product) {
        return subscribePool.getSubscribePool().get(product);
    }

    @Override
    public List<String> notifyUser(Product product) {
        List<String> lettersForUser = new ArrayList<>();
        for (int i = 0; i < stockService.getAmountOfProduct(product.getId())
                || i < subscribePool.getSubscribePool().get(product).size(); i++) {
            lettersForUser.add(subscribePool.getSubscribePool()
                    .get(product).get(i).notifySubscriber());
        }
        return lettersForUser;
    }

    @Override
    public void addNewProductToSubscribePool(Product product) {
        subscribePool.getSubscribePool().put(product, List.of());
    }

    @Override
    public SubscribePool getAllSubscriber() {
        return subscribePool;
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
