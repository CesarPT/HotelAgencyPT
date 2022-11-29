package Classes.DAO;

//Bibliotecas

import Classes.Quarto;
import hotel.agencypt.Controller.Controller;
import DataBase.ConnectionDB;
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
    private static Connection con;

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
        String sql = "SELECT idquarto, descricao\n" +
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
                quarto.setIdQuarto(rs.getInt("idquarto"));
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
                "WHERE piso=" + Controller.getInstance().getPiso() +
               //" AND descricao='" + Controller.getInstance().getDescricaoQuarto() + "'"+
                "WHERE idquarto=" + Controller.getInstance().getIdQuarto();

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
                " WHERE idquarto=" + Controller.getInstance().getIdQuarto();
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

    public boolean updateDescricao(Quarto quarto) {
        String sql = "UPDATE Quarto SET descricaoQuarto = ?" +
                " WHERE idquarto=" + Controller.getInstance().getIdQuarto();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, quarto.getDescricao());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("[ERRO]: updateDescricaoQuarto " + e.getMessage());
            return false;
        } finally {
            ConnectionDB.closeConnection(con, stmt);
        }
    }

    public List<Quarto> findDescricaoQuarto() {
        String sql = "select descricaoQuarto " +
                "from Quarto " +
                "where idquarto=" + Controller.getInstance().getIdQuarto();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Quarto> listDescricaoQuarto = new ArrayList<>();
        try {
            con = ConnectionDB.establishConnection();
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Quarto quarto = new Quarto();
                quarto.setDescricaoQuarto(rs.getString("descricaoQuarto"));
                listDescricaoQuarto.add(quarto);
            }
        } catch (SQLException e) {
            System.err.println("[ERRO]: DescricaoQuarto " + e.getMessage());
        } finally {
            ConnectionDB.closeConnection(con, stmt, rs);
        }
        return listDescricaoQuarto;
    }


    public  List<Quarto> findQuartoIndividual() {
        String sql = "select TOP 1 percent idquarto " +
                "from Quarto " +
                "where descricao='Individual'" +
                "and estado='Disponivel' " +
                "ORDER by idquarto;";

        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Quarto> listquarto = new ArrayList<>();

        try {
            con = ConnectionDB.establishConnection();
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Quarto quarto = new Quarto();
                quarto.setIdQuarto(rs.getInt("idquarto"));
                listquarto.add(quarto);
            }
        } catch (SQLException e) {
            System.err.println("[ERRO]: findReserva " + e.getMessage());
        } finally {
            ConnectionDB.closeConnection(con, stmt, rs);
        }
        return listquarto;
    }

    public  List<Quarto> findQuartoDuplo() {
        String sql = "select TOP 1 percent idquarto " +
                "from Quarto " +
                "where descricao='Duplo'" +
                "and estado='Disponivel' " +
                "ORDER by idquarto;";

        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Quarto> listquarto = new ArrayList<>();

        try {
            con = ConnectionDB.establishConnection();
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Quarto quarto = new Quarto();
                quarto.setIdQuarto(rs.getInt("idquarto"));
                listquarto.add(quarto);
            }
        } catch (SQLException e) {
            System.err.println("[ERRO]: findReserva " + e.getMessage());
        } finally {
            ConnectionDB.closeConnection(con, stmt, rs);
        }
        return listquarto;
    }
    public  List<Quarto> findQuartoFamiliar() {
        String sql = "select TOP 1 percent idquarto " +
                "from Quarto " +
                "where descricao='Familiar'" +
                "and estado='Disponivel' " +
                "ORDER by idquarto;";

        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Quarto> listquarto = new ArrayList<>();

        try {
            con = ConnectionDB.establishConnection();
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Quarto quarto = new Quarto();
                quarto.setIdQuarto(rs.getInt("idquarto"));
                listquarto.add(quarto);
            }
        } catch (SQLException e) {
            System.err.println("[ERRO]: findReserva " + e.getMessage());
        } finally {
            ConnectionDB.closeConnection(con, stmt, rs);
        }
        return listquarto;
    }

}
