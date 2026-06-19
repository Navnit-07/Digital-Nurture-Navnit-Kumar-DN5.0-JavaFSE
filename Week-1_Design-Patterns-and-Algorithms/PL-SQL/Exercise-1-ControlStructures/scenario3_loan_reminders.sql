-- Exercise 1 - Scenario 3: Remind customers whose loans are due within 30 days

DECLARE
    CURSOR loan_cursor IS
        SELECT l.loan_id, c.customer_name, l.due_date
        FROM loans l
        JOIN customers c ON l.customer_id = c.customer_id
        WHERE l.due_date BETWEEN SYSDATE AND SYSDATE + 30;

BEGIN
    FOR rec IN loan_cursor LOOP
        DBMS_OUTPUT.PUT_LINE(
            'Reminder: Dear ' || rec.customer_name ||
            ', your loan (ID: ' || rec.loan_id ||
            ') is due on ' || TO_CHAR(rec.due_date, 'DD-MON-YYYY') ||
            '. Please make payment on time.'
        );
    END LOOP;
END;
/
