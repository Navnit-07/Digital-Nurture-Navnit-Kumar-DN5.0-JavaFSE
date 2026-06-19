-- Exercise 1 - Scenario 2: Set IsVIP to TRUE for balance over $10,000

DECLARE
    CURSOR customer_cursor IS
        SELECT customer_id, balance FROM customers;

BEGIN
    FOR rec IN customer_cursor LOOP
        IF rec.balance > 10000 THEN
            UPDATE customers
            SET is_vip = 'TRUE'
            WHERE customer_id = rec.customer_id;

            DBMS_OUTPUT.PUT_LINE('Customer ID ' || rec.customer_id || ' promoted to VIP');
        END IF;
    END LOOP;

    COMMIT;
END;
/
