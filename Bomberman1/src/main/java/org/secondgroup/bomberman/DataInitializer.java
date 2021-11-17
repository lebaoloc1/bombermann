package org.secondgroup.bomberman;

import org.secondgroup.bomberman.common.DataConnection;
import org.secondgroup.bomberman.common.utils.resources.StreamReader;

import java.sql.Connection;
import java.sql.SQLException;

public class DataInitializer {
    private String getInitSQLScript() {
        var reader = new StreamReader(
                Main.class.getResourceAsStream("sql/init.sql")
        );

        return reader.toString();
    }

    private void initTables(Connection connection) throws SQLException {
        var initSQL = getInitSQLScript().split(";");
        var statement = connection.createStatement();

        for (String query : initSQL)
            statement.addBatch(query);

        statement.executeBatch();
    }

    public void initialize() {
        Main.getLogger().info("Initializing data connection");
        var connection = DataConnection.getConnection();
        Main.getLogger().info("Data connection initialized successfully");
        try {
            Main.getLogger().info("Initializing data tables");
            initTables(connection);
            Main.getLogger().info("Data tables initialized successfully");
        } catch (SQLException exception) {
            Main.getLogger().error(exception.getMessage());
            System.exit(0);
        }
    }
}
