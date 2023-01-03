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
    private static Connection con = ConnectionDB.establishConnection();

    /**
     * Método para pesquisar a descrição de quartos do piso escolhido
     *
     * @return lista
     */
    public List<Quarto> findQuarto() {
        String sql = "SELECT idquarto, descricao\n" +
                "FROM Quarto\n" +
                "WHERE piso='" + Controller.getInstance().getPiso() + "'";

        List<Quarto> listQuarto = new ArrayList<>();

        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Quarto quarto = new Quarto();
                quarto.setIdQuarto(rs.getInt("idquarto"));
                quarto.setDescricao(rs.getString("descricao"));
                listQuarto.add(quarto);
            }

        } catch (SQLException e) {
            System.err.println("[ERRO]: findRegEntradaQuarto " + e.getMessage());
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
                "WHERE idquarto=" + Controller.getInstance().getIdQuarto();
        //" AND descricao='" + Controller.getInstance().getDescricaoQuarto() + "'"+

        List<Quarto> listPreco = new ArrayList<>();

        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Quarto quarto = new Quarto();
                quarto.setPreco(rs.getFloat("preco"));
                listPreco.add(quarto);
            }

        } catch (SQLException e) {
            System.err.println("[ERRO]: findPreco " + e.getMessage());
        }
        return listPreco;
    }

    /**
     * @return true or false
     */
    public boolean updatePreco(Quarto quarto) {
        String sql = "UPDATE Quarto SET preco = ?" +
                " WHERE idquarto=" + Controller.getInstance().getIdQuarto();

        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setFloat(1, quarto.getPreco());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("[ERRO]: updatePreco " + e.getMessage());
            return false;
        }
    }

    public List<Quarto> findDescricaoQuarto() {
        String sql = "select descricaoQuarto " +
                "from Quarto " +
                "where idquarto=" + Controller.getInstance().getIdQuarto();


        List<Quarto> listDescricaoQuarto = new ArrayList<>();
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Quarto quarto = new Quarto();
                quarto.setDescricaoQuarto(rs.getString("descricaoQuarto"));
                listDescricaoQuarto.add(quarto);
            }
        } catch (SQLException e) {
            System.err.println("[ERRO]: DescricaoQuarto " + e.getMessage());
        }
        return listDescricaoQuarto;
    }


    public List<Quarto> findQuartoIndividual() {
        String sql = "select TOP 1 percent idquarto " +
                "from Quarto " +
                "where descricao='Individual'" +
                "and estado='Disponivel' " +
                "ORDER by idquarto;";

        List<Quarto> listquarto = new ArrayList<>();

        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Quarto quarto = new Quarto();
                quarto.setIdQuarto(rs.getInt("idquarto"));
                listquarto.add(quarto);
            }
        } catch (SQLException e) {
            System.err.println("[ERRO]: findReserva " + e.getMessage());
        }
        return listquarto;
    }

    public List<Quarto> findQuartoDuplo() {
        String sql = "select TOP 1 percent idquarto " +
                "from Quarto " +
                "where descricao='Duplo'" +
                "and estado='Disponivel' " +
                "ORDER by idquarto;";

        List<Quarto> listquarto = new ArrayList<>();

        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Quarto quarto = new Quarto();
                quarto.setIdQuarto(rs.getInt("idquarto"));
                listquarto.add(quarto);
            }
        } catch (SQLException e) {
            System.err.println("[ERRO]: findReserva " + e.getMessage());
        }
        return listquarto;
    }

    public List<Quarto> findQuartoFamiliar() {
        String sql = "select TOP 1 percent idquarto " +
                "from Quarto " +
                "where descricao='Familiar'" +
                "and estado='Disponivel' " +
                "ORDER by idquarto;";

        List<Quarto> listquarto = new ArrayList<>();

        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Quarto quarto = new Quarto();
                quarto.setIdQuarto(rs.getInt("idquarto"));
                listquarto.add(quarto);
            }
        } catch (SQLException e) {
            System.err.println("[ERRO]: findReserva " + e.getMessage());
        }
        return listquarto;
    }

    public boolean updateDescricao(Quarto quarto) {
        String sql = "UPDATE Quarto SET descricaoQuarto = ?" +
                " WHERE idquarto=" + Controller.getInstance().getIdQuarto();

        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, quarto.getDescricao());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("[ERRO]: updateDescricaoQuarto " + e.getMessage());
            return false;
        }
    }
}
