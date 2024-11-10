package dev.trifonov.account_service.service.repo.impl;

import dev.trifonov.account_service.entity.UserCartProduct;
import dev.trifonov.account_service.repository.CartProductRepository;

import dev.trifonov.account_service.service.repo.service.CartProductServiceWithRepo;
import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CartProductServiceWithRepoImpl implements CartProductServiceWithRepo {

    private final CartProductRepository userProductRepository;

    @Override
    public List<UserCartProduct> getUserCartProducts(Integer userId) {
        log.trace("");
        return userProductRepository.findAllByUserId(userId);
    }

    @Override
    public void addNewCartProduct(Integer userId, Integer productId) {
        log.trace("");
        UserCartProduct newUserCartProduct = new UserCartProduct()
                .setUserId(userId)
                .setProductId(productId)
                .setProductQuantity(1);
        userProductRepository.save(newUserCartProduct);
    }

    @Override
    @Transactional
    public void updateCartProductQuantity(Integer cartProductId, Integer newQuantity) {
        log.trace("");
        UserCartProduct productToUpdate = userProductRepository.findById(cartProductId)
                .orElseThrow(() -> new RuntimeException("Товар с указанным id не найден в корзине пользователя."));
        productToUpdate.setProductQuantity(newQuantity);
        userProductRepository.save(productToUpdate);
    }

    @Override
    public void removeProductFromUserCart(Integer cartProductId) {
        log.trace("");
        userProductRepository.deleteById(cartProductId);
    }
}
