package Classes.DAO;

import Classes.Quarto;
import Classes.Stock;
import DataBase.ConnectionDB;
import hotel.agencypt.Controller.Controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StockDAO {
    private static Connection con = ConnectionDB.establishConnection();

    /*
    public void closebd() {
        PreparedStatement stmt = null;
        ConnectionDB.closeConnection(con, stmt);
    }
     */

    public static boolean insertNewStock(
            String product_identifier, String product_description, String tipo_qtd,
            int quantidade, float preco, float vat, float preco_total
    ) {

        String sql = "INSERT INTO Stock (product_identifier,product_description, tipo_qtd, quantidade," +
                "preco, vat, preco_total) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
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

    public List<Stock> findStock() {
        String sql = "SELECT product_identifier, product_description, quantidade, tipo_qtd," +
                "preco, vat, preco_total\n" +
                "FROM Stock";

        List<Stock> listStock = new ArrayList<>();

        //Limpar tudo e Adicionar todas as entradas de stock
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Stock stock = new Stock();
                stock.setProduct_identifier(rs.getString("product_identifier"));
                stock.setProduct_description(rs.getString("product_description"));
                stock.setQuantidade(rs.getInt("quantidade"));
                stock.setTipo_qtd(rs.getString("tipo_qtd"));
                stock.setPreco(rs.getFloat("preco"));
                stock.setVat(rs.getFloat("vat"));
                stock.setPreco_total(rs.getFloat("preco_total"));
                listStock.add(stock);
            }
        } catch (SQLException e) {
            System.out.println("[ERRO]: Inserir valores no observableList obsEntradas");
        }
        return listStock;
    }

    public static boolean IFfindItem(String id_prod) {
        boolean verfica = true;

        String sql = "SELECT product_identifier from Stock where product_identifier='" + id_prod + "'";

        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Stock> liststock = new ArrayList<>();

        try {
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();
        } catch (SQLException e) {
            System.err.println("[ERRO]: findItem " + e.getMessage());

            verfica = false;
        }
        return verfica;
    }


    public static boolean updateStock(
            String product_identifier, int quantidade
    ) {

        String sql = "UPDATE Stock set quantidade = quantidade +" + quantidade + " where product_identifier ='" + product_identifier + "'";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("[ERRO]: updateStock " + e.getMessage());
            return false;
        }
    }

}
