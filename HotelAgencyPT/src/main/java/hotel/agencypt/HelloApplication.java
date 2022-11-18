package hotel.agencypt;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class HelloApplication extends Application {
    public void start(Stage stage) throws IOException {
<<<<<<< HEAD

        Parent root = FXMLLoader.load(getClass().getResource("Cliente.fxml"));
=======
        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
>>>>>>> d0b5c53244b125d85b3de6ece4c66aa30dc5bd7d
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(new Scene(root, 520, 400));
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}