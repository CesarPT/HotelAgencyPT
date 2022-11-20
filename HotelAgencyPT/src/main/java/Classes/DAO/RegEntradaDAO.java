package Classes.DAO;

import Classes.Cartao;
import Classes.RegEntrada;
import Classes.Reserva;
import DataBase.ConnectionDB;
import hotel.agencypt.Controller.Controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe pública que recebe dados da Base de Dados
 */
public class RegEntradaDAO {
    private Connection con;

    /**
     * Ligar à base de dados
     */
    public RegEntradaDAO(){
        con = ConnectionDB.establishConnection();
    }

    /**
     * Método para pesquisar o cartão com o username
     */
    public List<RegEntrada> findRegEntradaQuarto() {
        String sql = "SELECT RegEntrada.numcartao, RegEntrada.data, RegEntrada.local\n" +
                "FROM RegEntrada\n" +
                "INNER JOIN Cartao\n" +
                "ON Cartao.numcartao = RegEntrada.numcartao\n" +
                "INNER JOIN Reserva\n" +
                "ON Reserva.numcartao = Cartao.numcartao\n" +
                "INNER JOIN Cliente\n" +
                "ON Cliente.idcliente = Reserva.idcliente\n" +
                "INNER JOIN Utilizador\n" +
                "ON Utilizador.iduser = Cliente.iduser\n" +
                "WHERE Utilizador.nomeuser='"+Controller.getInstance().getUsername()+"'";

        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<RegEntrada> listRegEntrada = new ArrayList<>();

        try {
            con = ConnectionDB.establishConnection();
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                RegEntrada regentrada = new RegEntrada();
                regentrada.setNumcartao(rs.getInt("numcartao"));
                regentrada.setData(rs.getString("data"));
                regentrada.setLocal(rs.getString("local"));
                listRegEntrada.add(regentrada);
            }

        } catch (SQLException e) {
            System.err.println("[ERRO]: findRegEntradaQuarto " + e.getMessage());
        } finally {
            ConnectionDB.closeConnection(con, stmt, rs);
        }
        return listRegEntrada;
    }

    /**
     * Método para inserir registro de entrada
     * @param regentrada
     * @return
     */
    public boolean insertRegEntrada(RegEntrada regentrada){
        String sql = "INSERT INTO RegEntrada (local, data, numcartao) VALUES (?, ?, ?)";
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, regentrada.getLocal());
            stmt.setString(2, regentrada.getData());
            stmt.setInt(3, regentrada.getNumcartao());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("[ERRO]: insertRegEntrada " +e.getMessage());
            return false;
        }finally {
            ConnectionDB.closeConnection(con,stmt);
        }
    }
}
