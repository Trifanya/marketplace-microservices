CREATE TABLE t_order (
    id SERIAL PRIMARY KEY,
    user_id INT NOT NULL
                CHECK (user_id > 0),
    order_id INT NOT NULL
                 CHECK (order_id > 0),
    CONSTRAINT uk_user_order UNIQUE (user_id, order_id)
);