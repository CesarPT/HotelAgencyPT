package hotel.agencypt.Controller;

import Classes.Feedback;
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
    private final Connection con = ConnectionDB.establishConnection();
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

    /**
     * Método chamado ao iniciar a scene
     * @param location
     * The location used to resolve relative paths for the root object, or
     * {@code null} if the location is not known.
     *
     * @param resources
     * The resources used to localize the root object, or {@code null} if
     * the root object was not localized.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        String sql = "SELECT idcliente, descricao\n" +
                "FROM Feedback\n" +
                "WHERE tipofeedback='R'";


        //Limpar tudo e Adicionar todos os cartões
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

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

        //Limpar tudo e Adicionar todos os cartões
        try {
            PreparedStatement stmt2 = con.prepareStatement(sql2);
            ResultSet rs2 = stmt2.executeQuery();

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
        }
    }
}
