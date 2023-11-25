package Extensions;

import java.io.IOException;
import java.util.logging.*;

import static Utilities.CommonOperations.getData;

public class FileLogger {
    private static final Logger logger = Logger.getLogger(FileLogger.class.getName());

    static {
        try {
            // Set up a FileHandler to write logs to a file
            FileHandler fileHandler = new FileHandler(getData("logPath") + "test.log");
            fileHandler.setFormatter(new SimpleFormatter());
            logger.addHandler(fileHandler);

            // Set the logger level to capture messages of all levels
            logger.setLevel(Level.ALL);

            // Set the level of the file handler to capture messages of all levels
            fileHandler.setLevel(Level.ALL);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void info(String message) {
        logger.info(addMetaData(message));
    }

    public static void error(String message) {
        logger.severe(addMetaData(message));
    }

    public static void error(String message, Throwable throwable) {
        logger.log(Level.SEVERE, addMetaData(message), throwable);
    }

    public static void debug(String message) {
        logger.fine(addMetaData(message));
    }

    public static void noMatch(String expected, String actual) {
        String logMessage = String.format("No match. Expected '%s', Actual '%s'", expected, actual);
        logger.info(addMetaData(logMessage));
    }

    private FileLogger() {
    }

    private static String addMetaData(String message) {
        String metaData = "[" + ProcessHandle.current().pid() + " " + Thread.currentThread().getName() + "] " + message;
        return metaData;
    }
}
