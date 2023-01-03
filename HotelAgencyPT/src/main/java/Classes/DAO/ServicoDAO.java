package Classes.DAO;

import Classes.Servico;
import DataBase.ConnectionDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


/**
 * Classe pública que recebe dados da Base de Dados
 */
public class ServicoDAO {
    private static Connection con = ConnectionDB.establishConnection();

    /**
     * Método para pesquisar reservas pelo username
     */
    public static List<Servico> findServico() {
        String sql = "SELECT idservico, descricao,preco from Servico";

        List<Servico> listservico = new ArrayList<>();

        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Servico servico = new Servico();
                servico.setIdServico(rs.getInt("idservico"));
                servico.setDescricao(rs.getString("descricao"));
                servico.setPreco(rs.getFloat("preco"));

                listservico.add(servico);
            }
        } catch (SQLException e) {
            System.err.println("[ERRO]: findServico " + e.getMessage());
        }
        return listservico;
    }


    public static List<Servico> findServicoEsc(String descricao) {
        String sql = "SELECT idservico from Servico where descricao='" + descricao + "'";

        List<Servico> listservico = new ArrayList<>();

        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Servico servico = new Servico();
                servico.setIdServico(rs.getInt("idservico"));

                listservico.add(servico);
            }

        } catch (SQLException e) {
            System.err.println("[ERRO]: findServico " + e.getMessage());
        }
        return listservico;
    }


    public  List<Servico> findPrecoServico(String descricao) {
        String sql = "SELECT preco from Servico where descricao='" + descricao + "'";

        List<Servico> listservico = new ArrayList<>();

        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Servico servico = new Servico();
                servico.setPreco(rs.getInt("preco"));

                listservico.add(servico);
            }

        } catch (SQLException e) {
            System.err.println("[ERRO]: findServico " + e.getMessage());
        }
        return listservico;
    }

    public  List<Servico> findEstadoServico(String descricao) {
        String sql = "SELECT estado from Servico where descricao='" + descricao + "'";

        List<Servico> listservico = new ArrayList<>();

        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Servico servico = new Servico();
                servico.setEstado(rs.getString("estado"));

                listservico.add(servico);
            }

        } catch (SQLException e) {
            System.err.println("[ERRO]: findServico " + e.getMessage());
        }
        return listservico;
    }


    public static boolean criaServico(String descricao, float preco,String estado) {
        String sql = "INSERT INTO Servico (descricao,preco,estado) Values (?,?,?)";

        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, descricao);
            stmt.setFloat(2, preco);
            stmt.setString(3, estado);

            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("[ERRO]: criaServico " + e.getMessage());
            return false;
        }
    }

    public static boolean ativaServico(String descricao) {
        String sql = "update Servico set estado='Ativo' where descricao=?";


        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, descricao);

            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("[ERRO]: apagaServico " + e.getMessage());
            return false;
        }
    }

    public static boolean inativaServico(String descricao) {
        String sql = "update Servico set estado='Inativo' where descricao=?";


        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, descricao);

            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("[ERRO]: apagaServico " + e.getMessage());
            return false;
        }
    }

}