package hotel.agencypt.Controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Cliente implements Initializable {
    @FXML
    protected Button creatReserva;
    @FXML
    private ListView<String> listReserva;
    @FXML
    private Label teste;
    String[] reservas ={"reserva 1","reserva 2","reserva 3","reserva 4"};
    String reservasel;

    /**
     * @param location
     * The location used to resolve relative paths for the root object, or
     * {@code null} if the location is not known.
     *
     * @param resources
     * The resources used to localize the root object, or {@code null} if
     * the root object was not localized.
     *
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
            listReserva.getItems().addAll(reservas);
            listReserva.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
                @Override
                public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                    reservasel=listReserva.getSelectionModel().getSelectedItem();
                    teste.setText(reservasel);
                }
            });
    }

    /**
     * @param actionEvent
     */
    @FXML
    public void voltarAtras(ActionEvent actionEvent) {
        try {
            Singleton.open("Login", "Login");
        } catch (Exception e) {
            System.out.println("Erro ao voltar atrás.");
        }
    }


    /**
     *
     */
    public void abrirCLienteReserva(){
        try {
            Stage window = (Stage) creatReserva.getScene().getWindow();
            window.close();
            Singleton.open("C_Reserva", "Hotel >> Cliente");

        }
        catch (Exception e){
            System.out.println("erro ao abrir o scene");
        }
    }

}
