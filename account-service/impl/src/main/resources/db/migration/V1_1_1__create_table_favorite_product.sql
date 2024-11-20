CREATE TABLE favorite_product (
    id SERIAL PRIMARY KEY,
    user_id INT NOT NULL
                CHECK (user_id > 0),
    product_id INT NOT NULL
                   CHECK (product_id > 0),
    CONSTRAINT uk_user_favorite_product UNIQUE (user_id, product_id)
);