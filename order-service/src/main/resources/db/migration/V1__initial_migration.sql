CREATE TABLE order_items
(
    id           UUID         NOT NULL,
    order_id     UUID         NOT NULL,
    sku_code     VARCHAR(255) NOT NULL,
    quantity     INTEGER,
    total_amount DECIMAL,
    CONSTRAINT pk_order_items PRIMARY KEY (id)
);

CREATE TABLE orders
(
    id           UUID NOT NULL,
    total_amount DECIMAL,
    order_number VARCHAR(255),
    user_id      UUID NOT NULL,
    CONSTRAINT pk_orders PRIMARY KEY (id)
);

ALTER TABLE order_items
    ADD CONSTRAINT FK_ORDER_ITEMS_ON_ORDER FOREIGN KEY (order_id) REFERENCES orders (id);