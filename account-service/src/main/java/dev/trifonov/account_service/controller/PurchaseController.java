package dev.trifonov.account_service.controller;

import dev.trifonov.account_service.dto.PurchaseDto;
import dev.trifonov.account_service.entity.UserPurchase;
import dev.trifonov.account_service.service.repo.service.PurchaseServiceWithRepo;
import dev.trifonov.account_service.service.rest.service.PurchaseServiceWithRestClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/account/{userId}/purchases")
public class PurchaseController {

    private final PurchaseServiceWithRepo purchaseServiceWithRepo;
    private final PurchaseServiceWithRestClient purchaseServiceWithRestClient;

    @GetMapping
    public List<PurchaseDto> getUserPurchases(@PathVariable("userId") Integer userId) {
        log.trace("userId = " + userId);
        List<Integer> purchaseIds =
                purchaseServiceWithRepo.getPurchases(userId).stream()
                        .map(UserPurchase::getId)
                        .toList();
        return purchaseServiceWithRestClient.getPurchases(purchaseIds);
    }
}
