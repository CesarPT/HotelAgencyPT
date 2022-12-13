package Classes.DAO;

import Classes.Entrega;
import DataBase.ConnectionDB;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EntregaDAO {
    private static Connection con = ConnectionDB.establishConnection();

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
