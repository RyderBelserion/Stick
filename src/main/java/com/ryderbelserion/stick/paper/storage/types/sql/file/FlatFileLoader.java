package com.ryderbelserion.stick.paper.storage.types.sql.file;

import com.ryderbelserion.stick.paper.storage.types.sql.ConnectionManager;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.SQLException;

abstract class FlatFileLoader implements ConnectionManager {

    protected abstract Connection createConnection(Path file) throws SQLException;

    private Connection connection;
    private final Path file;

    FlatFileLoader(Path file) {
        this.file = file;
    }

    @Override
    public synchronized Connection getConnection() throws SQLException {
        if (this.connection == null || this.connection.isClosed()) this.connection = createConnection(this.file);

        return this.connection;
    }

    @Override
    public void shutdown() throws SQLException {
        if (this.connection != null) this.connection.close();
    }

    protected Path getFile() {
        return this.file;
    }
}