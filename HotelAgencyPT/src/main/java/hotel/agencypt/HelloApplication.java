package hotel.agencypt;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

import static hotel.agencypt.Controller.XMLpath.Lexml;

public class HelloApplication extends Application {
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        stage.initStyle(StageStyle.UNDECORATED);

        stage.setScene(new Scene(root, 520, 400));//Login
        //stage.setScene(new Scene(root, 765, 480));//Register
        stage.show();
    }

    public static void main(String[] args) throws Exception {
        //metodo para ler o xml
       // Lexml();
        launch();

    }

}