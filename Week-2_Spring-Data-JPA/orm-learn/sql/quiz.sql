-- Quiz schema for Hands-on 3 (run in ormlearn)

CREATE TABLE IF NOT EXISTS user (
    us_id    INT AUTO_INCREMENT PRIMARY KEY,
    us_name  VARCHAR(50),
    us_email VARCHAR(50)
);

CREATE TABLE IF NOT EXISTS question (
    qt_id   INT AUTO_INCREMENT PRIMARY KEY,
    qt_text VARCHAR(200)
);

CREATE TABLE IF NOT EXISTS options (
    op_id    INT AUTO_INCREMENT PRIMARY KEY,
    op_qt_id INT,
    op_score DOUBLE,
    op_text  VARCHAR(100),
    CONSTRAINT fk_op_qt FOREIGN KEY (op_qt_id) REFERENCES question(qt_id)
);

CREATE TABLE IF NOT EXISTS attempt (
    at_id    INT AUTO_INCREMENT PRIMARY KEY,
    at_date  DATETIME,
    at_us_id INT,
    at_score DOUBLE,
    CONSTRAINT fk_at_us FOREIGN KEY (at_us_id) REFERENCES user(us_id)
);

CREATE TABLE IF NOT EXISTS attempt_question (
    aq_id    INT AUTO_INCREMENT PRIMARY KEY,
    aq_at_id INT,
    aq_qt_id INT,
    CONSTRAINT fk_aq_at FOREIGN KEY (aq_at_id) REFERENCES attempt(at_id),
    CONSTRAINT fk_aq_qt FOREIGN KEY (aq_qt_id) REFERENCES question(qt_id)
);

CREATE TABLE IF NOT EXISTS attempt_option (
    ao_id       INT AUTO_INCREMENT PRIMARY KEY,
    ao_aq_id    INT,
    ao_op_id    INT,
    ao_selected BOOLEAN,
    CONSTRAINT fk_ao_aq FOREIGN KEY (ao_aq_id) REFERENCES attempt_question(aq_id),
    CONSTRAINT fk_ao_op FOREIGN KEY (ao_op_id) REFERENCES options(op_id)
);

INSERT INTO user (us_name, us_email) VALUES ('John', 'john@example.com');

INSERT INTO question (qt_text) VALUES
('What is the extension of the hyper text markup language file?'),
('What is the maximum level of heading tag can be used in a HTML page?'),
('The HTML document itself begins with <html> and ends </html>. State True of False'),
('Choose the right option to store text value value in a variable');

INSERT INTO options (op_qt_id, op_score, op_text) VALUES
(1, 0.0, '.xhtm'), (1, 0.0, '.ht'), (1, 1.0, '.html'), (1, 0.0, '.htmx'),
(2, 0.0, '5'), (2, 0.0, '3'), (2, 0.0, '4'), (2, 1.0, '6'),
(3, 0.0, 'false'), (3, 1.0, 'true'),
(4, 0.5, '''John'''), (4, 0.0, 'John'), (4, 0.5, '"John"'), (4, 0.0, '/John/');

INSERT INTO attempt (at_date, at_us_id, at_score) VALUES ('2026-06-01 10:00:00', 1, 3.5);

INSERT INTO attempt_question (aq_at_id, aq_qt_id) VALUES (1, 1), (1, 2), (1, 3), (1, 4);

-- Q1: selected .html (op 3)
INSERT INTO attempt_option (ao_aq_id, ao_op_id, ao_selected) VALUES
(1, 1, false), (1, 2, false), (1, 3, true), (1, 4, false);
-- Q2: selected 3 (op 6) - wrong; correct is 6
INSERT INTO attempt_option (ao_aq_id, ao_op_id, ao_selected) VALUES
(2, 5, false), (2, 6, true), (2, 7, false), (2, 8, false);
-- Q3: selected true
INSERT INTO attempt_option (ao_aq_id, ao_op_id, ao_selected) VALUES
(3, 9, false), (3, 10, true);
-- Q4: selected 'John'
INSERT INTO attempt_option (ao_aq_id, ao_op_id, ao_selected) VALUES
(4, 11, true), (4, 12, false), (4, 13, false), (4, 14, false);
