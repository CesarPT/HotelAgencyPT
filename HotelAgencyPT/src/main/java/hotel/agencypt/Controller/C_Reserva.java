package hotel.agencypt.Controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import java.net.URL;
import java.util.ResourceBundle;

public class C_Reserva implements Initializable {

    @FXML
    public ComboBox cboxquarto;
    @FXML
    ObservableList<String> listTquarto = FXCollections.observableArrayList("Singular", "Duplo", "Familiar");
    @FXML
    private ListView<String> listServtodos;
    @FXML
    private ListView<String> listServesco;
    String[] servico = {"servico 1", "servico 2", "servico 3", "servico 4"};
    String servicoselct;
    String servicoselce;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cboxquarto.setItems(listTquarto);

        listServtodos.getItems().addAll(servico);
        listServtodos.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                servicoselct = listServtodos.getSelectionModel().getSelectedItem();
            }
        });
    }

        public void onAdicionar () {
            listServesco.getItems().add(servicoselct);
            listServtodos.getItems().remove(servicoselct);
        }

        public void onRemover () {
            listServtodos.getItems().add(servicoselce);
            listServesco.getItems().remove(servicoselce);
        }
    }

