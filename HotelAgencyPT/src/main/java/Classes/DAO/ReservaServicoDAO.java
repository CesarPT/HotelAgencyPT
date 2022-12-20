package Classes.DAO;

import DataBase.ConnectionDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ReservaServicoDAO {

    private static Connection con = ConnectionDB.establishConnection();

    public static boolean criaReservaServico(int idreserva, int idservico) {
        String sql = "INSERT INTO ReservaServico (idreserva,idservico) Values(?,?)";

        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, idreserva);
            stmt.setInt(2, idservico);

            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("[ERRO]: insertReservaServico " + e.getMessage());
            return false;
        }
    }
}
