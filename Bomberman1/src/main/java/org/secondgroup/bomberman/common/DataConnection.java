package org.secondgroup.bomberman.common;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataConnection {
    private static volatile DataConnection instance;

    private final Connection connection;

    private DataConnection() {
        var appConfig = AppConfig.getInstance();
        var datasource = Paths.get(appConfig.getAppDataPath().toString(), "data", appConfig.getAppName() + ".db");
        connection = connectDatabase(datasource);
    }

    private static Connection connectDatabase(Path datasource) {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(
                    String.format("jdbc:sqlite:%s", datasource.toString()));
        } catch (SQLException exception) {
            Logger.error("Cannot connect to database, exiting...", "DataConnection");
            System.exit(126);
        }

        return conn;
    }

    public static DataConnection getInstance() {
        if (instance == null)
            synchronized (DataConnection.class) {
                if (instance == null)
                    instance = new DataConnection();
            }

        return instance;
    }

    public static Connection getConnection() {
        if (instance == null)
            synchronized (DataConnection.class) {
                if (instance == null)
                    instance = new DataConnection();
            }

        return instance.connection;
    }
}
