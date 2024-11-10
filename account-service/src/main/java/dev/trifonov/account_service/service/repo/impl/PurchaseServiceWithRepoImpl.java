package dev.trifonov.account_service.service.repo.impl;

import dev.trifonov.account_service.entity.UserPurchase;
import dev.trifonov.account_service.repository.PurchaseRepository;
import dev.trifonov.account_service.service.repo.service.PurchaseServiceWithRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class PurchaseServiceWithRepoImpl implements PurchaseServiceWithRepo {

    private final PurchaseRepository purchaseRepository;

    @Override
    public List<UserPurchase> getPurchases(Integer userId) {
        log.trace("");
        return purchaseRepository.findAllByUserId(userId);
    }

    @Override
    public void addNewPurchase(Integer userId, Integer productId) {
        log.trace("");
        UserPurchase newUserPurchase = new UserPurchase()
                .setUserId(userId)
                .setProductId(productId);
        purchaseRepository.save(newUserPurchase);
    }
}
