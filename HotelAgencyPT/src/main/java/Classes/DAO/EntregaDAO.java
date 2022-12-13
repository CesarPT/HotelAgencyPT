package Classes.DAO;

import Classes.Entrega;
import Classes.Stock;
import DataBase.ConnectionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EntregaDAO {
    private static Connection con = ConnectionDB.establishConnection();

    public List<Entrega> findEntrega() {
        String sql = "SELECT orderNumber, data_entrega, party_identifier, empresa,\n" +
                "\t   rua, n_porta, cidade, cp, pais\n" +
                "FROM Entrega";

        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Entrega> listEntrega = new ArrayList<>();

        //Limpar tudo e Adicionar todas as entradas de stock
        try {
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Entrega entrega = new Entrega();
                entrega.setOrderNumber(rs.getString("orderNumber"));
                entrega.setData_entrega(rs.getDate("data_entrega"));
                entrega.setParty_identifier(rs.getString("party_identifier"));
                entrega.setEmpresa(rs.getString("empresa"));
                entrega.setRua(rs.getString("rua"));
                entrega.setCidade(rs.getString("cidade"));
                entrega.setCp(rs.getString("cp"));
                entrega.setPais(rs.getString("pais"));
                listEntrega.add(entrega);
            }
        } catch (SQLException e) {
            System.out.println("[ERRO]: findEntrega |" + e.getMessage());
        }
        return listEntrega;
    }

    public boolean insertEntradaInfo(Entrega entrega) {
        String sql = "INSERT INTO Entrega (orderNumber,data_entrega, party_identifier, empresa, rua, n_porta, cidade, cp , pais) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, entrega.getOrderNumber());
            stmt.setDate(2, (Date) entrega.getData_entrega());
            stmt.setString(3, entrega.getParty_identifier());
            stmt.setString(4, entrega.getEmpresa());
            stmt.setString(5, entrega.getRua());
            stmt.setString(6, entrega.getN_porta());
            stmt.setString(7, entrega.getCidade());
            stmt.setString(8, entrega.getCp());
            stmt.setString(9, entrega.getPais());

            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("[ERRO]: insertEntradaInfo " + e.getMessage());
            return false;
        }
    }

}
