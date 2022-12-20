package Classes.DAO;

import Classes.EstadoFunc;
import Classes.Utilizador;
import DataBase.ConnectionDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UtilizadorDAO {

    private static Connection con = ConnectionDB.establishConnection();


    public static List<Utilizador> findClientepReserva() {
        String sql = "Select Utilizador.nomeuser from Utilizador INNER JOIN Cliente on Cliente.iduser=Utilizador.iduser";
        List<Utilizador> listUser = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Utilizador utilizador = new Utilizador();
                utilizador.setNomeUtilizador(rs.getString("nomeuser"));
                listUser.add(utilizador);
            }

        } catch (SQLException e) {
            System.err.println("[ERRO]: findClientepReserva " + e.getMessage());
        }
        return listUser;
    }


    public static List<Utilizador> findClientepReservaCnome(String nomeclient) {
        String sql = "Select Utilizador.nomeuser from Utilizador INNER JOIN Cliente " +
                "on Cliente.iduser=Utilizador.iduser and Utilizador.nomeuser= '" + nomeclient + "'";


        List<Utilizador> listUser = new ArrayList<>();

        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Utilizador utilizador = new Utilizador();
                utilizador.setNomeUtilizador(rs.getString("nomeuser"));
                listUser.add(utilizador);
            }

        } catch (SQLException e) {
            System.err.println("[ERRO]: findClientepReserva123 " + e.getMessage());
        }
        return listUser;
    }


    public static List<EstadoFunc> findAllFuncionarios() {
        String sql = "select Utilizador.nomeuser , Funcionario.estado from Utilizador inner join Funcionario on Utilizador.iduser = Funcionario.iduser";
        List<EstadoFunc> listUser = new ArrayList<>();

        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                EstadoFunc estadoFunc = new EstadoFunc();
                estadoFunc.setNomefunc(rs.getString("nomeuser"));
                estadoFunc.setEstado(rs.getString("estado"));
                listUser.add(estadoFunc);
            }

        } catch (SQLException e) {
            System.err.println("[ERRO]: findClientepReserva123 " + e.getMessage());
        }
        return listUser;
    }
}
