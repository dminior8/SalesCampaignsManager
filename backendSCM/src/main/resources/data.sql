-- Użytkownicy
--password: Admin123!
INSERT INTO account (id, username, password, balance, role_id, created_at) VALUES
                                                                           ('9fa84006-71d1-40ab-bf55-fae04b4c9308','jKowalski','$2a$10$kAdlfSJUwH7w2oooYJSLkunU0HXJM8BEHZtmmMamCHd2unCzweFFi' , 1000000.00, 0, CURRENT_TIMESTAMP),
                                                                           ('c0dcc065-88e9-4f47-8a78-b18954a2d2e6','aNowak','$2a$10$umQsHIC7TepfI9tAxlO5i.e2gEdiXJEIBq/Q5nahMijxEpwpUxbX2' , 200000.00, 1, CURRENT_TIMESTAMP);

-- Miasta
INSERT INTO city (id, name) VALUES
                                ('4df5a900-a1ec-4236-ad32-2a9141265e1e', 'Warszawa'),
                                ('e77531a4-dc2f-4685-9882-b0b70827f579', 'Kraków'),
                                ('6031a0a5-f0f7-436a-b8f7-5214dce2a33f', 'Łódź'),
                                ('8d8c6e9b-b245-4a0f-90bc-52fd81af528e', 'Wrocław'),
                                ('8d333e8a-512a-433b-a538-bc64ee69ba6e', 'Poznań');

-- Produkty
INSERT INTO product (id, name, description, price) VALUES
                                                       ('924410f4-196c-4fcb-8b6a-a0bcee4bbd73', 'Smartfon XYZ', 'Najlepszy smartfon na rynku z wieloma funkcjami.', 2999.99),
                                                       ('c3c87dc2-799c-4ac7-8965-74f3e267178a', 'Laptop ABC', 'Wydajny laptop do pracy i gier.', 4999.99),
                                                       ('ff170ac5-a55f-41d5-9b75-83675d521255', 'Telewizor 4K', 'Telewizor z rozdzielczością 4K i świetną jakością obrazu.', 1999.99);

-- Słowa kluczowe
INSERT INTO keywords (id, name) VALUES
                                   ('8d15eb3c-123b-4dc4-8877-f3e7d3cc80fe', 'elektronika'),
                                   ('522c3354-a71a-4d35-8f53-e69c083eb231', 'sprzęt AGD'),
                                   ('b69d9519-5d0d-48a9-ae0a-b9c932b36b75', 'komputery'),
                                   ('9171de71-5f50-4bc5-afad-78598c3404bb', 'telefony'),
                                   ('4dba4a52-0b93-4b70-b416-6c8033ea8752', 'telewizory');

-- Kampanie
INSERT INTO campaign (id, product_id, account_id, name, bid_amount, fund, status, radius, created_at) VALUES
                                                                                                                            ('705cb92c-d692-4e18-b732-d78b64c9cc60', 'c3c87dc2-799c-4ac7-8965-74f3e267178a', 'c0dcc065-88e9-4f47-8a78-b18954a2d2e6', 'Kampania Laptop ABC', 3.00, 50000.00, 'ON', 10, CURRENT_TIMESTAMP),
                                                                                                                            ('ccb015a5-b09f-4e4d-aa8a-452a5201061a', '924410f4-196c-4fcb-8b6a-a0bcee4bbd73', '9fa84006-71d1-40ab-bf55-fae04b4c9308', 'Kampania Smartfon XYZ', 4.00, 10000.00, 'OFF', 15, CURRENT_TIMESTAMP),
                                                                                                                            ('0d76ce65-9c86-4724-92af-e0a8da20c245', 'ff170ac5-a55f-41d5-9b75-83675d521255', 'c0dcc065-88e9-4f47-8a78-b18954a2d2e6', 'Kampania TV', 2.50, 100000.00, 'ON', 150, CURRENT_TIMESTAMP),
                                                                                                                            ('4cb12643-9d95-4a62-b275-318418dccf3d', 'ff170ac5-a55f-41d5-9b75-83675d521255', 'c0dcc065-88e9-4f47-8a78-b18954a2d2e6', 'Kampania TV', 1.50, 15000.00, 'OFF', 80, CURRENT_TIMESTAMP),
                                                                                                                            ('38db36d9-75f5-4072-aa44-95cb2d53d9ea', 'ff170ac5-a55f-41d5-9b75-83675d521255', 'c0dcc065-88e9-4f47-8a78-b18954a2d2e6', 'Kampania TV', 3.50, 210000.00, 'ON', 20, CURRENT_TIMESTAMP);

-- Kampania-Słowa kluczowe
INSERT INTO campaign_keyword (campaign_id, keyword_id) VALUES
                                                           ('705cb92c-d692-4e18-b732-d78b64c9cc60', '8d15eb3c-123b-4dc4-8877-f3e7d3cc80fe'),
                                                           ('705cb92c-d692-4e18-b732-d78b64c9cc60', '9171de71-5f50-4bc5-afad-78598c3404bb'),
                                                           ('0d76ce65-9c86-4724-92af-e0a8da20c245', 'b69d9519-5d0d-48a9-ae0a-b9c932b36b75'),
                                                           ('0d76ce65-9c86-4724-92af-e0a8da20c245', '4dba4a52-0b93-4b70-b416-6c8033ea8752');
-- Kampania-Słowa kluczowe
INSERT INTO campaign_city (campaign_id, city_id) VALUES
                                                           ('705cb92c-d692-4e18-b732-d78b64c9cc60', '8d333e8a-512a-433b-a538-bc64ee69ba6e'),
                                                           ('ccb015a5-b09f-4e4d-aa8a-452a5201061a', 'e77531a4-dc2f-4685-9882-b0b70827f579'),
                                                           ('0d76ce65-9c86-4724-92af-e0a8da20c245', '6031a0a5-f0f7-436a-b8f7-5214dce2a33f'),
                                                           ('4cb12643-9d95-4a62-b275-318418dccf3d', 'e77531a4-dc2f-4685-9882-b0b70827f579'),
                                                           ('38db36d9-75f5-4072-aa44-95cb2d53d9ea', '4df5a900-a1ec-4236-ad32-2a9141265e1e');
