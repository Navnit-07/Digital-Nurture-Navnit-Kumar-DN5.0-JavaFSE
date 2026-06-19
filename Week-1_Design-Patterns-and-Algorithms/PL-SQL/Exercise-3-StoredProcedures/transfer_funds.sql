-- Exercise 3 - Scenario 3: Transfer funds between accounts

CREATE OR REPLACE PROCEDURE TransferFunds(
    p_from_account IN NUMBER,
    p_to_account   IN NUMBER,
    p_amount       IN NUMBER
) AS
    v_balance accounts.balance%TYPE;
BEGIN
    -- Check balance of source account
    SELECT balance INTO v_balance
    FROM accounts
    WHERE account_id = p_from_account
    FOR UPDATE;

    IF v_balance >= p_amount THEN
        UPDATE accounts SET balance = balance - p_amount WHERE account_id = p_from_account;
        UPDATE accounts SET balance = balance + p_amount WHERE account_id = p_to_account;

        COMMIT;
        DBMS_OUTPUT.PUT_LINE('Transfer of $' || p_amount || ' successful.');
    ELSE
        DBMS_OUTPUT.PUT_LINE('Transfer failed. Insufficient balance.');
    END IF;

EXCEPTION
    WHEN NO_DATA_FOUND THEN
        DBMS_OUTPUT.PUT_LINE('Account not found.');
END TransferFunds;
/

-- To run: EXEC TransferFunds(101, 102, 500);
