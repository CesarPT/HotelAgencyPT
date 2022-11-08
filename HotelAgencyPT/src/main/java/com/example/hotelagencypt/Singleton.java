package com.example.hotelagencypt;

import com.example.hotelagencypt.OpenApplication;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Classe pública que abre e fecha menus/views
 */
public class Singleton {
    private static Stage stage = null;

    /**
     * Abrir o stage seguinte com parâmetros
     * @param id
     * @param title
     * @throws Exception
     */
    private static void openStage(String id, String title) throws Exception {
        //Resolver problema do null
        if (stage == null) stage = new Stage();

        Scene scene = new Scene(getLoader(id));
        Image image = new Image("https://cdn-icons-png.flaticon.com/512/5900/5900308.png");
        stage.getIcons().add(image);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.setTitle(title);
        stage.show();
    }

    /**
     * Abrir o stage seguinte
     * @param id
     * @param title
     * @throws Exception
     */
    public static void open(String id, String title) throws Exception {
        openStage(id, title);
    }

    /**
     * Fechar o stage anterior
     * @param scene
     * @throws Exception
     */
    public static void close(Scene scene) throws Exception {
        ((Stage) scene.getWindow()).close();
    }

    /**
     * Recebe a localização da pasta onde estão os ficheiros .fxml
     * @param path
     * @return
     * @throws Exception
     */
    private static Parent getLoader(String path) throws Exception {
        try {
            return new FXMLLoader(OpenApplication.class.getResource(path + ".fxml")).load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
