package Classes.DAO;

import Classes.Funcionario;
import Classes.Utilizador;
import DataBase.ConnectionDB;
import hotel.agencypt.Controller.Controller;
import hotel.agencypt.Controller.Singleton;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;


public class LoginDAO {
    static String correctOrIncorrect;

    static boolean hasCheck = false;

    static Utilizador util = new Utilizador();
    static Funcionario func = new Funcionario();
    static Connection con = ConnectionDB.establishConnection();

    public static boolean login(String user, Stage window, String encryptedpassword) {
        String verifyLogin = "SELECT count(1) FROM Utilizador WHERE nomeuser ='" + user + "' AND convert (varchar(MAX), password) = '" + encryptedpassword + "'";
        boolean log;
        try {
            PreparedStatement stmt = con.prepareStatement(verifyLogin);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                if (rs.getInt(1) == 1) {
                    correctOrIncorrect = "Login com sucesso!";
                    Controller.getInstance().setUsername(user);
                } else {
                    correctOrIncorrect = "Login invalido, tente novamente!";

                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if (Objects.equals(correctOrIncorrect, "Login com sucesso!")) {
            //Passa informações para a scene seguinte
            validatePerms(user, window);
            log = true;
        } else {
            log = false;
        }
        return log;
    }


    /**
     * Valida as permissões e abre a aba dessa mesma.
     */
    public static void validatePerms(String user, Stage window) {
        String verifyPerm = ("SELECT iduser, tipouser FROM Utilizador WHERE nomeuser ='" + user + "'");
        try {
            PreparedStatement stmt = con.prepareStatement(verifyPerm);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                if (Objects.equals(rs.getString("tipouser"), "G")) {
                    Controller.getInstance().setTipo_user('G');
                    window.close();
                    Singleton.open("GestorHotel", "Username: " + Controller.getInstance().getUsername() + " | Hotel >> Gestor de Hotel");
                }

                if (Objects.equals(rs.getString("tipouser"), "F")) {
                    func.setIdUtilizador(rs.getInt("iduser"));
                    validateEmployee(window);
                }

                if (Objects.equals(rs.getString("tipouser"), "C")) {
                    util.setIdUtilizador(rs.getInt("iduser"));
                    validateClient(window);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }

    public static void validateEmployee(Stage window) {
        Integer idUser = func.getIdUtilizador();
        String ValidateIDE = "SELECT estado FROM Funcionario WHERE iduser = " + idUser;
        try {
            PreparedStatement stm = con.prepareStatement(ValidateIDE);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                if (Objects.equals(rs.getString("estado"), "Ativo")) {
                    func.setEstado(rs.getString("estado"));
                    Controller.getInstance().setTipo_user('F');
                    window.close();
                    Singleton.open("funcionariointerface", "Username: " + Controller.getInstance().getUsername() + " | Hotel >> Funcionário");
                } else {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Erro!");
                    alert.setHeaderText("O Funcionario não esta ativo!");
                    alert.showAndWait();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void validateClient(Stage window) {
        Integer idUser = util.getIdUtilizador();
        String Client = "SELECT idcliente FROM Cliente INNER JOIN Utilizador on Cliente.iduser = Utilizador.iduser where Utilizador.iduser =" + idUser + "";
        Integer id = CheckInDAO.getIdClient(Client);
        String ValidateIDC = "SELECT EstadoCheckIn FROM CheckInOut WHERE idcliente = " + id + " AND EstadoCheckIn = 'I'";
        try {
            PreparedStatement stm = con.prepareStatement(ValidateIDC);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                if (Objects.equals(rs.getString("EstadoCheckIn"), "I")) {
                    hasCheck = true;
                }
            }
            if (hasCheck == true) {
                Controller.getInstance().setTipo_user('C');
                window.close();
                Singleton.open("Cliente", "Username: " + Controller.getInstance().getUsername() + " | Hotel >> Cliente");
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Erro!");
                alert.setHeaderText("O Cliente não tem Check-in!");
                alert.showAndWait();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
