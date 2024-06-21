-- Użytkownicy
INSERT INTO account (id, owner, balance, role_id, created_at, updated_at) VALUES
                                                                           (1,'Jan Kowalski', 1000.00, 0, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
                                                                           (2,'Anna Nowak', 2000.00, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Hasła użytkowników (hasła są hashowane przy użyciu BCrypt)
INSERT INTO user_password (username, password_hash, salt, account_id) VALUES
                                                                       ('jkowalski', '$2a$10$7QJ8/SxK8xH6G1fpRZ8aZetY0aEYOHsmT3t8hY9x1U8LunY0zUHyG', 'randomSalt', 1),
                                                                       ('anowak', '$2a$10$T2dM9E5E8TpSZBEmz2zHu.WM2Ww7glwZT7wT4LC8RCJm5XTvOq.9K', 'randomSalt', 2);

-- Miasta
INSERT INTO city (id, name) VALUES
                                (1, 'Warszawa'),
                                (2, 'Kraków'),
                                (3, 'Łódź'),
                                (4, 'Wrocław'),
                                (5, 'Poznań');

-- Produkty
INSERT INTO product (id, name, description, price) VALUES
                                                       (1, 'Smartfon XYZ', 'Najlepszy smartfon na rynku z wieloma funkcjami.', 2999.99),
                                                       (2, 'Laptop ABC', 'Wydajny laptop do pracy i gier.', 4999.99),
                                                       (3, 'Telewizor 4K', 'Telewizor z rozdzielczością 4K i świetną jakością obrazu.', 1999.99);

-- Słowa kluczowe
INSERT INTO keyword (id, name) VALUES
                                   (1, 'elektronika'),
                                   (2, 'sprzęt AGD'),
                                   (3, 'komputery'),
                                   (4, 'telefony'),
                                   (5, 'telewizory');

-- Kampanie
INSERT INTO campaign (id, product_id, account_id, name, bid_amount, fund, status, city_id, radius, created_at, updated_at) VALUES
                                                                                                                            (1, 1, 1, 'Kampania Smartfon XYZ', 1.50, 500.00, 'ON', 1, 10, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
                                                                                                                            (2, 2, 2, 'Kampania Laptop ABC', 2.00, 1000.00, 'OFF', 3, 15, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Kampania-Słowa kluczowe
INSERT INTO campaign_keyword (campaign_id, keyword_id) VALUES
                                                           (1, 1),
                                                           (1, 4),
                                                           (2, 3),
                                                           (2, 5);
-- Kampania-Słowa kluczowe
INSERT INTO campaign_city (campaign_id, city_id) VALUES
                                                           (1, 1),
                                                           (1, 3),
                                                           (2, 3),
                                                           (2, 2);
