package app.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Дмитрий on 23.02.2018.
 */
public class DBConnUtils {
    public static Connection getConnection() throws ClassNotFoundException, SQLException {

        String url = "jdbc:postgresql://127.0.0.1:5432/transasiaPartsDB";
        String name = "postgres";
        String pass = "root";
        Class.forName("org.postgresql.Driver");

        Connection conn = DriverManager.getConnection(url, name, pass);
        return conn;
    }

    public static void closeQuietly(Connection conn) {
        try {
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
