package Classes.DAO;

import DataBase.ConnectionDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;


public class RegisterDAO {
    static Connection con = ConnectionDB.establishConnection();
    private static int id;
    static String vExists;
    static String registe;

    /**
     * Verificação se o username já existe!
     *
     * @return user
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


    public static boolean Register(char tipouser, String username, String encrypt) {
        String insertToRegister = "INSERT INTO Utilizador(nomeuser, password, tipouser) VALUES('" + username + "' , '" + encrypt + "' , '" + tipouser + "')";
        boolean rg;
        try {
            PreparedStatement stmt = con.prepareStatement(insertToRegister);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                registe = "Registado com sucesso!";
            } else {
                registe = "";
            }
        } catch (SQLException e) {
            e.getCause();
        }
        String getIDUser = "SELECT iduser FROM Utilizador WHERE nomeuser = '" + username + "' and tipouser ='" + tipouser + "'";
        CreateTypeUser(getIDUser, tipouser);
        if (Objects.equals(registe, "Registado com sucesso!")) {
            rg = true;
        } else {
            rg = false;
        }
        return rg;
    }

    public static void validateUser(String getIDUser) {
        try {
            PreparedStatement stm = con.prepareStatement(getIDUser);
            ResultSet rs = stm.executeQuery();

            if (rs.next()) {
                id = rs.getInt("iduser");
                System.out.println(id);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void CreateTypeUser(String getIDUser, char tipoUser) {
        validateUser(getIDUser);
        if (tipoUser == 'C') {
            String validarC = "SELECT count(1) FROM Cliente WHERE iduser=" + id;
            try {
                PreparedStatement stmt = con.prepareStatement(validarC);
                ResultSet rs = stmt.executeQuery();

                while (rs.next()) {
                    if (rs.getInt(1) == 1) {
                    } else {
                        String create = "INSERT INTO Cliente(iduser) VALUES(" + id + ")";
                        PreparedStatement stm1 = con.prepareStatement(create);
                        stm1.executeQuery();
                    }
                }

            } catch (Exception e) {
                e.getCause();
            }
        } else if (tipoUser == 'F') {
            String validateF = "SELECT count(1) FROM Funcionario WHERE iduser=" + id;
            try {
                PreparedStatement stmt = con.prepareStatement(validateF);
                ResultSet rs = stmt.executeQuery();

                while (rs.next()) {
                    if (rs.getInt(1) == 1) {
                    } else {
                        String create = "INSERT INTO Funcionario(iduser, estado) VALUES(" + id + ", 'Ativo')";
                        PreparedStatement stm1 = con.prepareStatement(create);
                        stm1.executeQuery();
                    }
                }
            } catch (Exception e) {
                e.getCause();
            }
        } else if (tipoUser == 'G') {
            String validarG = "SELECT count(1) FROM Gestor WHERE iduser=" + id;
            try {
                PreparedStatement stmt = con.prepareStatement(validarG);
                ResultSet rs = stmt.executeQuery();

                while (rs.next()) {
                    if (rs.getInt(1) == 1) {
                    } else {
                        String create = "INSERT INTO Gestor(iduser) VALUES(" + id + ")";
                        PreparedStatement stm1 = con.prepareStatement(create);
                        stm1.executeQuery();
                    }
                }
            } catch (Exception e) {
                e.getCause();
            }
        }
    }
}
