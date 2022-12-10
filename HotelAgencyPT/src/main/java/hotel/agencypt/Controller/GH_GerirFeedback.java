package hotel.agencypt.Controller;

import Classes.Feedback;
import Classes.RegEntrada;
import DataBase.ConnectionDB;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * Classe pública do controlador GH_GerirFeedback.fxml
 */
public class GH_GerirFeedback implements Initializable {
    private Connection con = ConnectionDB.establishConnection();
    private Connection con2 = ConnectionDB.establishConnection();
    @FXML
    private TableView<Feedback> tableReclamacao;
    @FXML
    private TableView<Feedback> tableSugestao;
    @FXML
    private TableColumn<Feedback, Integer> columnID;
    @FXML
    private TableColumn<Feedback, Integer> columnID2;
    @FXML
    private TableColumn<Feedback, String> columnDescricao;
    @FXML
    private TableColumn<Feedback, String> columnDescricao2;

    ObservableList<Feedback> obsReclamacao = FXCollections.observableArrayList();
    ObservableList<Feedback> obsSugestao = FXCollections.observableArrayList();
    /**
     * Volta atrás para a View GestorHotel.fxml
     *
     * @param actionEvent
     */
    @FXML
    public void voltarAtras(ActionEvent actionEvent) {
        try {
            Singleton.open("GestorHotel", "Hotel >> Gestor de Hotel");
        } catch (Exception e) {
            System.out.println("Erro ao voltar atrás.");
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        String sql = "SELECT idcliente, descricao\n" +
                        "FROM Feedback\n" +
                        "WHERE tipofeedback='R'";

                PreparedStatement stmt = null;
                ResultSet rs = null;

                //Limpar tudo e Adicionar todos os cartões
                try {
                    stmt = con.prepareStatement(sql);
                    rs = stmt.executeQuery();

                    while (rs.next()) {
                        Integer idcliente = rs.getInt("idcliente");
                        String descricao = rs.getString("descricao");
                obsReclamacao.add(new Feedback(idcliente, descricao));
            }
            //Colocar os valores nas colunas da TableView
            columnID.setCellValueFactory(new PropertyValueFactory<>("id_cliente"));
            columnDescricao.setCellValueFactory(new PropertyValueFactory<>("descricao"));
            tableReclamacao.setItems(obsReclamacao);
        } catch (SQLException e) {
            System.err.println("[ERRO]: initialize " + e.getMessage());
        }

        String sql2 = "SELECT idcliente, descricao\n" +
                "FROM Feedback\n" +
                "WHERE tipofeedback='S'";

        PreparedStatement stmt2 = null;
        ResultSet rs2 = null;

        //Limpar tudo e Adicionar todos os cartões
        try {
            stmt2 = con2.prepareStatement(sql2);
            rs2 = stmt2.executeQuery();

            while (rs2.next()) {
                Integer idcliente = rs2.getInt("idcliente");
                String descricao = rs2.getString("descricao");
                obsSugestao.add(new Feedback(idcliente, descricao));
            }
            //Colocar os valores nas colunas da TableView
            columnID2.setCellValueFactory(new PropertyValueFactory<>("id_cliente"));
            columnDescricao2.setCellValueFactory(new PropertyValueFactory<>("descricao"));
            tableSugestao.setItems(obsSugestao);
        } catch (SQLException e) {
            System.err.println("[ERRO]: initialize " + e.getMessage());
        } finally {
            ConnectionDB.closeConnection(con2, stmt2, rs2);
        }
    }
}
