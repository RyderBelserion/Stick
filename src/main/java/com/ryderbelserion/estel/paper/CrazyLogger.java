package com.ryderbelserion.estel.paper;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import com.ryderbelserion.estel.paper.utils.AdventureUtils;

import java.util.function.Supplier;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

public class CrazyLogger {

    private static InternalLogger InternalLogger;

    /**
     * Set the name of the logger and create the logger instance
     *
     * @param name string
     */
    public static void setName(String name) {
        InternalLogger = new InternalLogger(name);
    }

    /**
     * @return the logger instance
     */
    public static InternalLogger getLogger() {
        return InternalLogger;
    }

    /**
     * Sends a debug message.
     *
     * @param message message to send
     */
    public static void debug(String message) {
        info("<yellow>[DEBUG]</yellow> " + message);
    }

    /**
     * Sends a debug message.
     *
     * @param message message to send
     * @param exception exception to check
     */
    public static void debug(String message, Exception exception) {
        info("<yellow>[DEBUG]</yellow> " + message);

        if (exception != null) info("<yellow>[DEBUG]</yellow> " + exception.getMessage());
    }

    /**
     * Sends an info message.
     *
     * @param message message to send
     */
    public static void info(String message) {
        InternalLogger.info(message);
    }

    /**
     * Sends a warning message.
     *
     * @param message message to send
     */
    public static void warn(String message) {
        InternalLogger.warning(message);
    }

    /**
     * Sends a severe message.
     *
     * @param message message to send
     */
    public static void severe(String message) {
        InternalLogger.severe(message);
    }

    /**
     * The internal logger class that does all the fancy magic.
     */
    public static class InternalLogger extends Logger {

        /**
         * Constructor to build the internal logger.
         *
         * @param loggerName the name of the logger
         */
        public InternalLogger(String loggerName) {
            super(loggerName.toLowerCase(), null);
        }

        /**
         * Sends a message through getConsole() with adventure support.
         * Allows us to strip colors before being thrown in a log file due to sending through console sender.
         *
         * @param message the string message (or a key in the message catalog)
         */
        @Override
        public void info(String message) {
            Component msg = AdventureUtils.parse(CrazyCore.getProjectPrefix() + message, false);

            Bukkit.getConsoleSender().sendMessage(msg);
        }

        @Override
        public void warning(String message) {
            String prefix = CrazyCore.getProjectPrefix();

            Component msg = AdventureUtils.parse(prefix + "<yellow>" + message + "</yellow>", false);

            Bukkit.getConsoleSender().sendMessage(msg);
        }

        @Override
        public void severe(String message) {
            String prefix = CrazyCore.getProjectPrefix();

            Component msg = AdventureUtils.parse(prefix + "<red>" + message + "</red>", false);

            Bukkit.getConsoleSender().sendMessage(msg);
        }

        /**
         * Sends a message with desired log message.
         *
         * @param msgSupplier a function, which when called, produces the desired log message
         */
        @Override
        public void info(Supplier<String> msgSupplier) {
            info(msgSupplier.get());
        }

        /**
         * @param record supplied log record
         */
        private void doLog(LogRecord record) {
            if (record.getLevel() == Level.INFO) {
                info(record.getMessage());
                return;
            }

            record.setLoggerName(getName());

            log(record);
        }

        /**
         * @param level one of the message level identifiers, e.g., SEVERE
         * @param msg string message (or a key in the message catalog)
         */
        @Override
        public void log(Level level, String msg) {
            if (!isLoggable(level)) return;

            LogRecord record = new LogRecord(level, msg);

            doLog(record);
        }

        /**
         * @param level one of the message level identifiers, e.g., SEVERE
         * @param msgSupplier a function, which when called, produces the desired log message
         */
        @Override
        public void log(Level level, Supplier<String> msgSupplier) {
            if (!isLoggable(level)) return;

            LogRecord record = new LogRecord(level, msgSupplier.get());

            doLog(record);
        }

        /**
         * @param level one of the message level identifiers, e.g., SEVERE
         * @param msg string message (or a key in the message catalog)
         * @param param1 parameter to the message
         */
        @Override
        public void log(Level level, String msg, Object param1) {
            if (!isLoggable(level)) return;

            LogRecord record = new LogRecord(level, msg);

            Object[] params = {param1};

            record.setParameters(params);

            doLog(record);
        }

        /**
         * @param level one of the message level identifiers, e.g., SEVERE
         * @param msg string message (or a key in the message catalog)
         * @param params array of parameters to the message
         */
        @Override
        public void log(Level level, String msg, Object[] params) {
            if (!isLoggable(level)) return;

            LogRecord record = new LogRecord(level, msg);

            record.setParameters(params);

            doLog(record);
        }

        /**
         * @param level one of the message level identifiers, e.g., SEVERE
         * @param msg  the string message (or a key in the message catalog)
         * @param thrown throwable associated with log message.
         */
        @Override
        public void log(Level level, String msg, Throwable thrown) {
            if (!isLoggable(level)) return;

            LogRecord record = new LogRecord(level, msg);

            record.setThrown(thrown);

            doLog(record);
        }

        /**
         * @param level one of the message level identifiers, e.g., SEVERE
         * @param thrown throwable associated with log message.
         * @param msgSupplier a function, which when called, produces the desired log message
         */
        @Override
        public void log(Level level, Throwable thrown, Supplier<String> msgSupplier) {
            if (!isLoggable(level)) return;

            LogRecord record = new LogRecord(level, msgSupplier.get());

            record.setThrown(thrown);

            doLog(record);
        }

        /**
         * @param sourceClass name of class that issued the logging request
         * @param sourceMethod name of the method.
         * @param thrown throwable that is being thrown.
         */
        @Override
        public void throwing(String sourceClass, String sourceMethod, Throwable thrown) {
            if (!isLoggable(Level.FINER)) return;

            LogRecord record = new LogRecord(Level.FINER, "THROW");

            record.setSourceClassName(sourceClass);
            record.setSourceMethodName(sourceMethod);
            record.setThrown(thrown);

            doLog(record);
        }
    }
}