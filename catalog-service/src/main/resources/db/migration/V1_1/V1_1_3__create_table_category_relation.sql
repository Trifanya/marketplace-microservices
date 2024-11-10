CREATE TABLE category_relation (
    id SERIAL PRIMARY KEY,
    parent_id INT REFERENCES category(id) NOT NULL
                                          CHECK (parent_id > 0),
    child_id INT REFERENCES category(id) NOT NULL
                                         CHECK (child_id > 0),
    CONSTRAINT uk_category_relation UNIQUE (parent_id, child_id)
);