import calculator.Calculator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

// Exercise 4: AAA pattern with @Before and @After
public class AAATest {

    private Calculator calculator;

    @Before
    public void setUp() {
        // Runs before each test - setup test fixture
        calculator = new Calculator();
        System.out.println("Setup: Calculator object created");
    }

    @After
    public void tearDown() {
        // Runs after each test - cleanup
        calculator = null;
        System.out.println("Teardown: Calculator object cleared");
    }

    @Test
    public void testAdd() {
        // Arrange
        int a = 10;
        int b = 5;

        // Act
        int result = calculator.add(a, b);

        // Assert
        assertEquals(15, result);
    }

    @Test
    public void testSubtract() {
        // Arrange
        int a = 10;
        int b = 3;

        // Act
        int result = calculator.subtract(a, b);

        // Assert
        assertEquals(7, result);
    }
}
