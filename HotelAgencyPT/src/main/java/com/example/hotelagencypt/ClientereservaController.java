package com.example.hotelagencypt;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;

public class ClientereservaController {

    @FXML
    private ComboBox<String> tquarto;

    ObservableList<String> obsReserva =
            FXCollections.observableArrayList(
                    "Option 1",
                    "Option 2",
                    "Option 3",
                    "Option 4"
            );
    final ComboBox comboBoxReserva = new ComboBox(obsReserva);

}
