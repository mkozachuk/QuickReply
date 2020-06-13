CREATE TABLE IF NOT EXISTS category(
    id SERIAL PRIMARY KEY,
    category_name VARCHAR(2),
    country_name VARCHAR(100)
);