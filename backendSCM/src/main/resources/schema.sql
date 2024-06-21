CREATE TABLE account
(
    id            INT AUTO_INCREMENT PRIMARY KEY,
    owner         VARCHAR(255)   NOT NULL,
    balance       DECIMAL(10, 2) NOT NULL,
    role_id       INT,
    created_at    TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at    TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE user_password
(
    username     VARCHAR(40),
    password_hash VARCHAR(255),
    salt          VARCHAR(50),
    account_id    INT,
    FOREIGN KEY (account_id) REFERENCES account (id)
);

CREATE TABLE city
(
    id        INT AUTO_INCREMENT PRIMARY KEY,
    name      VARCHAR(100) UNIQUE
);

CREATE TABLE product
(
    id          INT AUTO_INCREMENT PRIMARY KEY,
    name        VARCHAR(255)   NOT NULL,
    description TEXT           NOT NULL,
    price       DECIMAL(10, 2) NOT NULL
);

CREATE TABLE keyword
(
    id    INT AUTO_INCREMENT PRIMARY KEY,
    name  VARCHAR(255) NOT NULL
);

CREATE TABLE campaign
(
    id            INT AUTO_INCREMENT PRIMARY KEY,
    product_id    INT,
    account_id    INT,
    name          VARCHAR(255)       NOT NULL,
    bid_amount    DECIMAL(10, 2)     NOT NULL CHECK (bid_amount >= 0.01),
    fund          DECIMAL(10, 2)     NOT NULL,
    status        ENUM ('ON', 'OFF') NOT NULL,
    city_id       INT,
    radius        INT                NOT NULL,
    created_at    TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at    TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (product_id) REFERENCES product (id),
    FOREIGN KEY (account_id) REFERENCES account (id),
    FOREIGN KEY (city_id) REFERENCES city (id)
);

CREATE TABLE campaign_keyword
(
    campaign_id INT,
    keyword_id  INT,
    FOREIGN KEY (campaign_id) REFERENCES campaign (id),
    FOREIGN KEY (keyword_id) REFERENCES keyword (id)
);

CREATE TABLE campaign_city
(
    campaign_id INT,
    city_id  INT,
    FOREIGN KEY (campaign_id) REFERENCES campaign (id),
    FOREIGN KEY (city_id) REFERENCES city (id),
    PRIMARY KEY (campaign_id, city_id)
);

