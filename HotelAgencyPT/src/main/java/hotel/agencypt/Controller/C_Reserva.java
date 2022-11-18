package hotel.agencypt.Controller;

import javafx.beans.InvalidationListener;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.net.URL;
import java.util.*;

public class C_Reserva implements Initializable {


    @FXML
    protected Button creatReserva ;

    @FXML
    public ComboBox quarto ;
    @FXML
    ObservableList<String> listTquarto = FXCollections.observableArrayList("Singular","Duplo","Familiar");

    @FXML
    private ListView<String> listServtodos;
    @FXML
    private ListView<String> listServesco;

    String[] servico ={"servico 1","servico 2","servico 3","servico 4"};
    String servicoselc;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        quarto.setItems(listTquarto);

        listServtodos.getItems().addAll(servico);
        listServtodos.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                servicoselc=listServtodos.getSelectionModel().getSelectedItem();
                //teste.setText(reservasel);

            }
        });

    }

}