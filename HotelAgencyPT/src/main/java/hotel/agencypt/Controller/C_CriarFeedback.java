package hotel.agencypt.Controller;

import Classes.Cliente;
import Classes.DAO.ClienteDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.awt.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Classe pública do controlador GH_GerirFeedback.fxml
 */
public class C_CriarFeedback implements Initializable {
    public TextField textIDCliente;
    public TextArea textReclamacao;
    public TextArea textSugestao;

    ClienteDAO cDAO = new ClienteDAO();
    List<Classes.Cliente> arrayCliente = new ArrayList<>();
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        arrayCliente = cDAO.findIDCliente();
        for (Cliente c : arrayCliente) {
            textIDCliente.setText(String.valueOf(c.getIdCliente()));
        }
    }

    /**
     * Quando clica no + adiciona a reclamação
     */
    public void adicionarReclamacao(){
        if (textReclamacao.getText().length() < 30){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Aviso");
            alert.setHeaderText("Sem descrição");
            alert.setContentText("Crie uma descrição maior.");
            alert.showAndWait();
        } else if (textReclamacao.getText().length() > 500){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Aviso");
            alert.setHeaderText("Descrição grande");
            alert.setContentText("Crie uma descrição menor.");
            alert.showAndWait();
        } else {
        }
    }
    /**
     * Quando clica no + adiciona a sugestão
     */
    public void adicionarSugestao(){
        if (textReclamacao.getText().length() < 30){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Aviso");
            alert.setHeaderText("Sem descrição");
            alert.setContentText("Crie uma descrição maior.");
            alert.showAndWait();
        } else if (textReclamacao.getText().length() > 500){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Aviso");
            alert.setHeaderText("Descrição grande");
            alert.setContentText("Crie uma descrição menor.");
            alert.showAndWait();
        } else {

        }
    }

    /**
     * Volta atrás para a View GestorHotel.fxml
     *
     * @param actionEvent
     */
    @FXML
    public void voltarAtras(ActionEvent actionEvent) {
        try {
            Singleton.open("Cliente", "User: " + Controller.getInstance().getUsername() + " | Hotel >> Cliente");
        } catch (Exception e) {
            System.out.println("Erro ao voltar atrás.");
        }
    }

}
