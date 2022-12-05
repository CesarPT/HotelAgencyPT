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

    public static boolean insertNewStock(
            String product_identifier, String product_description, String tipo_qtd,
            int quantidade, float preco, float vat, float preco_total
    ) {

        String sql = "INSERT INTO Stock (product_identifier,product_description, tipo_qtd, quantidade," +
                "preco, vat, preco_total) VALUES (?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, product_identifier);
            stmt.setString(2, product_description);
            stmt.setString(3, tipo_qtd);
            stmt.setInt(4, quantidade);
            stmt.setFloat(5, preco);
            stmt.setFloat(6, vat);
            stmt.setFloat(7, preco_total);


            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("[ERRO]: insertNewStock " + e.getMessage());
            return false;
        }
    }


}
