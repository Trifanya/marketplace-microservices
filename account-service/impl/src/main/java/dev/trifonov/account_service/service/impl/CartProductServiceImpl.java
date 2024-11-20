package dev.trifonov.account_service.service.impl;

import dev.trifonov.account_service.entity.UserCartProduct;
import dev.trifonov.account_service.repository.CartProductRepository;

import dev.trifonov.account_service.service.api.CartProductService;
import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CartProductServiceImpl implements CartProductService {

    private final CartProductRepository userProductRepository;

    @Override
    public List<UserCartProduct> getUserCartProducts(long userId) {
        log.trace("");
        return userProductRepository.findAllByUserId(userId);
    }

    @Override
    public void addNewCartProduct(long userId, long productId) {
        log.trace("");
        UserCartProduct newUserCartProduct = new UserCartProduct()
                .setUserId(userId)
                .setProductId(productId)
                .setProductQuantity(1);
        userProductRepository.save(newUserCartProduct);
    }

    @Override
    @Transactional
    public void updateCartProductQuantity(long cartProductId, int newQuantity) {
        log.trace("");
        UserCartProduct productToUpdate = userProductRepository.findById(cartProductId)
                .orElseThrow(() -> new RuntimeException("Товар с указанным id не найден в корзине пользователя."));
        productToUpdate.setProductQuantity(newQuantity);
        userProductRepository.save(productToUpdate);
    }

    @Override
    public void removeProductFromUserCart(long cartProductId) {
        log.trace("");
        userProductRepository.deleteById(cartProductId);
    }
}
