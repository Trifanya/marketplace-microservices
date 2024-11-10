CREATE TABLE authority (
    id SERIAL PRIMARY KEY,
    name VARCHAR NOT NULL
                 CHECK (LENGTH(TRIM(name)) > 0)
                 UNIQUE
);