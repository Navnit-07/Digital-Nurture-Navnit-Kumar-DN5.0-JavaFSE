-- Setup script: create tables needed for PL/SQL exercises
-- Run this first before other scripts

CREATE TABLE customers (
    customer_id   NUMBER PRIMARY KEY,
    customer_name VARCHAR2(100),
    age           NUMBER,
    balance       NUMBER(10, 2),
    is_vip        VARCHAR2(5) DEFAULT 'FALSE'
);

CREATE TABLE loans (
    loan_id       NUMBER PRIMARY KEY,
    customer_id   NUMBER,
    interest_rate NUMBER(5, 2),
    due_date      DATE,
    CONSTRAINT fk_loan_customer FOREIGN KEY (customer_id) REFERENCES customers(customer_id)
);

-- Sample data
INSERT INTO customers VALUES (1, 'John Smith', 65, 15000, 'FALSE');
INSERT INTO customers VALUES (2, 'Jane Doe', 45, 8000, 'FALSE');
INSERT INTO customers VALUES (3, 'Bob Wilson', 72, 25000, 'FALSE');
INSERT INTO customers VALUES (4, 'Alice Brown', 30, 12000, 'FALSE');

INSERT INTO loans VALUES (101, 1, 8.5, SYSDATE + 15);
INSERT INTO loans VALUES (102, 2, 7.0, SYSDATE + 45);
INSERT INTO loans VALUES (103, 3, 9.0, SYSDATE + 10);
INSERT INTO loans VALUES (104, 4, 6.5, SYSDATE + 25);

COMMIT;
