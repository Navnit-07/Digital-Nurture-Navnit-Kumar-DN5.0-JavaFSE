public class Logger {

    private static Logger instance = null;

    private Logger() {
        System.out.println("Logger created!");
    }

    public static Logger getInstance() {
        if (instance == null) {
            instance = new Logger();
        }
        return instance;
    }

    // A simple method to log a message
    public void log(String message) {
        System.out.println("LOG: " + message);
    }
}