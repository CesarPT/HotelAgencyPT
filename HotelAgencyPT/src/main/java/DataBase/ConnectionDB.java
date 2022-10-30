package DataBase;

import java.sql.*;
public class ConnectionDB {
    private String url, username, password;

    public ConnectionDB() {
        this.url = "jdbc:sqlserver://DESKTOP-2IMNL57\\SQLEXPRESS;databaseName=arroz;TrustServerCertificate=True";
        this.username = "sa";
        this.password = "123";
    }
    public void TestConnection() {
        try {
            Connection con = DriverManager.getConnection(returnUrl(), returnUser(), returnPassword());
            System.out.println("Connected!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public String returnUrl() {
        return url;
    }

    public String returnUser() {
        return username;
    }

    public String returnPassword() {
        return password;
    }

    public static void main(String[] args){
        ConnectionDB a = new ConnectionDB();
        a.TestConnection();
    }
}

