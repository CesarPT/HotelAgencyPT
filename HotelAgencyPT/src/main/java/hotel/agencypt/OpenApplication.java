package hotel.agencypt;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.Objects;

public class OpenApplication extends Application {
    public void start(Stage stage) {
        try {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Login.fxml")));
        stage.initStyle(StageStyle.UNDECORATED);

        stage.setScene(new Scene(root, 520, 400));//Login
        //stage.setScene(new Scene(root, 765, 480));//Register
        stage.show();
        } catch (IOException e){
            System.out.println("[ERRO] : " + e.getMessage());
        }
    }

    public static void main(String[] args) throws Exception {
        //metodo para ler e dar print na consola o xml
        //Lexml();
        launch();

    }

}