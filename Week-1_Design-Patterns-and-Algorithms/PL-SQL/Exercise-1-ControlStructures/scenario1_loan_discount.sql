-- Exercise 1 - Scenario 1: Apply 1% discount on loan interest for customers above 60

DECLARE
    CURSOR customer_cursor IS
        SELECT customer_id, age FROM customers;

    v_customer_id customers.customer_id%TYPE;
    v_age         customers.age%TYPE;
BEGIN
    FOR rec IN customer_cursor LOOP
        IF rec.age > 60 THEN
            UPDATE loans
            SET interest_rate = interest_rate - 1
            WHERE customer_id = rec.customer_id;

            DBMS_OUTPUT.PUT_LINE('Discount applied for customer ID: ' || rec.customer_id);
        END IF;
    END LOOP;

    COMMIT;
END;
/
