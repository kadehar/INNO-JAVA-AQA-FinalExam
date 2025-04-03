package com.github.kadehar.inno.db;

import com.p6spy.engine.spy.P6DataSource;
import org.postgresql.ds.PGSimpleDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;
import java.util.function.Function;

public class Databases {

    private Databases() {
    }

    private static final Map<String, DataSource> SOURCES = new ConcurrentHashMap<>();
    private static final Map<Long, Map<String, Connection>> THREAD_CONNECTIONS = new ConcurrentHashMap<>();

    public static <T> T transaction(Function<Connection, T> function, DbData data) {
        Connection connection = null;
        try {
            connection = connection(data);
            connection.setAutoCommit(false);
            T result = function.apply(connection);
            connection.commit();
            connection.setAutoCommit(true);
            return result;
        } catch (Exception e) {
            if (connection != null) {
                try {
                    connection.rollback();
                    connection.setAutoCommit(true);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
            throw new RuntimeException(e);
        }
    }

    public static void transaction(Consumer<Connection> consumer, DbData data) {
        Connection connection = null;
        try {
            connection = connection(data);
            connection.setAutoCommit(false);
            consumer.accept(connection);
            connection.commit();
            connection.setAutoCommit(true);
        } catch (Exception e) {
            if (connection != null) {
                try {
                    connection.rollback();
                    connection.setAutoCommit(true);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
            throw new RuntimeException(e);
        }
    }

    private static DataSource dataSource(DbData data) {
        return SOURCES.computeIfAbsent(
                data.getJdbcUrl(),
                key -> {
                    PGSimpleDataSource ds = new PGSimpleDataSource();
                    ds.setUser(data.getLogin());
                    ds.setPassword(data.getPassword());
                    ds.setUrl(key);
                    return new P6DataSource(ds);
                }
        );
    }

    private static Connection connection(DbData data) {
        return THREAD_CONNECTIONS.computeIfAbsent(
                Thread.currentThread().threadId(),
                _ -> {
                    try {
                        return new HashMap<>(Map.of(
                                data.getJdbcUrl(),
                                dataSource(data).getConnection()
                        ));
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                }
        ).computeIfAbsent(
                data.getJdbcUrl(),
                _ -> {
                    try {
                        return dataSource(data).getConnection();
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                }
        );
    }

    public static void closeAllConnections() {
        for (Map<String, Connection> connectionMap : THREAD_CONNECTIONS.values()) {
            for (Connection connection : connectionMap.values()) {
                try {
                    if (connection != null && !connection.isClosed()) {
                        connection.close();
                    }
                } catch (SQLException ignore) {

                }
            }
        }
    }
}
