CREATE TABLE t_user (
    id SERIAL PRIMARY KEY,
    name VARCHAR NOT NULL
                 CHECK (LENGTH(TRIM(name)) > 0)
                 UNIQUE,
    password VARCHAR(100)
);