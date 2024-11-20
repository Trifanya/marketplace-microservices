CREATE TABLE cart_product (
    id SERIAL PRIMARY KEY,
    user_id INT NOT NULL
                CHECK (user_id > 0),
    product_id INT NOT NULL
                   CHECK (product_id > 0),
    product_amount INT NOT NULL
                       CHECK (product_amount > 0),
    CONSTRAINT uk_user_cart_product UNIQUE (user_id, product_id)
);