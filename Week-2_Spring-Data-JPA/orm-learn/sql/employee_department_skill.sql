-- Tables for O/R Mapping demo (Department, Employee, Skill)
-- Run in ormlearn schema. Or use spring.jpa.hibernate.ddl-auto=update

CREATE TABLE IF NOT EXISTS department (
    dp_id   INT AUTO_INCREMENT PRIMARY KEY,
    dp_name VARCHAR(50)
);

CREATE TABLE IF NOT EXISTS skill (
    sk_id   INT AUTO_INCREMENT PRIMARY KEY,
    sk_name VARCHAR(50)
);

-- Drop old simple employee table if needed, then create new one
DROP TABLE IF EXISTS employee_skill;
DROP TABLE IF EXISTS employee;

CREATE TABLE employee (
    em_id            INT AUTO_INCREMENT PRIMARY KEY,
    em_name          VARCHAR(50),
    em_salary        DOUBLE,
    em_permanent     BOOLEAN,
    em_date_of_birth DATE,
    em_dp_id         INT,
    CONSTRAINT fk_emp_dept FOREIGN KEY (em_dp_id) REFERENCES department(dp_id)
);

CREATE TABLE employee_skill (
    es_em_id INT,
    es_sk_id INT,
    PRIMARY KEY (es_em_id, es_sk_id),
    CONSTRAINT fk_es_emp FOREIGN KEY (es_em_id) REFERENCES employee(em_id),
    CONSTRAINT fk_es_sk FOREIGN KEY (es_sk_id) REFERENCES skill(sk_id)
);

INSERT INTO department (dp_name) VALUES ('HR');
INSERT INTO department (dp_name) VALUES ('IT');
INSERT INTO department (dp_name) VALUES ('Finance');

INSERT INTO skill (sk_name) VALUES ('Java');
INSERT INTO skill (sk_name) VALUES ('Spring');
INSERT INTO skill (sk_name) VALUES ('SQL');

INSERT INTO employee (em_name, em_salary, em_permanent, em_date_of_birth, em_dp_id)
VALUES ('Ravi', 50000, true, '1995-05-10', 2);
INSERT INTO employee (em_name, em_salary, em_permanent, em_date_of_birth, em_dp_id)
VALUES ('Priya', 60000, true, '1992-08-15', 2);
INSERT INTO employee (em_name, em_salary, em_permanent, em_date_of_birth, em_dp_id)
VALUES ('Kumar', 35000, false, '1998-01-20', 1);
INSERT INTO employee (em_name, em_salary, em_permanent, em_date_of_birth, em_dp_id)
VALUES ('Anita', 75000, true, '1990-03-05', 3);
INSERT INTO employee (em_name, em_salary, em_permanent, em_date_of_birth, em_dp_id)
VALUES ('Rahul', 45000, false, '1996-11-12', 2);

INSERT INTO employee_skill VALUES (1, 1);
INSERT INTO employee_skill VALUES (1, 2);
INSERT INTO employee_skill VALUES (2, 1);
INSERT INTO employee_skill VALUES (2, 3);
INSERT INTO employee_skill VALUES (4, 2);
