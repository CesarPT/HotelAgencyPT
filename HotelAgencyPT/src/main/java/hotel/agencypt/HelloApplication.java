package hotel.agencypt;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
<<<<<<< HEAD:HotelAgencyPT/src/main/java/com/example/hotelagencypt/OpenApplication.java
        FXMLLoader fxmlLoader = new FXMLLoader(OpenApplication.class.getResource("clienteinterface.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 500);
        stage.setTitle("Hotel >> Gestor de Hotel");
        stage.setScene(scene);
=======
        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(new Scene(root, 520, 400));
>>>>>>> 332d8189b7499ab20d512fa525a55ad405379dfc:HotelAgencyPT/src/main/java/hotel/agencypt/HelloApplication.java
        stage.show();
    }
    
    public static void main(String[] args) {
        launch();
    }
}