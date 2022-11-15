package DataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDB {
    private static String url;
    private static String username;
    private static String password;

    public ConnectionDB() {
        this.url = "jdbc:sqlserver://DESKTOP-2IMNL57\\SQLEXPRESS;databaseName=arroz;TrustServerCertificate=True";
        this.username = "sa";
        this.password = "123";
    }

    public static Connection establishConnection() {
        Connection con = null;
        try {
            con = DriverManager.getConnection(returnUrl(), returnUser(), returnPassword());
            System.out.println("Connected!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return con;
    }

    public static String returnUrl() {
        return url;
    }

    public static String returnUser() {
        return username;
    }

    public static String returnPassword() {
        return password;
    }
}

