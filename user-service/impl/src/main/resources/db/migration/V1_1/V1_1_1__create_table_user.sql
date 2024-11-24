CREATE TABLE t_user (
    id SERIAL PRIMARY KEY,
    name VARCHAR (20) NOT NULL
                 CHECK (LENGTH(TRIM(name)) > 0),
    surname VARCHAR (20) NOT NULL
                    CHECK (LENGTH(TRIM(surname)) > 0),
    phone_number VARCHAR (12) NOT NULL
                              CHECK (LENGTH(TRIM(phone_number)) > 0),
    email VARCHAR NOT NULL
                  CHECK (LENGTH(TRIM(email)) > 0)
                  UNIQUE,
    password VARCHAR NOT NULL
);