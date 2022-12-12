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
}