package DataBase;

import java.sql.*;

import javafx.stage.Stage;


public class ConnectionDB {

    static Connection connect = null;

    /**
     * Conexão com a base de dados
     *
     * @return Conexão da base dados
     */
    public static Connection establishConnection() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connect = DriverManager.getConnection("jdbc:sqlserver://CTESPBD.DEI.ISEP.IPP.PT", "2022_E_LP3_G2", "LP3_bdG2");
            System.out.println("A carregar informacoes da base de dados...");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Erro ao conectar a base de dados! \n Erro: ");
            e.printStackTrace();
        }
        return connect;
    }

    /**
     * Fechar a conexão com a base de dados atraves da conexão
     *
     * @param con Conexão com a base de dados
     */
    public static void closeConnection(Connection con) {
        if (con != null) {
            try {
                con.close();
            } catch (SQLException e) {
                System.err.println("Erro: " + e.getMessage());
            }
        }
    }

    /**
     * Fechar através da conexão com a base de dados e da declaração preparada para inserir os valores
     *
     * @param con Conexão com a base de dados
     * @param stmt Declaração preparada para inserir
     */
    public static void closeConnection(Connection con, PreparedStatement stmt) {
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                System.err.println("Erro: " + e.getMessage());
            }
        }
        closeConnection(con);
    }

    /**
     * Fechar através da conexão com a base de dados, da declaração preparada para inserir e o resultados inseridos
     *
     * @param con Conexão com a base de dados
     * @param stmt Declaração preparada para inserir/update/delete/...
     * @param rs Conjunto de resultados para inserir/update/delete/...
     */
    public static void closeConnection(Connection con, PreparedStatement stmt, ResultSet rs){
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                System.err.println("Erro: " + e.getMessage());
            }
        }
        closeConnection(con, stmt);
    }
}

