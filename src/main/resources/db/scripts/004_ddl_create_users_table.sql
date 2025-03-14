CREATE TABLE j_user (
    id serial PRIMARY KEY,
    name VARCHAR(2000),
    role_id INT NOT NULL REFERENCES j_role(id)
);