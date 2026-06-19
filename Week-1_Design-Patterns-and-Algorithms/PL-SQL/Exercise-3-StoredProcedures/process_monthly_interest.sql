-- Exercise 3 - Scenario 1: Process monthly interest for savings accounts (1%)

CREATE OR REPLACE PROCEDURE ProcessMonthlyInterest AS
BEGIN
    UPDATE savings_accounts
    SET balance = balance + (balance * 0.01);

    COMMIT;
    DBMS_OUTPUT.PUT_LINE('Monthly interest applied to all savings accounts.');
END ProcessMonthlyInterest;
/

-- To run: EXEC ProcessMonthlyInterest;
