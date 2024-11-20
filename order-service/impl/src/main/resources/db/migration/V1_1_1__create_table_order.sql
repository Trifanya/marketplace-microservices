CREATE TABLE t_order (
    id SERIAL PRIMARY KEY,
    user_id INT NOT NULL
                CHECK user_id > 0,
    price INT NOT NULL
              CHECK (price > 0),
    date_time TIMESTAMP NOT NULL
)