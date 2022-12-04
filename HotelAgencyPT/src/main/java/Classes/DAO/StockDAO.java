package Classes.DAO;

import DataBase.ConnectionDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class StockDAO {
    private static Connection con;

    /**
     * Ligar à base de dados
     */
    public StockDAO() {
        con = ConnectionDB.establishConnection();
    }

    public void closebd() {
        PreparedStatement stmt = null;
        ConnectionDB.closeConnection(con, stmt);
    }

    public static boolean insertNewStock(String product_identifier, String product_description, int quantidade) {

        String sql = "INSERT INTO Stock (product_identifier,product_description, quantidade) VALUES (?, ?, ?)";
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, product_identifier);
            stmt.setString(2, product_description);
            stmt.setInt(3, quantidade);


            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("[ERRO]: insertNewStock " + e.getMessage());
            return false;
        }
    }


}
