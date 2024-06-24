CREATE TABLE account
(
    id            UUID PRIMARY KEY,
    username      VARCHAR(20)   NOT NULL,
    password      VARCHAR(100)      NOT NULL,
    balance       DECIMAL(10, 2) NOT NULL,
    role_id       INT,
    created_at    TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE city
(
    id        UUID PRIMARY KEY,
    name      VARCHAR(100) UNIQUE
);

CREATE TABLE product
(
    id          UUID PRIMARY KEY,
    name        VARCHAR(255)   NOT NULL,
    description TEXT           NOT NULL,
    price       DECIMAL(10, 2) NOT NULL
);

CREATE TABLE keywords
(
    id    UUID PRIMARY KEY,
    name  VARCHAR(255) NOT NULL
);

CREATE TABLE campaign
(
    id            UUID PRIMARY KEY,
    product_id    UUID,
    account_id    UUID,
    name          VARCHAR(255)       NOT NULL,
    bid_amount    DECIMAL(10, 2)     NOT NULL CHECK (bid_amount >= 0.01),
    fund          DECIMAL(10, 2)     NOT NULL,
    status        ENUM ('ON', 'OFF') NOT NULL,
    radius        INT                NOT NULL,
    created_at    TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (product_id) REFERENCES product (id),
    FOREIGN KEY (account_id) REFERENCES account (id)
);

CREATE TABLE campaign_keyword
(
    campaign_id UUID,
    keyword_id  UUID,
    FOREIGN KEY (campaign_id) REFERENCES campaign (id),
    FOREIGN KEY (keyword_id) REFERENCES keywords (id),
    PRIMARY KEY (campaign_id, keyword_id)
);

CREATE TABLE campaign_city
(
    campaign_id UUID,
    city_id  UUID,
    FOREIGN KEY (campaign_id) REFERENCES campaign (id),
    FOREIGN KEY (city_id) REFERENCES city (id),
    PRIMARY KEY (campaign_id, city_id)
);

