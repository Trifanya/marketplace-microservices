package dev.trifonov.account_service.service.impl;

import dev.trifonov.account_service.entity.UserPurchase;
import dev.trifonov.account_service.repository.PurchaseRepository;
import dev.trifonov.account_service.service.api.PurchaseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class PurchaseServiceImpl implements PurchaseService {

    private final PurchaseRepository purchaseRepository;

    @Override
    public List<UserPurchase> getPurchases(long userId) {
        return purchaseRepository.findAllByUserId(userId);
    }

    @Override
    public void addNewPurchase(long userId, long productId) {
        UserPurchase newUserPurchase = new UserPurchase()
                .setUserId(userId)
                .setProductId(productId);
        purchaseRepository.save(newUserPurchase);
    }
}
