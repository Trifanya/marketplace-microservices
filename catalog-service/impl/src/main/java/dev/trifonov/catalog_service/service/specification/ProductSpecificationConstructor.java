package dev.trifonov.catalog_service.service.specification;

import dev.trifonov.catalog_service.entity.Product;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class ProductSpecificationConstructor {
    public static Specification<Product> build(Map<String, String> filters) {
        Specification<Product> productSpecification = null;

        for (Map.Entry<String, String> filter : filters.entrySet()) {

        }

        return productSpecification;
    }
}
