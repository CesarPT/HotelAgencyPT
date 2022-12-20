package Classes.DAO;

import Classes.QuartoStock;
import DataBase.ConnectionDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class QuartoStockDAO {
    private static Connection con = ConnectionDB.establishConnection();

    /**
     * Método para pesquisar o idquarto, descrição do produto e quantidade
     *
     * @return lista
     */
    public List<QuartoStock> findQuartoStock() {
        String sql = "SELECT QuartoStock.idquarto, Stock.product_description, QuartoStock.quantidade\n" +
                "FROM QuartoStock\n" +
                "INNER JOIN Stock\n" +
                "ON Stock.product_identifier = QuartoStock.idstock\n" +
                "ORDER BY QuartoStock.idquarto";

        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<QuartoStock> listQuartoStock = new ArrayList<>();

        try {
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                QuartoStock quarto = new QuartoStock();
                quarto.setIdquarto(rs.getInt("idquarto"));
                quarto.setProduct_description(rs.getString("product_description"));
                quarto.setQuantidade(rs.getFloat("quantidade"));
                listQuartoStock.add(quarto);
            }

        } catch (SQLException e) {
            System.err.println("[ERRO]: findQuartoStock " + e.getMessage());
        } finally {
            ConnectionDB.closeConnection(con, stmt, rs);
        }
        return listQuartoStock;
    }
}
