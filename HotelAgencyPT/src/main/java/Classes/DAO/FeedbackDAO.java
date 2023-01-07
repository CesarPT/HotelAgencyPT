package Classes.DAO;

import Classes.Feedback;
import DataBase.ConnectionDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class FeedbackDAO {
    private static final Connection con = ConnectionDB.establishConnection();

    /**
     * Criar uma reclamação
     *
     * @param feedback
     * @return
     */
    public static boolean criarReclamacao(Feedback feedback) {
        String sql = "INSERT INTO Feedback (idcliente,descricao, tipofeedback) Values (?,?,?)";

        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, feedback.getId_cliente());
            stmt.setString(2, feedback.getDescricao());
            stmt.setString(3, String.valueOf(feedback.getTipofeedback()));
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("[ERRO]: criarReclamacao |" + e.getMessage());
            return false;
        }
    }

    /**
     * Criar uma sugestão
     *
     * @param feedback
     * @return
     */
    public static boolean criarSugestao(Feedback feedback) {
        String sql = "INSERT INTO Feedback (idcliente,descricao, tipofeedback) Values (?,?,?)";

        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, feedback.getId_cliente());
            stmt.setString(2, feedback.getDescricao());
            stmt.setString(3, String.valueOf(feedback.getTipofeedback()));
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("[ERRO]: criarReclamacao |" + e.getMessage());
            return false;
        }
    }
}
