-- Crear tabla Client
CREATE TABLE Client (
    id INT PRIMARY KEY,
    nui VARCHAR(50) UNIQUE,
    full_name VARCHAR(100),
    address VARCHAR(255)
);

-- Crear tabla Invoice
CREATE TABLE Invoice (
    id INT PRIMARY KEY,
    code VARCHAR(50) UNIQUE,
    create_at DATE  TIME,
    total DECIMAL(10, 2),
    client_id INT,
    FOREIGN KEY (client_id) REFERENCES Client(id)
);

-- Crear tabla Product
CREATE TABLE Product (
    id INT PRIMARY KEY,
    description VARCHAR(255),
    brand VARCHAR(50),
    price DECIMAL(10, 2),
    stock INT
);

-- Crear tabla Detail
CREATE TABLE Detail (
    id INT PRIMARY KEY,
    quantity INT,
    price DECIMAL(10, 2),
    invoice_id INT,
    product_id INT,
    FOREIGN KEY (invoice_id) REFERENCES Invoice(id),
    FOREIGN KEY (product_id) REFERENCES Product(id)
);
