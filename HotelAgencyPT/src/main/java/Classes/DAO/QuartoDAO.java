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

public class QuartoDAO {
    private static Connection con;

    /**
     * Ligar à base de dados
     */
    public QuartoDAO() {
        con = ConnectionDB.establishConnection();
    }


    public static List<Reserva> findQuartoIndividual() {
        String sql = " SELECT LEFT(descricao, charindex(' ', descricao) - 1) from Quarto where ";


        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Reserva> listreserva = new ArrayList<>();

        try {
            con = ConnectionDB.establishConnection();
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Reserva reserva = new Reserva();
                reserva.setIdreserva(rs.getInt("idQuarto"));
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
