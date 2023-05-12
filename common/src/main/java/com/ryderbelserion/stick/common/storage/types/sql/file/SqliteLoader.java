package com.ryderbelserion.stick.common.storage.types.sql.file;

import java.nio.file.Path;
import java.sql.Connection;

public class SqliteLoader extends FlatFileLoader {

    public SqliteLoader(Path file) {
        super(file);
    }

    @Override
    public String getImplName() {
        return "SQLite";
    }

    @Override
    protected Connection createConnection(Path file) {
        return null;
    }
}