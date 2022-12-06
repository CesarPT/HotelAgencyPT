package hotel.agencypt.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class CheckInController {
    @FXML
    private Button BackButton;

    public void BackToStaff(ActionEvent event) throws Exception {
        Stage stage = (Stage) BackButton.getScene().getWindow();
        stage.close();
        Singleton.open("funcionariointerface", "Hotel >> Funcionário");
    }

}
