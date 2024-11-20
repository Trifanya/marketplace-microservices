package dev.trifonov.account_service.service.api;

import dev.trifonov.account_service.entity.UserPurchase;

import java.util.List;

public interface PurchaseService {
    List<UserPurchase> getPurchases(long userId);
    void addNewPurchase(long userId, long productId);
}
