package DataBase;

import Classes.SearchFile;

import java.sql.*;
import java.util.List;

/**
 * Classe pública ConnectionDB
 */
public class ConnectionDB {

    static Connection connect = null;

    /**
     * Conexão com a base de dados
     *
     * @return Conexão da base dados
     */
    public static Connection establishConnection() {
        List<String> list = SearchFile.SearchDB();

        String url = list.get(0);
        String user = list.get(1);
        String pass = list.get(2);
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connect = DriverManager.getConnection(url, user, pass);
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
     * @param con  Conexão com a base de dados
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
     * @param con  Conexão com a base de dados
     * @param stmt Declaração preparada para inserir/update/delete/...
     * @param rs   Conjunto de resultados para inserir/update/delete/...
     */
    public static void closeConnection(Connection con, PreparedStatement stmt, ResultSet rs) {
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

