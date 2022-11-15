package hotel.agencypt.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

/**
 * Classe pública do controlador GH_GerirFeedback.fxml
 */
public class GH_GerirFeedback {
    @FXML
    private ListView<String> listViewReclamacoes;

    /**
     * Classe pública que inicia a scene com o que está dentro dela
     */
    public void initialize() {
        listViewReclamacoes.getItems().add(
                "aaaaaaaaaaaaaaaaaaaasadasdDASIDJASOD#$%IQWJD DWIDOQWJD WDQW IDQWD JQOWDQWDIQOWDIQJDIOQ DI WQaa!!"
        );
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
