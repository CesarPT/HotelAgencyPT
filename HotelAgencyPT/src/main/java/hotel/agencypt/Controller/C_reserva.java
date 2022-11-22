package hotel.agencypt.Controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
<<<<<<< HEAD
=======
import javafx.scene.control.Button;
>>>>>>> feature/XML
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;

import java.net.URL;
<<<<<<< HEAD
import java.util.ResourceBundle;
=======
import java.util.*;
>>>>>>> feature/XML

public class C_Reserva implements Initializable {

    @FXML
<<<<<<< HEAD
    public ComboBox cboxquarto;
    @FXML
    ObservableList<String> listTquarto = FXCollections.observableArrayList("Singular", "Duplo", "Familiar");
=======
    public ComboBox cboxquarto ;
    @FXML
    ObservableList<String> listTquarto = FXCollections.observableArrayList("Singular","Duplo","Familiar");
>>>>>>> feature/XML

    @FXML
    private ListView<String> listServtodos;
    @FXML
    private ListView<String> listServesco;

<<<<<<< HEAD
    String[] servico = {"servico 1", "servico 2", "servico 3", "servico 4"};
=======
    String[] servico ={"servico 1","servico 2","servico 3","servico 4"};
>>>>>>> feature/XML
    String servicoselct;
    String servicoselce;


<<<<<<< HEAD
=======

>>>>>>> feature/XML
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cboxquarto.setItems(listTquarto);

        listServtodos.getItems().addAll(servico);
        listServtodos.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
<<<<<<< HEAD
                servicoselct = listServtodos.getSelectionModel().getSelectedItem();
=======
                servicoselct=listServtodos.getSelectionModel().getSelectedItem();
>>>>>>> feature/XML
            }
        });

        listServesco.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
<<<<<<< HEAD
                servicoselce = listServesco.getSelectionModel().getSelectedItem();
=======
                servicoselce=listServesco.getSelectionModel().getSelectedItem();
>>>>>>> feature/XML
            }
        });

    }

<<<<<<< HEAD
    public void onAdicionar() {
=======
    public void onAdicionar(){
>>>>>>> feature/XML
        listServesco.getItems().add(servicoselct);
        listServtodos.getItems().remove(servicoselct);
    }

<<<<<<< HEAD
    public void onRemover() {
=======
    public void onRemover(){
>>>>>>> feature/XML
        listServtodos.getItems().add(servicoselce);
        listServesco.getItems().remove(servicoselce);
    }


}