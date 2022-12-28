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
    static String verifyCheckIn;

    static Integer id;

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

    public static void CreateCheckIn(Integer idClient, Integer idReserv) {
        String checkIn = "Insert INTO CheckInOut(EstadoCheckIn, idcliente, idreserva) VALUES('I',?,?)";
        try {
            PreparedStatement stmt = con.prepareStatement(checkIn);
            stmt.setInt(1, idClient);
            stmt.setInt(2, idReserv);
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void VerifyCheckInExists(Integer idReserv) {
        Integer idClient = check.getIdClient();
        String exists = "SELECT COUNT(1) FROM CheckInOut where idcliente = " + idClient + " AND idreserva = " + idReserv + " AND (EstadoCheckIn = 'I' OR EstadoCheckIn = 'O')";
        try {
            PreparedStatement stm = con.prepareStatement(exists);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                if (rs.getInt(1) == 1) {
                    verifyCheckIn = "Exists!";
                } else {
                    verifyCheckIn = "Not Exists!";
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (Objects.equals(verifyCheckIn, "Exists!")) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Aviso!");
            alert.setHeaderText("Esta reserva já tem Check-IN");
            alert.showAndWait();
        } else {
            CreateCheckIn(idClient, idReserv);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Sucesso!");
            alert.setHeaderText("O Check-In foi feito com sucesso!");
            alert.showAndWait();
        }
    }

    public static void createCheckOut(Integer idClient, Integer idReserv) {
        String update = "UPDATE CheckInOut SET EstadoCheckIn = 'O' WHERE idcliente = " + idClient + " AND idreserva = " + idReserv;
        try {
            PreparedStatement stmt = con.prepareStatement(update);
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void VerifyCheckOutExists(Integer idReserv) {
        Integer idClient = check.getIdClient();
        String exists = "SELECT COUNT(1) FROM CheckInOut where idcliente = " + idClient + " AND idreserva = " + idReserv + "AND EstadoCheckIn = 'I'";
        try {
            PreparedStatement stm = con.prepareStatement(exists);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                if (rs.getInt(1) == 1) {
                    verifyCheckIn = "Exists!";
                } else {
                    verifyCheckIn = "Not Exists!";
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (Objects.equals(verifyCheckIn, "Exists!")) {
            createCheckOut(idClient, idReserv);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Sucesso!");
            alert.setHeaderText("O Check-out foi feito com sucesso!");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Aviso!");
            alert.setHeaderText("Esta reserva não tem Check-IN");
            alert.showAndWait();
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
            check.setIdClient(getIdClient(idClient));
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

    public static Integer getIdClient(String idClient) {
        try {
            PreparedStatement stm = con.prepareStatement(idClient);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                id = rs.getInt("idcliente");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;

    }
}
