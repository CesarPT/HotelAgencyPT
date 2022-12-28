package Classes.DAO;

import Classes.Utilizador;
import DataBase.ConnectionDB;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;


public class RegisterDAO {
    static Connection con = ConnectionDB.establishConnection();
    private static int id;
    static String vExists;
    static Utilizador user = new Utilizador();

    /**
     * Obter os tipos de utilizador que existe na base dados e adicionar na combobox
     *
     * @param permissionComboBox recebe a comboBox
     */
    public static void getTypeUserComboBox(ComboBox permissionComboBox) {
        String getTypeUser = "SELECT tipouser FROM TipoUser";
        try {
            PreparedStatement stmt = con.prepareStatement(getTypeUser);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String typeUser = rs.getString("tipouser");
                permissionComboBox.getItems().add(typeUser);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Verifica se existe outro nome de utilizador igual
     *
     * @param user recebe o nome de utilizador
     * @return se o nome já existe ou não
     */
    public static boolean verifyUser(String user) {
        boolean vUser;
        String verifyUser = "SELECT count(1) FROM Utilizador WHERE nomeuser ='" + user + "'";
        try {
            PreparedStatement stmt = con.prepareStatement(verifyUser);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                if (rs.getInt(1) == 1) {
                    vExists = "O nome já existe!";
                } else {
                    vExists = "";
                }
            }
        } catch (SQLException e) {
            e.getCause();
        }
        if (Objects.equals(vExists, "O nome já existe!")) {
            vUser = false;
        } else {
            vUser = true;
        }
        return vUser;
    }

    public static void verifyTypeUser(String perm) {
        String getTypeUser = "SELECT prefixo FROM TipoUser where tipouser = '" + perm + "'";
        try {
            PreparedStatement stmt = con.prepareStatement(getTypeUser);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                user.setTipoUtilizador(rs.getString("prefixo"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void Register(String username, String encrypt) {
        String prefix = user.getTipoUtilizador();
        String insertToRegister = "INSERT INTO Utilizador(nomeuser, password, tipouser) VALUES(?,?,?)";
        try {
            PreparedStatement stmt = con.prepareStatement(insertToRegister);
            stmt.setString(1, username);
            stmt.setString(2, encrypt);
            stmt.setString(3, prefix);
            stmt.executeUpdate();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Sucesso!");
            alert.setHeaderText("Registado com sucesso!");
            alert.showAndWait();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        CreateTypeUser(username, prefix);
    }

    public static void validateUser(String username, String prefix) {
        String getIDUser = "SELECT iduser FROM Utilizador WHERE nomeuser = '" + username + "' and tipouser ='" + prefix + "'";
        try {
            PreparedStatement stm = con.prepareStatement(getIDUser);
            ResultSet rs = stm.executeQuery();

            if (rs.next()) {
                id = rs.getInt("iduser");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void CreateTypeUser(String username, String prefix) {
        validateUser(username, prefix);
        if (Objects.equals(prefix, "C")) {
            String validateC = "SELECT count(1) FROM Cliente WHERE iduser=" + id;
            try {
                PreparedStatement stmt = con.prepareStatement(validateC);
                ResultSet rs = stmt.executeQuery();

                while (rs.next()) {
                    if (rs.getInt(1) == 1) {
                    } else {
                        String create = "INSERT INTO Cliente(iduser) VALUES(" + id + ")";
                        PreparedStatement stm1 = con.prepareStatement(create);
                        stm1.executeUpdate();
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (Objects.equals(prefix, "F")) {
            String validateF = "SELECT count(1) FROM Funcionario WHERE iduser=" + id;
            try {
                PreparedStatement stmt = con.prepareStatement(validateF);
                ResultSet rs = stmt.executeQuery();

                while (rs.next()) {
                    if (rs.getInt(1) == 1) {
                    } else {
                        String create = "INSERT INTO Funcionario(iduser, estado) VALUES(" + id + ", 'Ativo')";
                        PreparedStatement stm1 = con.prepareStatement(create);
                        stm1.executeUpdate();
                        ;
                    }
                }
            } catch (Exception e) {
                e.getCause();
            }
        } else if (Objects.equals(prefix, "G")) {
            String validarG = "SELECT count(1) FROM Gestor WHERE iduser=" + id;
            try {
                PreparedStatement stmt = con.prepareStatement(validarG);
                ResultSet rs = stmt.executeQuery();

                while (rs.next()) {
                    if (rs.getInt(1) == 1) {
                    } else {
                        String create = "INSERT INTO Gestor(iduser) VALUES(" + id + ")";
                        PreparedStatement stm1 = con.prepareStatement(create);
                        stm1.executeUpdate();
                    }
                }
            } catch (Exception e) {
                e.getCause();
            }
        }
    }
}
