package utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DbUtility {

    public static final int TIMEOUT = 30;

    private static final String CONNECTION = "jdbc:sqlite:movie.db";
    private static final String DRIVER_NAME = "org.sqlite.JDBC";

    public static Connection createConnection() throws ClassNotFoundException, SQLException {

        // register the database driver
        Class.forName(DRIVER_NAME);

        // create the database connection
        return DriverManager.getConnection(CONNECTION);
    }

    public static void classConnection(final Connection connection, final Statement statement) {

        try {

            if (null != connection) {

                connection.close();
            }
            if (null != statement) {

                statement.close();
            }
        } catch(SQLException ex){
            ex.printStackTrace();
        }


    }
}
