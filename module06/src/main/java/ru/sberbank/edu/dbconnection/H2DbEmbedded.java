package ru.sberbank.edu.dbconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * This class provides a way to connect to an embedded H2 database using the JDBC driver.
 */
public class H2DbEmbedded implements AutoCloseable {
    /**
     * The URL for the H2 database in memory mode.
     */
    private static final String URL_MEM = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1";
    /**
     * The username for the H2 database.
     */
    private static final String USER = "sa";
    /**
     * The password for the H2 database.
     */
    public static final String PASSWD = "";
    /**
     * The connection to the H2 database.
     */
    private static Connection connection;

    /**
     * Initializes the H2 database by creating the car table.
     *
     * @throws SQLException if there is an error connecting to or initializing the database
     */
    public static void initDb() throws SQLException {
        String createCarTableSql = "CREATE TABLE car ( " +
                "id VARCHAR(30), " +
                "model VARCHAR(30)" +
                ")";
        Connection conn = getConnection();
        try (Statement statement = conn.createStatement()) {
            int count = statement.executeUpdate(createCarTableSql);
            System.out.println(count);
        }
    }

    /**
     * Gets a connection to the H2 database.
     * @return the connection to the H2 database
     * @throws SQLException if there is an error connecting to the database
     */
    public static Connection getConnection() throws SQLException {
        connection = connection == null ? DriverManager.getConnection(URL_MEM, USER, PASSWD) : connection;
        return connection;
    }

    /**
     * Closes the connection to the H2 database.
     * @throws Exception if there is an error closing the connection
     */
    @Override
    public void close() throws Exception {
        if (connection == null) {
            return;
        } else {
            connection.close();
        }
    }
}
