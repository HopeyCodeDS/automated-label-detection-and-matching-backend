CREATE TABLE orderlines (
                            id VARCHAR(50) PRIMARY KEY,
                            product_code VARCHAR(50),
                            batch_number VARCHAR(50),
                            product_description TEXT,
                            processed BOOLEAN DEFAULT FALSE
);

-- TRUNCATE TABLE orderlines;

-- INSERT INTO orderlines (id, product_code, batch_number, product_description, processed)
-- SELECT * FROM (
--
--                VALUES ('ORDER1-7', '51-4766-65', '297429', 'KEPPRA DG 500MG 60TAB IT', false),
--                       ('ORDER1-8', '52-4767-66', '308530', 'KEPPRA DG 250MG 30TAB IT', false),
--                       ('ORDER1-9', '53-4768-67', '319631', 'KEPPRA DG 100MG 90TAB IT', false),
--                       ('ORDER1-10', '54-4769-68', '328732', 'KEPPRA DG 75MG 120TAB IT', false),
--                       ('ORDER1-11', '55-4770-69', '339833', 'KEPPRA DG 50MG 180TAB IT', false),
--                       ('ORDER1-12', '56-4771-70', '348934', 'KEPPRA DG 200MG 60TAB IT', false),
--                       ('ORDER1-13', '57-4772-71', '359035', 'KEPPRA DG 150MG 90TAB IT', false),
--                       ('ORDER1-14', '58-4773-72', '369136', 'KEPPRA DG 125MG 120TAB IT', false),
--                       ('ORDER1-15', '59-4774-73', '379237', 'KEPPRA DG 225MG 60TAB IT', false),
--                       ('ORDER1-16', '60-4775-74', '389338', 'KEPPRA DG 175MG 90TAB IT', false),
--                       ('ORDER1-17', '61-4776-75', '399439', 'KEPPRA DG 275MG 60TAB IT', false),
--                       ('ORDER1-18', '62-4777-76', '409540', 'KEPPRA DG 100MG 120TAB IT', false),
--                       ('ORDER1-19', '63-4778-77', '419641', 'KEPPRA DG 200MG 180TAB IT', false),
--                       ('ORDER1-20', '64-4779-78', '429742', 'KEPPRA DG 150MG 180TAB IT', false),
--                       ('ORDER1-21', '65-4780-79', '439843', 'KEPPRA DG 250MG 120TAB IT', false),
--                       ('ORDER1-22', '66-4781-80', '449944', 'KEPPRA DG 300MG 60TAB IT', false)
--                )
--     AS new_data(id, product_code, batch_number, product_description, processed)
--     WHERE NOT EXISTS (SELECT 1 FROM orderlines);


INSERT INTO orderlines (id, product_code, batch_number, product_description, processed)

  VALUES ('ORDER1-7', '51-4766-65', '297429', 'KEPPRA DG 500MG 60TAB IT', false),
         ('ORDER1-8', '52-4767-66', '308530', 'KEPPRA DG 250MG 30TAB IT', false),
         ('ORDER1-9', '53-4768-67', '319631', 'KEPPRA DG 100MG 90TAB IT', false),
         ('ORDER1-10', '54-4769-68', '328732', 'KEPPRA DG 75MG 120TAB IT', false),
         ('ORDER1-11', '55-4770-69', '339833', 'KEPPRA DG 50MG 180TAB IT', false),
         ('ORDER1-12', '56-4771-70', '348934', 'KEPPRA DG 200MG 60TAB IT', false),
         ('ORDER1-13', '57-4772-71', '359035', 'KEPPRA DG 150MG 90TAB IT', false),
         ('ORDER1-14', '58-4773-72', '369136', 'KEPPRA DG 125MG 120TAB IT', false),
         ('ORDER1-15', '59-4774-73', '379237', 'KEPPRA DG 225MG 60TAB IT', false),
         ('ORDER1-16', '60-4775-74', '389338', 'KEPPRA DG 175MG 90TAB IT', false);