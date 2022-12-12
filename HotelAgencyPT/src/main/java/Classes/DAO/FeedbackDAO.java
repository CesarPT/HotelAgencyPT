package Classes.DAO;

import Classes.Feedback;
import Classes.Reserva;
import DataBase.ConnectionDB;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class FeedbackDAO {
    private static Connection con = ConnectionDB.establishConnection();

    public FeedbackDAO() {
    }

    /**
     * Criar uma reclamação
     * @param feedback
     * @return
     */
    public static boolean criarReclamacao(Feedback feedback) {
        String sql = "INSERT INTO Feedback (idcliente,descricao, tipofeedback) Values (?,?,?)";
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, feedback.getId_cliente());
            stmt.setString(2, feedback.getDescricao());
            stmt.setString(3, String.valueOf(feedback.getTipofeedback()));
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("[ERRO]: criarReclamacao |" + e.getMessage());
            return false;
        } finally {
            ConnectionDB.closeConnection(con, stmt);
        }
    }


    /**
     * Criar uma sugestão
     * @param feedback
     * @return
     */
    public static boolean criarSugestao(Feedback feedback) {
        String sql = "INSERT INTO Feedback (idcliente,descricao, tipofeedback) Values (?,?,?)";
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, feedback.getId_cliente());
            stmt.setString(2, feedback.getDescricao());
            stmt.setString(3, String.valueOf(feedback.getTipofeedback()));
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("[ERRO]: criarReclamacao |" + e.getMessage());
            return false;
        } finally {
            ConnectionDB.closeConnection(con, stmt);
        }
    }
}