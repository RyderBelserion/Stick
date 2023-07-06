package com.ryderbelserion.stick.paper;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import com.ryderbelserion.stick.paper.utils.AdventureUtils;
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
            Component msg = AdventureUtils.parse(Stick.getProjectPrefix() + message);

            Bukkit.getConsoleSender().sendMessage(msg);
        }

        @Override
        public void warning(String message) {
            String prefix = Stick.getProjectPrefix();

            Component msg = AdventureUtils.parse(prefix + "<yellow>" + message + "</yellow>");

            Bukkit.getConsoleSender().sendMessage(msg);
        }

        @Override
        public void severe(String message) {
            String prefix = Stick.getProjectPrefix();

            Component msg = AdventureUtils.parse(prefix + "<red>" + message + "</red>");

            Bukkit.getConsoleSender().sendMessage(msg);
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