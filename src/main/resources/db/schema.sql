-- Init table for user
CREATE TABLE IF NOT EXISTS user (
       id BIGINT AUTO_INCREMENT PRIMARY KEY,
       username VARCHAR(50) UNIQUE NOT NULL,
       created_at TIMESTAMP,
       updated_at TIMESTAMP,
       created_by VARCHAR(50),
       updated_by VARCHAR(50)
);

-- Init table for wallet
CREATE TABLE wallet (
     id BIGINT AUTO_INCREMENT PRIMARY KEY,
     user_id BIGINT NOT NULL,
     currency_id BIGINT NOT NULL,
     balance DECIMAL(18, 8) NOT NULL DEFAULT 0.0,
     created_at TIMESTAMP,
     updated_at TIMESTAMP,
     created_by VARCHAR(50),
     updated_by VARCHAR(50),
     FOREIGN KEY (user_id) REFERENCES user(id),
     FOREIGN KEY (currency_id) REFERENCES currency(id),
);

-- Init table for transaction
CREATE TABLE transaction (
        id BIGINT AUTO_INCREMENT PRIMARY KEY,
        user_id BIGINT NOT NULL,
        type VARCHAR(10) NOT NULL CHECK (type IN ('ASK', 'BID')),
        trading_pair_id BIGINT NOT NULL,
        price DECIMAL(18, 8) NOT NULL,
        quantity DECIMAL(18, 8) NOT NULL,
        created_at TIMESTAMP,
        updated_at TIMESTAMP,
        created_by VARCHAR(50),
        updated_by VARCHAR(50),
        FOREIGN KEY (user_id) REFERENCES users(id),
        FOREIGN KEY (trading_pair_id) REFERENCES trading_pair(id),
);

-- Init table trading pair
CREATE TABLE trading_pair (
       id BIGINT AUTO_INCREMENT PRIMARY KEY,
       base_currency_id BIGINT NOT NULL,
       quote_currency_id BIGINT NOT NULL,
       bid_price DECIMAL(18, 8) NOT NULL,
       ask_price DECIMAL(18, 8) NOT NULL,
       created_at TIMESTAMP,
       updated_at TIMESTAMP,
       created_by VARCHAR(50),
       updated_by VARCHAR(50),
       FOREIGN KEY (base_currency_id) REFERENCES currency(id),
       FOREIGN KEY (quote_currency_id) REFERENCES currency(id),
);

-- Init table for currency (ETH, BTC, USDT)
CREATE TABLE currency (
       id BIGINT AUTO_INCREMENT PRIMARY KEY,
       name VARCHAR(10) NOT NULL CHECK (name IN ('ETH', 'BTC', 'USDT')),
       created_at TIMESTAMP,
       updated_at TIMESTAMP,
       created_by VARCHAR(50),
       updated_by VARCHAR(50)
);


