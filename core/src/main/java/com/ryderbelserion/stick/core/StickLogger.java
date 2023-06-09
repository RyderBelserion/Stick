package com.ryderbelserion.stick.core;

import com.ryderbelserion.stick.core.utils.AdventureUtils;
import java.util.function.Supplier;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

public class StickLogger {

    private static InternalLogger InternalLogger;

    public static void setName(String name) {
        InternalLogger = new InternalLogger(name);
    }

    public static InternalLogger getLogger() {
        return InternalLogger;
    }

    public static void debug(String message) {
        info("<yellow>[DEBUG]</yellow> " + message);
    }

    public static void debug(String message, Exception exception) {
        info("<yellow>[DEBUG]</yellow> " + message);

        if (exception != null) info("<yellow>[DEBUG]</yellow> " + exception.getMessage());
    }

    public static void info(String message) {
        InternalLogger.info(message);
    }

    public static void warn(String message) {
        InternalLogger.warning(message);
    }

    public static void severe(String message) {
        InternalLogger.severe(message);
    }

    public static class InternalLogger extends Logger {

        public InternalLogger(String loggerName) {
            super(loggerName.toLowerCase(), null);
        }

        @Override
        public void info(String message) {
            StickCore.api().getConsole().send(StickCore.api().getProjectPrefix(), AdventureUtils.parse(message));
        }

        @Override
        public void warning(String message) {
            StickCore.api().getConsole().send(AdventureUtils.parse(StickCore.api().getProjectPrefix() + "<yellow>" + message + "</yellow>"));
        }

        @Override
        public void severe(String message) {
            StickCore.api().getConsole().send(AdventureUtils.parse(StickCore.api().getProjectPrefix() + "<red>" + message + "</red>"));
        }

        @Override
        public void info(Supplier<String> msgSupplier) {
            info(msgSupplier.get());
        }

        private void doLog(LogRecord record) {
            if (record.getLevel() == Level.INFO) {
                info(record.getMessage());
                return;
            }

            record.setLoggerName(getName());

            log(record);
        }

        @Override
        public void log(Level level, String msg) {
            if (!isLoggable(level)) return;

            LogRecord record = new LogRecord(level, msg);

            doLog(record);
        }

        @Override
        public void log(Level level, Supplier<String> msgSupplier) {
            if (!isLoggable(level)) return;

            LogRecord record = new LogRecord(level, msgSupplier.get());

            doLog(record);
        }

        @Override
        public void log(Level level, String msg, Object param1) {
            if (!isLoggable(level)) return;

            LogRecord record = new LogRecord(level, msg);

            Object[] params = {param1};

            record.setParameters(params);

            doLog(record);
        }

        @Override
        public void log(Level level, String msg, Object[] params) {
            if (!isLoggable(level)) return;

            LogRecord record = new LogRecord(level, msg);

            record.setParameters(params);

            doLog(record);
        }

        @Override
        public void log(Level level, String msg, Throwable thrown) {
            if (!isLoggable(level)) return;

            LogRecord record = new LogRecord(level, msg);

            record.setThrown(thrown);

            doLog(record);
        }

        @Override
        public void log(Level level, Throwable thrown, Supplier<String> msgSupplier) {
            if (!isLoggable(level)) return;

            LogRecord record = new LogRecord(level, msgSupplier.get());

            record.setThrown(thrown);

            doLog(record);
        }

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