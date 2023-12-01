CREATE VIEW Invoice_Detail_view AS
SELECT
    D.id AS detail_id,
    D.quantity,
    D.price AS detail_price,
    I.id AS invoice_id,
    I.code AS invoice_code,
    I.create_at AS invoice_create_at,
    C.full_name AS full_name_client, -- Cambiado a "full_name"
    P.id AS product_id,
    P.description AS product_description,
    P.brand AS product_brand,
    P.price AS product_price,
    P.stock AS product_stock
FROM Detail D
         JOIN Invoice I ON D.invoice_id = I.id
         JOIN Client C ON I.client_id = C.id
         JOIN Product P ON D.product_id = P.id;

