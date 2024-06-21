CREATE TABLE Account
(
    id            INT AUTO_INCREMENT PRIMARY KEY,
    owner         VARCHAR(255)   NOT NULL,
    balance       DECIMAL(10, 2) NOT NULL,
    role_id       INT,
    created_at    TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at    TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE User_Password
(
    username     VARCHAR(40),
    password_hash VARCHAR(255),
    salt          VARCHAR(50),
    account_id    INT,
    FOREIGN KEY (account_id) REFERENCES Account (id)
);

CREATE TABLE City
(
    id        INT AUTO_INCREMENT PRIMARY KEY,
    name      VARCHAR(100) UNIQUE
);

CREATE TABLE Product
(
    id          INT AUTO_INCREMENT PRIMARY KEY,
    name        VARCHAR(255)   NOT NULL,
    description TEXT           NOT NULL,
    price       DECIMAL(10, 2) NOT NULL
);

CREATE TABLE Keyword
(
    id    INT AUTO_INCREMENT PRIMARY KEY,
    name  VARCHAR(255) NOT NULL
);

CREATE TABLE Campaign
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
    FOREIGN KEY (product_id) REFERENCES Product (id),
    FOREIGN KEY (account_id) REFERENCES Account (id),
    FOREIGN KEY (city_id) REFERENCES City (id)
);

CREATE TABLE Campaign_Keyword
(
    campaign_id INT,
    keyword_id  INT,
    FOREIGN KEY (campaign_id) REFERENCES Campaign (id),
    FOREIGN KEY (keyword_id) REFERENCES Keyword (id),
    PRIMARY KEY (campaign_id, keyword_id)
);


