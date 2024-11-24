CREATE TABLE order_product (
    id SERIAL PRIMARY KEY,
    order_id INT REFERENCES t_order(id)
                     NOT NULL
                     CHECK (order_id > 0),
    product_id INT NOT NULL
                   CHECK (product_id > 0),
    amount INT NOT NULL
               CHECK (amount > 0),
    price INT NOT NULL
              CHECK (price > 0),
    CONSTRAINT uk_order_product UNIQUE (order_id, product_id)
);