package hotel.agencypt.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;

public class ClientereservaController {


    ObservableList<String> obsReserva =
            FXCollections.observableArrayList(
                    "Option 1",
                    "Option 2",
                    "Option 3",
                    "Option 4"
            );
    final ComboBox comboBoxReserva = new ComboBox(obsReserva);

}
