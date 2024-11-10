package dev.trifonov.account_service.service.repo.service;

import dev.trifonov.account_service.entity.UserPurchase;

import java.util.List;

public interface PurchaseServiceWithRepo {
    List<UserPurchase> getPurchases(Integer userId);
    void addNewPurchase(Integer userId, Integer productId);
}
