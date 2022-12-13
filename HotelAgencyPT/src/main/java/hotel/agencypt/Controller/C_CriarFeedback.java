package hotel.agencypt.Controller;

import Classes.Cliente;
import Classes.DAO.ClienteDAO;
import Classes.DAO.FeedbackDAO;
import Classes.Feedback;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

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
    public void adicionarReclamacao() {
        if (textReclamacao.getText().length() < 30) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Aviso");
            alert.setHeaderText("Sem descrição");
            alert.setContentText("Crie uma descrição maior.");
            alert.showAndWait();
        } else if (textReclamacao.getText().length() > 500) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Aviso");
            alert.setHeaderText("Descrição grande");
            alert.setContentText("Crie uma descrição menor.");
            alert.showAndWait();
        } else {
            //Criar reclamação na BD (Tabela: Feedback)
            FeedbackDAO feedbackDAO = new FeedbackDAO();
            Feedback feedback = new Feedback();
            feedback.setId_cliente(Integer.parseInt(textIDCliente.getText()));
            feedback.setDescricao(textReclamacao.getText());
            feedback.setTipofeedback('R');
            feedbackDAO.criarReclamacao(feedback);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Reclamação adicionada");
            alert.setContentText("Reclamação adicionada com sucesso. Obrigado :)");
            alert.showAndWait();
        }
    }

    /**
     * Quando clica no + adiciona a sugestão
     */
    public void adicionarSugestao() {
        if (textSugestao.getText().length() < 30) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Aviso");
            alert.setHeaderText("Sem descrição");
            alert.setContentText("Crie uma descrição maior.");
            alert.showAndWait();
        } else if (textSugestao.getText().length() > 500) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Aviso");
            alert.setHeaderText("Descrição grande");
            alert.setContentText("Crie uma descrição menor.");
            alert.showAndWait();
        } else {
            //Criar sugestão na BD (Tabela: Feedback)
            FeedbackDAO feedbackDAO = new FeedbackDAO();
            Feedback feedback = new Feedback();
            feedback.setId_cliente(Integer.parseInt(textIDCliente.getText()));
            feedback.setDescricao(textSugestao.getText());
            feedback.setTipofeedback('S');
            feedbackDAO.criarSugestao(feedback);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Sugestão adicionada");
            alert.setContentText("Sugestão adicionada com sucesso. Obrigado :)");
            alert.showAndWait();
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
