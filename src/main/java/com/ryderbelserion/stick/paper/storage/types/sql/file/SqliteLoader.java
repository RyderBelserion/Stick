package com.ryderbelserion.stick.paper.storage.types.sql.file;

import java.lang.reflect.Constructor;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class SqliteLoader extends FlatFileLoader {

    private Constructor<?> connectionBuilder;

    public SqliteLoader(Path file) {
        super(file);
    }

    @Override
    public String getImplName() {
        return "SQLite";
    }

    @Override
    protected Connection createConnection(Path file) throws SQLException {
        try {
            return (Connection) this.connectionBuilder.newInstance("jdbc:sqlite:" + file.toString(), file.toString(), new Properties());
        } catch (ReflectiveOperationException exception) {
            if (exception.getCause() instanceof SQLException) {
                throw (SQLException) exception.getCause();
            }

            throw new RuntimeException(exception);
        }
    }
}