CREATE TABLE user_review (
    id SERIAL PRIMARY KEY,
    user_id INT NOT NULL
                CHECK (user_id > 0),
    review_id INT NOT NULL
                  CHECK (review_id > 0),
    CONSTRAINT uk_user_review UNIQUE (user_id, review_id)
);