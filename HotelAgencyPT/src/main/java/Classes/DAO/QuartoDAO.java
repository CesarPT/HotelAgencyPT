package Classes.DAO;

//Bibliotecas

import Classes.Quarto;
import DataBase.ConnectionDB;
import hotel.agencypt.Controller.Controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe pública com todos os métodos/atributos necessários.
 */
public class QuartoDAO {
    private Connection con;

    /**
     * Ligar à base de dados
     */
    public QuartoDAO() {
        con = ConnectionDB.establishConnection();
    }

    /**
     * Método para pesquisar a descrição de quartos do piso escolhido
     *
     * @return lista
     */
    public List<Quarto> findQuarto() {
        String sql = "SELECT descricao\n" +
                "FROM Quarto\n" +
                "WHERE piso='" + Controller.getInstance().getPiso() + "'";

        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Quarto> listQuarto = new ArrayList<>();

        try {
            con = ConnectionDB.establishConnection();
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Quarto quarto = new Quarto();
                quarto.setDescricao(rs.getString("descricao"));
                listQuarto.add(quarto);
            }

        } catch (SQLException e) {
            System.err.println("[ERRO]: findRegEntradaQuarto " + e.getMessage());
        } finally {
            ConnectionDB.closeConnection(con, stmt, rs);
        }
        return listQuarto;
    }

    /**
     * Método para pesquisar o preço de um quarto escolhido
     *
     * @return lista
     */
    public List<Quarto> findPreco() {
        String sql = "SELECT preco\n" +
                "FROM Quarto\n" +
                "WHERE piso=" + Controller.getInstance().getPiso() + " AND descricao='" + Controller.getInstance().getDescricaoQuarto() + "'";

        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Quarto> listPreco = new ArrayList<>();

        try {
            con = ConnectionDB.establishConnection();
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Quarto quarto = new Quarto();
                quarto.setPreco(rs.getFloat("preco"));
                listPreco.add(quarto);
            }

        } catch (SQLException e) {
            System.err.println("[ERRO]: findPreco " + e.getMessage());
        } finally {
            ConnectionDB.closeConnection(con, stmt, rs);
        }
        return listPreco;
    }

    /**
     * @return true or false
     */
    public boolean updatePreco(Quarto quarto) {
        String sql = "UPDATE Quarto SET preco = ?" +
                " WHERE descricao='" + Controller.getInstance().getDescricaoQuarto() + "' AND" +
                " piso=" + Controller.getInstance().getPiso();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement(sql);
            stmt.setFloat(1, quarto.getPreco());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("[ERRO]: updatePreco " + e.getMessage());
            return false;
        } finally {
            ConnectionDB.closeConnection(con, stmt);
        }
    }

}
