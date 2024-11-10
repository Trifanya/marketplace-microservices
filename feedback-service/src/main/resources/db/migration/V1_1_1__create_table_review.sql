CREATE TABLE review (
    id SERIAL PRIMARY KEY,
    user_id INT CHECK (user_id > 0),
    product_id INT CHECK (product_id > 0),
    text TEXT,
    product_quality SMALLINT CHECK (product_quality >= 1 AND product_quality <= 5),
    posted_at TIMESTAMP NOT NULL,
    CONSTRAINT uk_user_product UNIQUE (user_id, product_id)
);