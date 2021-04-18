CREATE TABLE IF NOT EXISTS b_item (
    id uuid PRIMARY KEY,
    cost integer NOT NULL,
    name varchar(255) UNIQUE NOT NULL
)

CREATE TABLE IF NOT EXISTS b_client (
    id uuid PRIMARY KEY,
    phone varchar(255) NOT NULL,
    name varchar(255),
    address varchar(255) NOT NULL
)

CREATE TABLE IF NOT EXISTS b_order (
    id uuid PRIMARY KEY,
    client_id uuid REFERENCES b_client(id),
    price integer NOT NULL
)

CREATE TABLE IF NOT EXISTS b_order_items (
    order_id uuid PRIMARY KEY REFERENCES b_order(id),
    item_id uuid PRIMARY KEY REFERENCES b_item(id),
    CONSTRAINT b_order_items_pkey PRIMARY KEY (order_id, item_id),

    amount integer NOT NULL
)