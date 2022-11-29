package Classes.DAO;

import Classes.Reserva;
import Classes.ReservaServico;
import DataBase.ConnectionDB;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ReservaServicoDAO {

    private static Connection con;

    /**
     * Ligar à base de dados
     */
    public ReservaServicoDAO() {
        con = ConnectionDB.establishConnection();
    }


    public static boolean criaReservaServico(int idreserva ) {
        String sql = "INSERT INTO Reserva (idreserva,idservico) Values(?,?)";
        PreparedStatement stmt = null;

        try {
            con = ConnectionDB.establishConnection();
            stmt = con.prepareStatement(sql);
            stmt.setInt(1,idreserva);
            //stmt.setInt(2, reservaservico.getIdservico());

            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("[ERRO]: insertReserva " + e.getMessage());
            return false;
        } finally {
            ConnectionDB.closeConnection(con, stmt);
        }
    }
}
