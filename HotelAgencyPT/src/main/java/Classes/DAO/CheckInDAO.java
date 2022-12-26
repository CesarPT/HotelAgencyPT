package Classes.DAO;

import Classes.CheckIn;
import DataBase.ConnectionDB;
import javafx.scene.control.Alert;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

import static hotel.agencypt.Controller.CheckInController.CheckInObservableList;

public class CheckInDAO {
    static Connection con = ConnectionDB.establishConnection();
    static String correctOrIncorrect;

    public static CheckIn check = new CheckIn();


    public static void getTable() {

        Integer client = check.getIdClient();
        String query = "Select idreserva, idquarto AS quarto, datai, dataf FROM Reserva WHERE idcliente = " + client;
        try {
            PreparedStatement stm = con.prepareStatement(query);
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                Integer queryReservID = rs.getInt("idreserva");
                Integer queryRoomID = rs.getInt("quarto");
                check.setIdquarto(queryRoomID);
                String queryDateI = rs.getString("datai");
                String queryDateF = rs.getString("dataf");

                CheckInObservableList.add(new CheckIn(queryReservID, queryRoomID, queryDateI, queryDateF));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void getFloorType() {
        Integer roomID = check.getIdquarto();
        String query = "SELECT piso,descricao As tipo FROM Quarto WHERE idquarto = " + roomID;
        try {
            PreparedStatement stm = con.prepareStatement(query);
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                check.setFloor(rs.getInt("piso"));
                check.setType(rs.getString("tipo"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void CreateCheckIn(Integer idReserv) {
        String checkIn = "Insert INTO CheckInOut(EstadoCheckIn, idcliente, idreserva) VALUES('I',?,?)";
        Integer idClient = check.getIdClient();
        try {
            PreparedStatement stmt = con.prepareStatement(checkIn);
            stmt.setInt(1, idClient);
            stmt.setInt(2, idReserv);
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean VerifyExists(String username) {
        String verify = "SELECT COUNT(1) FROM Cliente INNER JOIN Utilizador on Cliente.iduser = Utilizador.iduser where Utilizador.nomeuser = '" + username + "'";
        String idClient = "SELECT idcliente FROM Cliente INNER JOIN Utilizador on Cliente.iduser = Utilizador.iduser where Utilizador.nomeuser ='" + username + "'";
        boolean exist;
        try {
            PreparedStatement stm = con.prepareStatement(verify);
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                if (rs.getInt(1) == 1) {
                    correctOrIncorrect = "Exists!";
                } else {
                    correctOrIncorrect = "Not Exists!";
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (Objects.equals(correctOrIncorrect, "Exists!")) {
            exist = true;
            getIdClient(idClient);
            getTable();
            getFloorType();
        } else {
            exist = false;
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Aviso");
            alert.setHeaderText("O username do cliente não existe.");
            alert.showAndWait();
        }
        return exist;
    }

    public static void getIdClient(String idClient) {
        try {
            PreparedStatement stm = con.prepareStatement(idClient);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                check.setIdClient(rs.getInt("idcliente"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
