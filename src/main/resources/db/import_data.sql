INSERT INTO currency (name) VALUES ('ETH'), ('BTC'), ('USDT');

-- Insert ETH/USDT and BTC/USDT pairs
INSERT INTO trading_pair (base_currency_id, quote_currency_id, bid_price, ask_price, symbol)
SELECT c1.id, c2.id, 3000.00, 3001.00, CONCAT(c1.name, c2.name) FROM currency c1, currency c2
WHERE c1.name IN ('ETH', 'BTC') AND c2.name = 'USDT';

INSERT INTO account (username) VALUES ('lamdnt2000'), ('h3nzyzzz');

INSERT INTO wallet (account_id, currency_id, balance) VALUES (1, 3, 50000), (2, 3, 50000);