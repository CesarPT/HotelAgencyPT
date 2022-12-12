package hotel.agencypt.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class StockQuartos {

    /**
     * Volta atrás
     * @param actionEvent
     */
    @FXML
    public void voltarAtras(ActionEvent actionEvent) {
        try {
            if (Controller.getInstance().getTipo_user() == 'G'){
                Singleton.open("GestorHotel", "Hotel >> Gestor de Hotel");
            } else {
                Singleton.open("funcionariointerface", "Hotel >> Funcionário");
            }
        } catch (Exception e) {
            System.out.println("Erro ao voltar atrás.");
        }
    }
}
