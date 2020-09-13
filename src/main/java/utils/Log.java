package utils;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Loggers to be used while scripting for infomational purpose.
 * info  > informational messages
 * error > erroneous messages
 * warn  > test step warning messages
 * pass  > test step pass messages
 * fail  > test fail pass messages
 */
public class Log {
    private static Logger Log = LogManager.getLogger(Log.class);

    public static void info(String message) {
        Log.info(message);
    }

    public static void error(String message) {
        Log.error(message);

    }
    public static void logTestStep(String step) {
        Log.info(step);
    }

    public static void warn(String message) {
        Log.warn(message);
    }

    public static void pass(String message) {
        Log.log(Level.forName("PASS", 350), message);
    }

    public static void fail(String message) {
        Log.log(Level.forName("FAIL", 375), message);
    }

}
