CREATE TABLE image (
    id SERIAL PRIMARY KEY,
    url VARCHAR(200) NOT NULL
                     UNIQUE,
    product_id INT REFERENCES product(id)
                   NOT NULL
                   CHECK (product_id > 1)
);