package dev.trifonov.account_service.service.impl;

import dev.trifonov.account_service.dto.ProductInCartDto;
import dev.trifonov.account_service.entity.CartProduct;
import dev.trifonov.account_service.feign.CatalogFeignClient;
import dev.trifonov.account_service.feign.OrderFeignClient;
import dev.trifonov.account_service.mapper.CartProductMapper;
import dev.trifonov.account_service.repository.CartProductRepository;
import dev.trifonov.account_service.service.api.CartProductService;
import dev.trifonov.catalog_service.dto.ProductPreviewDto;
import dev.trifonov.order_service.dto.OrderProductDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class CartProductServiceImpl implements CartProductService {

    private final OrderFeignClient orderFeignClient;
    private final CatalogFeignClient catalogFeignClient;
    private final CartProductRepository userProductRepository;
    private final CartProductMapper cartProductMapper;

    /*@Override
    public List<ProductInCartDto> getUserCartProducts(long userId) {
        Map<Long, Integer> productIdsAndAmounts = userProductRepository.findAllByUserId(userId).stream()
                .collect(Collectors.toMap(CartProduct::getProductId, CartProduct::getProductQuantity));
        List<ProductPreviewDto> productPreviews = catalogFeignClient.getProductsByIds(productIdsAndAmounts.keySet());
        return productPreviews.stream()
                .map(prod -> new ProductInCartDto()
                        .setProductId(prod.getId())
                        .setProductName(prod.getName())
                        .setProductBrand(prod.getBrand())
                        .setProductPrice(prod.getPrice())
                        .setProductRating(prod.getRating())
                        .setProductCoverUrl(prod.getProductCoverUrl())
                        .setAmountInCart(productIdsAndAmounts.get(prod.getId())))
                .toList();
    }*/
    @Override
    public List<ProductInCartDto> getUserCartProducts(long userId) {
        Map<Long, Integer> productIdsAndAmounts = userProductRepository.findAllByUserId(userId).stream()
                .collect(Collectors.toMap(CartProduct::getProductId, CartProduct::getProductQuantity));
        List<ProductPreviewDto> productPreviews = catalogFeignClient.getProductsByIds(productIdsAndAmounts.keySet());
        return productPreviews.stream()
                .map(prod -> new ProductInCartDto()
                        .setProductPreviewDto(prod)
                        .setAmountInCart(productIdsAndAmounts.get(prod.getProductId())))
                .toList();
    }

    @Override
    public void addNewCartProduct(long userId, long productId) {
        CartProduct newCartProduct = new CartProduct()
                .setUserId(userId)
                .setProductId(productId)
                .setProductQuantity(1);
        userProductRepository.save(newCartProduct);
    }

    @Override
    public void placeAnOrder(long userId) {
        List<ProductInCartDto> productsInCart = this.getUserCartProducts(userId);
        List<OrderProductDto> orderProductDtos = productsInCart.stream()
                .map(cartProductMapper::convertToOrderProductDto)
                .toList();
        orderFeignClient.placeAnOrder(userId, orderProductDtos);
    }

    @Override
    @Transactional
    public void updateCartProductQuantity(long cartProductId, int newQuantity) {
        CartProduct productToUpdate = userProductRepository.findById(cartProductId)
                .orElseThrow(() -> new RuntimeException("Товар с указанным id не найден в корзине пользователя."));
        productToUpdate.setProductQuantity(newQuantity);
        userProductRepository.save(productToUpdate);
    }

    @Override
    public void removeProductFromUserCart(long cartProductId) {
        userProductRepository.deleteById(cartProductId);
    }
}
