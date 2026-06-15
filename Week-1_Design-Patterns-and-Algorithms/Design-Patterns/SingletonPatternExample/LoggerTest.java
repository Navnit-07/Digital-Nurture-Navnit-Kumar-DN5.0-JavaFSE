public class LoggerTest {

    public static void main(String[] args) {

        // Get the logger — creates it for the first time
        Logger loggerA = Logger.getInstance();
        loggerA.log("First message");

        // Get the logger again — no new object is created
        Logger loggerB = Logger.getInstance();
        loggerB.log("Second message");

        // Are they the same object?
        if (loggerA == loggerB) {
            System.out.println("Same instance! Singleton works.");
        } else {
            System.out.println("Different instances. Something went wrong.");
        }
    }
}