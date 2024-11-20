CREATE TABLE category_characteristic (
    id SERIAL PRIMARY KEY,
    category_id INT REFERENCES category(id)
                    NOT NULL
                    CHECK (category_id > 0),
    characteristic_id INT REFERENCES characteristic(id)
                          NOT NULL
                          CHECK (characteristic_id > 0),
    CONSTRAINT uk_category_characteristic UNIQUE (category_id, characteristic_id)
);