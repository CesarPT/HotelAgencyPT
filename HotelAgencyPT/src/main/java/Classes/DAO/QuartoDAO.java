package Classes.DAO;

import DataBase.ConnectionDB;

import java.sql.Connection;

public class QuartoDAO {
    private static Connection con;

    /**
     * Ligar à base de dados
     */
    public QuartoDAO() {
        con = ConnectionDB.establishConnection();
    }



}
