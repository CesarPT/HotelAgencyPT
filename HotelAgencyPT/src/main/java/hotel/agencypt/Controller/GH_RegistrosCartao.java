package hotel.agencypt.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * Classe pública do controlador GH_RegistrosCartao.fxml
 */
public class GH_RegistrosCartao {
    @FXML
    protected Button button;

    @FXML
    protected void abrirRegistrosCartao() throws Exception {
        Stage window = (Stage) button.getScene().getWindow();
        window.close();
        Singleton.open("RegistrosCartao", "Hotel >> Gestor de Hotel >> Ver registros de um cartão");
    }

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
}
