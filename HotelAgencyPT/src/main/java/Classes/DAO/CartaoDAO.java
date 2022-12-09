package Classes.DAO;

import Classes.Cartao;
import DataBase.ConnectionDB;
import hotel.agencypt.Controller.Controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe pública que recebe dados da Base de Dados
 */
public class CartaoDAO {
    private Connection con = ConnectionDB.establishConnection();
    ;

    /**
     * Ligar à base de dados
     */
    public CartaoDAO() {
    }

    /**
     * Método para pesquisar o cartão com o username
     */
    public List<Cartao> findCartao() {
        String sql = "SELECT Cartao.numcartao, Cartao.datacriacao, Cartao.dataexp\n" +
                "FROM Cartao\n" +
                "INNER JOIN Reserva\n" +
                "ON Reserva.numcartao = Cartao.numcartao\n" +
                "INNER JOIN Cliente\n" +
                "ON Cliente.idcliente = Reserva.idcliente\n" +
                "INNER JOIN Utilizador\n" +
                "ON Utilizador.iduser = Cliente.iduser\n" +
                "WHERE Utilizador.nomeuser=" + "'" + Controller.getInstance().getUsername() + "'";

        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Cartao> listcartao = new ArrayList<>();
        try {
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Cartao cartao = new Cartao();
                cartao.setNumcartao(rs.getInt("numcartao"));
                cartao.setDatacriacao(rs.getDate("datacriacao"));
                cartao.setDataexp(rs.getDate("dataexp"));
                listcartao.add(cartao);
            }

        } catch (SQLException e) {
            System.err.println("[ERRO]: findCartao " + e.getMessage());
        } finally {
            ConnectionDB.closeConnection(con, stmt, rs);
        }
        return listcartao;
    }

}
