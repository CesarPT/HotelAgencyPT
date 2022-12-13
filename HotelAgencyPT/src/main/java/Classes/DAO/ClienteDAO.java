package Classes.DAO;

import Classes.Cliente;
import DataBase.ConnectionDB;
import hotel.agencypt.Controller.Controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {
    private static Connection con = ConnectionDB.establishConnection();

    public static List<Cliente> findidCliente() {
        String sql = "SELECT idcliente\n" +
                "FROM Cliente";


        List<Cliente> listCliente = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setIdCliente(rs.getInt("idcliente"));
                listCliente.add(cliente);
            }

        } catch (SQLException e) {
            System.err.println("[ERRO]: findRegEntradaQuarto " + e.getMessage());
        } finally {
            ConnectionDB.closeConnection(con, stmt, rs);
        }
        return listCliente;
    }


    public List<Cliente> findClienteCid(int idcliente) {
        String sql = "SELECT idcliente\n" +
                "FROM Cliente\n" +
                "WHERE idcliente='" + idcliente + "'";

        List<Cliente> listCliente = new ArrayList<>();

        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setIdCliente(rs.getInt("idcliente"));
                listCliente.add(cliente);
            }

        } catch (SQLException e) {
            System.err.println("[ERRO]: findRegEntradaQuarto " + e.getMessage());
        }
        return listCliente;
    }


    public List<Cliente> findIDCliente() {
        String sql = "SELECT idcliente\n" +
                "FROM Cliente\n" +
                "INNER JOIN Utilizador\n" +
                "ON Utilizador.iduser = Cliente.iduser\n" +
                "WHERE Utilizador.nomeuser = +'" + Controller.getInstance().getUsername() + "'";


        List<Cliente> listCliente = new ArrayList<>();


        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setIdCliente(rs.getInt("idcliente"));
                listCliente.add(cliente);
            }

        } catch (SQLException e) {
            System.err.println("[ERRO]: findRegEntradaQuarto " + e.getMessage());
        } finally {
            ConnectionDB.closeConnection(con, stmt, rs);
        }
        return listCliente;
    }

}
