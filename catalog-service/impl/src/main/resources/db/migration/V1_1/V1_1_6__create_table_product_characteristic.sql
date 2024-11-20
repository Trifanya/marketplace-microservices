CREATE TABLE product_characteristic (
    id SERIAL PRIMARY KEY,
    product_id INT REFERENCES product(id)
                   NOT NULL
                   CHECK (product_id > 0),
    characteristic_id INT REFERENCES characteristic(id)
                          NOT NULL
                          CHECK (characteristic_id > 0),
    c_value VARCHAR(100),
    CONSTRAINT uk_product_characteristic UNIQUE (product_id, characteristic_id)
);