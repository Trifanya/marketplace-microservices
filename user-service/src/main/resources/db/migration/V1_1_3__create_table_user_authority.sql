CREATE TABLE user_authority (
    id serial PRIMARY KEY,
    user_id INT REFERENCES t_user(id)
                NOT NULL,
    authority_id INT NOT NULL
                     REFERENCES authority(id),
    CONSTRAINT uk_user_authority UNIQUE (user_id, authority_id)
);