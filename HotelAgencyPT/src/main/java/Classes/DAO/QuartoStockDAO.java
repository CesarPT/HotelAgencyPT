package Classes.DAO;

import Classes.Quarto;
import Classes.QuartoStock;
import DataBase.ConnectionDB;
import hotel.agencypt.Controller.Controller;

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

    public List<QuartoStock> findIDQuartoStock() {
        String sql = "SELECT Stock.product_description, QuartoStock.quantidade\n" +
                "FROM QuartoStock\n" +
                "INNER JOIN Stock\n" +
                "ON Stock.product_identifier = QuartoStock.idstock\n" +
                "WHERE QuartoStock.idquarto=" + Controller.getInstance().getIdQuarto();

        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<QuartoStock> listQuartoStock = new ArrayList<>();

        try {
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                QuartoStock quarto = new QuartoStock();
                quarto.setProduct_description(rs.getString("product_description"));
                quarto.setQuantidade(rs.getFloat("quantidade"));
                listQuartoStock.add(quarto);
            }

        } catch (SQLException e) {
            System.err.println("[ERRO]: findIDQuartoStock " + e.getMessage());
        }
        return listQuartoStock;
    }


    public boolean updateQtd(QuartoStock quarto) {
        String sql = "UPDATE QuartoStock SET quantidade += +?\n" +
                "  FROM QuartoStock INNER JOIN Stock\n" +
                "  ON Stock.product_identifier = QuartoStock.idstock\n" +
                "                WHERE idquarto=" + Controller.getInstance().getIdQuarto() + " and Stock.product_description='" +
                Controller.getInstance().getProdutosEscolhidos() + "'";

        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setFloat(1, quarto.getQuantidade());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("[ERRO]: updateQtd " + e.getMessage());
            return false;
        }
    }
}
