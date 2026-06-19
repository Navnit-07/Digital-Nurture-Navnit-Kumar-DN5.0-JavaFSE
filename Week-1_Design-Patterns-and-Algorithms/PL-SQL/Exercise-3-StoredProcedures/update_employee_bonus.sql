-- Exercise 3 - Scenario 2: Update employee bonus by department

CREATE OR REPLACE PROCEDURE UpdateEmployeeBonus(
    p_department     IN VARCHAR2,
    p_bonus_percent  IN NUMBER
) AS
BEGIN
    UPDATE employees
    SET salary = salary + (salary * p_bonus_percent / 100)
    WHERE department = p_department;

    COMMIT;
    DBMS_OUTPUT.PUT_LINE('Bonus of ' || p_bonus_percent || '% applied to ' || p_department || ' department.');
END UpdateEmployeeBonus;
/

-- To run: EXEC UpdateEmployeeBonus('IT', 10);
