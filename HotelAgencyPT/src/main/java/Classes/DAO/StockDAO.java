package Classes.DAO;

import Classes.Stock;
import DataBase.ConnectionDB;
import hotel.agencypt.Controller.Controller;
import javafx.scene.control.Alert;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StockDAO {
    private static final Connection con = ConnectionDB.establishConnection();

    public static boolean insertNewStock(
            String product_identifier, String product_description, String tipo_qtd,
            float quantidade, float preco, float vat, float preco_total
    ) {

        String sql = "INSERT INTO Stock (product_identifier,product_description, tipo_qtd, quantidade," +
                "preco, vat, preco_total) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, product_identifier);
            stmt.setString(2, product_description);
            stmt.setString(3, tipo_qtd);
            stmt.setFloat(4, quantidade);
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

    /**
     * Lista de produtos só consumíveis
     *
     * @return
     */
    public List<Stock> findStockConsumivel() {
        String sql = "SELECT product_identifier, product_description, quantidade, tipo_qtd," +
                "preco, vat, preco_total\n" +
                "FROM Stock\n" +
                "WHERE NOT (product_identifier='CF-9812'\n" +
                "OR product_identifier='LG18964'\n" +
                "OR product_identifier='XPO-756')";


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
            System.out.println("[ERRO]: findStockConsumivel | " + e.getMessage());
        }
        return listStock;
    }

    public static boolean IFfindItem(String id_prod) {
        boolean verfica = true;

        String sql = "SELECT product_identifier from Stock where product_identifier='" + id_prod + "'";

        List<Stock> liststock = new ArrayList<>();

        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
        } catch (SQLException e) {
            System.err.println("[ERRO]: findItem " + e.getMessage());

            verfica = false;
        }
        return verfica;
    }


    public static boolean updateStock(
            String product_identifier, float quantidade
    ) {

        String sql = "UPDATE Stock set quantidade = quantidade +" + quantidade +
                " WHERE product_identifier ='" + product_identifier + "'";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("[ERRO]: updateStock " + e.getMessage());
            return false;
        }
    }


    public static boolean updateStockQtd(
            int quantidade, String product_description
    ) {

        String sql = "UPDATE Stock set quantidade = quantidade" + quantidade +
                " WHERE product_description ='" + Controller.getInstance().getProdutosEscolhidos();
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("[ERRO]: updateStockQtd " + e.getMessage());
            return false;
        }
    }

    public List<Stock> decrementarStock() {

        String sql = "UPDATE Stock set quantidade = quantidade - 1 " +
                "WHERE product_description ='" + Controller.getInstance().getProdutosEscolhidos()+ "'";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.executeUpdate();

            //Informação
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Informação");
            alert.setHeaderText("Decrementar produtos");
            alert.setContentText("Stock: -1 \nQuartoStock: +1 ");
            alert.showAndWait();
        } catch (SQLException e) {
            System.err.println("[ERRO]: decrementarStock " + e.getMessage());
        }
        return null;
    }


}
