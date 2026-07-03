-- Sample products for Criteria Query demo

CREATE TABLE IF NOT EXISTS product (
    id                INT AUTO_INCREMENT PRIMARY KEY,
    name              VARCHAR(100),
    customer_review   DOUBLE,
    hard_disk_size    VARCHAR(20),
    ram_size          VARCHAR(20),
    cpu_speed         VARCHAR(20),
    operating_system  VARCHAR(50),
    weight            DOUBLE,
    cpu               VARCHAR(50)
);

INSERT INTO product (name, customer_review, hard_disk_size, ram_size, cpu_speed, operating_system, weight, cpu)
VALUES
('Dell Inspiron Laptop', 4.2, '512GB', '8GB', '2.4GHz', 'Windows', 1.8, 'Intel i5'),
('HP Pavilion Laptop', 4.5, '1TB', '16GB', '3.0GHz', 'Windows', 2.0, 'Intel i7'),
('MacBook Air Laptop', 4.8, '256GB', '8GB', '3.2GHz', 'macOS', 1.2, 'Apple M1'),
('Lenovo IdeaPad Laptop', 3.9, '512GB', '8GB', '2.0GHz', 'Windows', 1.9, 'AMD Ryzen 5'),
('Asus VivoBook Laptop', 4.0, '256GB', '4GB', '2.2GHz', 'Windows', 1.7, 'Intel i3');
