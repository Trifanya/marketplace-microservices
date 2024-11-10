package dev.trifonov.account_service.service.rest.service;

import dev.trifonov.account_service.dto.PurchaseDto;

import java.util.List;

public interface PurchaseServiceWithRestClient {
    List<PurchaseDto> getPurchases(List<Integer> purchaseIds);
}
