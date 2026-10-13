DROP TABLE IF EXISTS prices;
CREATE TABLE prices
(
    brand_id   BIGINT         NOT NULL,
    start_date TIMESTAMP      NOT NULL,
    end_date   TIMESTAMP      NOT NULL,
    price_list BIGINT         NOT NULL PRIMARY KEY,
    product_id BIGINT         NOT NULL,
    priority   INTEGER        NOT NULL,
    price      NUMERIC(10, 2) NOT NULL,
    curr       CHAR(3)        NOT NULL
);