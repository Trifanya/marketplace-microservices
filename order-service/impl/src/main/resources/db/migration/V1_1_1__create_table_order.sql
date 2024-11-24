CREATE TABLE t_order (
    id SERIAL PRIMARY KEY,
    user_id INT NOT NULL
                CHECK (user_id > 0),
    price INT NOT NULL
              CHECK (price > 0),
    order_date_time TIMESTAMP NOT NULL,
    delivery_date_time TIMESTAMP NOT NULL,
    status VARCHAR (20) NOT NULL
)