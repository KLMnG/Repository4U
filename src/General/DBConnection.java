package General;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {


    private String ConnectionString = "jdbc:sqlite:resources/db/vacation4uDB.db";

    Connection conn = null;
    public DBConnection(){}

    public Connection getSQLLiteDBConnection(){
        if (conn == null) {
                try {
                // db parameters
                // create a connection to the database
                conn = DriverManager.getConnection(ConnectionString);
                System.out.println("Connection to SQLite has been established.");

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        return conn;
    }


}
