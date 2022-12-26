package Classes.DAO;

import Classes.Funcionario;
import Classes.Reserva;
import DataBase.ConnectionDB;
import hotel.agencypt.Controller.Controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FuncionarioDAO {
    static Connection con = ConnectionDB.establishConnection();


    public static void updateAtiva(String nome) {
        String sql = "update Funcionario SET Funcionario.estado = 'Ativo' From Funcionario " +
                "inner join Utilizador on Utilizador.iduser = Funcionario.iduser " +
                "where Utilizador.nomeuser=?";
        List<Funcionario> listfuncionario = new ArrayList<>();

        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, nome);

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static void updateInativa(String nome) {
        String sql = "update Funcionario SET Funcionario.estado = 'Inativado' From Funcionario " +
                "inner join Utilizador on Utilizador.iduser = Funcionario.iduser " +
                "where Utilizador.nomeuser=?";

        List<Funcionario> listfuncionario = new ArrayList<>();

        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, nome);

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
