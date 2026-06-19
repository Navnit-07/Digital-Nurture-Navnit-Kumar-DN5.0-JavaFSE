-- Setup for stored procedure exercises

CREATE TABLE savings_accounts (
    account_id NUMBER PRIMARY KEY,
    balance    NUMBER(10, 2)
);

CREATE TABLE employees (
    emp_id     NUMBER PRIMARY KEY,
    emp_name   VARCHAR2(100),
    department VARCHAR2(50),
    salary     NUMBER(10, 2)
);

CREATE TABLE accounts (
    account_id NUMBER PRIMARY KEY,
    balance    NUMBER(10, 2)
);

INSERT INTO savings_accounts VALUES (1, 5000);
INSERT INTO savings_accounts VALUES (2, 12000);
INSERT INTO savings_accounts VALUES (3, 800);

INSERT INTO employees VALUES (1, 'Ravi', 'IT', 50000);
INSERT INTO employees VALUES (2, 'Priya', 'IT', 55000);
INSERT INTO employees VALUES (3, 'Kumar', 'HR', 40000);

INSERT INTO accounts VALUES (101, 5000);
INSERT INTO accounts VALUES (102, 3000);

COMMIT;
