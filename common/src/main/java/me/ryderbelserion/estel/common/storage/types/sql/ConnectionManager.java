package me.ryderbelserion.estel.common.storage.types.sql;

import java.sql.Connection;
import java.sql.SQLException;

public interface ConnectionManager {

    String getImplName();

    void shutdown() throws SQLException;

    //Connection getConnection() throws SQLException;

}