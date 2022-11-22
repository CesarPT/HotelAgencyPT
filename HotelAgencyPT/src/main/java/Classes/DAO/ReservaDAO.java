package Classes.DAO;

import Classes.Reserva;
import DataBase.ConnectionDB;
import hotel.agencypt.Controller.Controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe pública que recebe dados da Base de Dados
 */
public class ReservaDAO {
    private Connection con;

    /**
     * Ligar à base de dados
     */
    public ReservaDAO() {
        con = ConnectionDB.establishConnection();
    }

    /**
     * Método para pesquisar reservas pelo username
     */
    public List<Reserva> findReserva() {
        String sql = "SELECT Reserva.idreserva\n" +
                "FROM Reserva\n" +
                "INNER JOIN Cliente\n" +
                "ON Cliente.idcliente = Reserva.idcliente\n" +
                "INNER JOIN Utilizador\n" +
                "ON Utilizador.iduser = Cliente.iduser\n" +
                "WHERE Utilizador.nomeuser='" + Controller.getInstance().getUsername() + "'";

        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Reserva> listreserva = new ArrayList<>();

        try {
            con = ConnectionDB.establishConnection();
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Reserva reserva = new Reserva();
                reserva.setIdreserva(rs.getInt("idreserva"));
                listreserva.add(reserva);
            }

        } catch (SQLException e) {
            System.err.println("[ERRO]: findReserva " + e.getMessage());
        } finally {
            ConnectionDB.closeConnection(con, stmt, rs);
        }
        return listreserva;
    }

}
