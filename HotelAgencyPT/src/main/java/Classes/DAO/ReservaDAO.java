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
    static StockDAO stockDAO = new StockDAO();
    private static final Connection con = ConnectionDB.establishConnection();

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

        List<Reserva> listreserva = new ArrayList<>();

        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Reserva reserva = new Reserva();
                reserva.setIdreserva(rs.getInt("idreserva"));
                listreserva.add(reserva);
            }

        } catch (SQLException e) {
            System.err.println("[ERRO]: findReserva " + e.getMessage());
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

        List<Reserva> listreserva = new ArrayList<>();

        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Reserva reserva = new Reserva();
                reserva.setIdreserva(rs.getInt("idreserva"));
                listreserva.add(reserva);
            }

        } catch (SQLException e) {
            System.err.println("[ERRO]: findReserva " + e.getMessage());
        }
        return listreserva;
    }

    public static List<Reserva> temCheckIn() {
        String sql = "SELECT Reserva.idcliente, Reserva.idreserva,\n" +
                "            case when CheckInOut.EstadoCheckIn = 'I' Then 'Sim' Else 'Nao' End as CheckIn\n" +
                "    FROM Reserva\n" +
                "    FULL OUTER JOIN CheckInOut\n" +
                "    ON CheckInOut.idreserva = Reserva.idreserva\n" +
                "    ORDER BY CheckIn desc";

        List<Reserva> listreserva = new ArrayList<>();
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Reserva reserva = new Reserva();
                reserva.setIdcliente(rs.getInt("idcliente"));
                reserva.setIdreserva(rs.getInt("idreserva"));
                reserva.setCheckIn(rs.getString("CheckIn"));
                listreserva.add(reserva);
            }
        } catch (SQLException e) {
            System.err.println("[ERRO]: temCheckIn " + e.getMessage());
        }
        return listreserva;
    }

    public static void criaReserva(Reserva reserva) {
        String sql = "INSERT INTO Reserva (idcliente,idquarto,datai,dataf) Values (?,?,?,?)";

        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, reserva.getIdcliente());
            stmt.setInt(2, reserva.getIdquarto());
            stmt.setDate(3, (Date) reserva.getDataI());
            stmt.setDate(4, (Date) reserva.getDataF());
            stmt.executeUpdate();

        } catch (SQLException e) {
            System.err.println("[ERRO]: criarReserva " + e.getMessage());
        }
    }

    public boolean deleteReserva() {
        String sql = "DELETE FROM CheckInOut\n" +
                "    WHERE idreserva=" + Controller.getInstance().getSelectedRowReserva() + "\n" +
                "DELETE FROM ReservaServico\n" +
                "    WHERE idreserva=" + Controller.getInstance().getSelectedRowReserva() + "\n" +
                "    DELETE FROM Reserva\n" +
                "    WHERE idreserva=" + Controller.getInstance().getSelectedRowReserva();
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("[ERRO]: deleteReserva " + e.getMessage());
            return false;
        }
    }
}
