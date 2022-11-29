package Classes.DAO;

import Classes.Reserva;
import DataBase.ConnectionDB;
import hotel.agencypt.Controller.Controller;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe pública que recebe dados da Base de Dados
 */
public class ReservaDAO {
    private static Connection con;

    /**
     * Ligar à base de dados
     */
    public ReservaDAO() {
        con = ConnectionDB.establishConnection();
    }

    /**
     * Método para pesquisar reservas pelo username
     */
    public static List<Reserva> findReserva() {
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

    /**
     * Método para pesquisar a ultima reserva criada para associar com
     */
    public static List<Reserva> findUltReserva() {
        String sql = "select TOP 1 percent idreserva " +
                "from Reserva " +
                "order by  idreserva desc;";


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


    public static boolean criaReserva(Reserva reserva) {
        String sql = "INSERT INTO Reserva (idcliente,idquarto,numcartao,datai,dataf) Values(?,?,?,?,?)";
        PreparedStatement stmt = null;

        try {
            con = ConnectionDB.establishConnection();
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, reserva.getIdcliente());
            stmt.setInt(2, reserva.getIdquarto());
            stmt.setInt(3, reserva.getNumcartao());

            stmt.setDate(4, (Date) reserva.getDataI());
            stmt.setDate(5, (Date) reserva.getDataF());
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
