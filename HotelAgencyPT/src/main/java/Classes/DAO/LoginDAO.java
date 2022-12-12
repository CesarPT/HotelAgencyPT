package Classes.DAO;

import DataBase.ConnectionDB;
import hotel.agencypt.Controller.Controller;
import hotel.agencypt.Controller.Singleton;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;


public class LoginDAO {
    static String correctOrIncorrect;

    static Connection con = ConnectionDB.establishConnection();

    public static boolean login(String user, String verifyLogin, Stage window) {
        String verifyPerm = ("SELECT tipouser FROM Utilizador WHERE nomeuser ='" + user + "'");
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
            System.out.println(Controller.getInstance().getUsername());
            //Passa informações para a scene seguinte
            validatePerms(verifyPerm, window);
            log = true;
        } else {
            log = false;
        }
        return log;
    }

    /**
     * Valida as permissões e abre a aba dessa mesma.
     */
    public static void validatePerms(String verifyPerm, Stage window) {
        try {
            PreparedStatement stmt = con.prepareStatement(verifyPerm);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {

                if (Objects.equals(rs.getString("tipouser"), "G")) {
                    window.close();
                    Singleton.open("GestorHotel", "Username: " + Controller.getInstance().getUsername() + " | Hotel >> Gestor de Hotel");
                }

                if (Objects.equals(rs.getString("tipouser"), "F")) {
                    window.close();
                    Singleton.open("funcionariointerface", "Username: " + Controller.getInstance().getUsername() + " | Hotel >> Funcionário");
                }

                if (Objects.equals(rs.getString("tipouser"), "C")) {
                    window.close();
                    Singleton.open("Cliente", "Username: " + Controller.getInstance().getUsername() + " | Hotel >> Cliente");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }

}