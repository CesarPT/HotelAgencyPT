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
    /**
     * @param stage the primary stage for this application, onto which
     * the application scene can be set.
     * Applications may create other stages, if needed, but they will not be
     * primary stages.
     */
    public void start(Stage stage) {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Login.fxml")));
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setScene(new Scene(root, 520, 400));//Login
            //stage.setScene(new Scene(root, 765, 480));//Register
            stage.show();
        } catch (IOException e) {
            System.out.println("[ERRO] : " + e.getMessage());
        }
    }

    /**
     * Classe estática sem retorno que inicia a aplicação
     * @param args
     */
    public static void main(String[] args) {
        launch();
    }

}