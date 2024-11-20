CREATE TABLE product (
    id SERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL
                     CHECK (LENGTH(TRIM(name)) >= 3),
    brand VARCHAR(50),
    seller VARCHAR(50) NOT NULL,
    price INT NOT NULL
              CHECK (price > 0),
    quantity INT NOT NULL
                 CHECK (quantity >= 0),
    description VARCHAR(1000),
    category_id INT REFERENCES category(id)
                    NOT NULL
                    CHECK (category_id > 0),
    rating DECIMAL(3, 2) NOT NULL
                         CHECK (rating >= 0.0 AND rating <= 5.0)
                         DEFAULT 0.0
);