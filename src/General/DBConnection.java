package General;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {


    private final String ConnectionString = "jdbc:sqlite:resources/db/vacation4uDB.db";

    Connection conn = null;
    public DBConnection(){}

    public Connection getSQLLiteDBConnection() throws SQLException {
        if (conn == null || conn.isClosed()) {
                try {
                // db parameters
                // create a connection to the database
                    conn = DriverManager.getConnection(ConnectionString);

            } catch (SQLException e) {
                   e.getMessage();
                }
        }
        return conn;
    }


}
