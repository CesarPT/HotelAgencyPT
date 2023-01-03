package Classes.DAO;

import Classes.QuartoStock;
import DataBase.ConnectionDB;
import hotel.agencypt.Controller.Controller;
import javafx.scene.control.Alert;

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
    List<QuartoStock> listQuartoStock = new ArrayList<>();
    public List<QuartoStock> findQuartoStock() {
        String sql = "SELECT QuartoStock.idquarto, Stock.product_description, QuartoStock.quantidade\n" +
                "FROM QuartoStock\n" +
                "INNER JOIN Stock\n" +
                "ON Stock.product_identifier = QuartoStock.idstock\n" +
                "WHERE QuartoStock.idquarto=" + Controller.getInstance().getIdQuarto() +
                "ORDER BY QuartoStock.idquarto";

        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                QuartoStock quarto = new QuartoStock();
                quarto.setIdquarto(rs.getInt("idquarto"));
                quarto.setProduct_description(rs.getString("product_description"));
                quarto.setQuantidade(rs.getFloat("quantidade"));
                listQuartoStock.add(quarto);
            }

        } catch (SQLException e) {
            System.err.println("[ERRO]: findQuartoStock " + e.getMessage());
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

    public List<QuartoStock>  updateStockQuarto() {

        String sql = "UPDATE QuartoStock set quantidade += 1\n" +
                "FROM \n" +
                "    QuartoStock\n" +
                "    INNER JOIN Stock\n" +
                "        ON Stock.product_identifier = QuartoStock.idstock\n" +
                "                WHERE product_description='"+Controller.getInstance().getProdutosEscolhidos()+"' AND idquarto="+Controller.getInstance().getIdQuarto();
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("[ERRO]: updateStock " + e.getMessage());
        }
        return  null;
    }

    public List<QuartoStock>  RemoveStockQuarto() {

        String sql = "UPDATE QuartoStock set quantidade = 0\n" +
                "FROM \n" +
                "    QuartoStock\n" +
                "    INNER JOIN Stock\n" +
                "        ON Stock.product_identifier = QuartoStock.idstock\n" +
                "                WHERE product_description='"+Controller.getInstance().getProdutosEscolhidos();
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.executeUpdate();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Informação");
            alert.setHeaderText("Decrementar produtos");
            alert.setContentText("QuartoStock: -1 ");
            alert.showAndWait();
        } catch (SQLException e) {
            System.err.println("[ERRO]: updateStock " + e.getMessage());
        }
        return  null;
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
    public boolean deleteProdutoQuarto(QuartoStock quarto) {
        String sql = "DELETE FROM QuartoStock\n" +
                "  INNER JOIN Stock\n" +
                "  ON Stock.product_identifier = QuartoStock.idstock\n" +
                "                WHERE idquarto=" + Controller.getInstance().getIdQuarto() + " and Stock.product_description='" +
                Controller.getInstance().getProdutosEscolhidos() + "'";

        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.executeUpdate();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);

            return true;

        } catch (SQLException e) {
            System.err.println("[ERRO]: deleteProdutoQuarto " + e.getMessage());
            return false;
        }
    }
}
