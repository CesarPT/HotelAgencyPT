package com.example.hotelagencypt;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * Classe pública do controlador GestorHotel.fxml
 */
public class GestorHotel {
    @FXML
    protected Button button;
    @FXML
    protected void abrirRegistrosCartao() {
        try {
            Stage window = (Stage) button.getScene().getWindow();
            window.close();
            Singleton.open("RegistrosCartao", "Hotel >> Gestor de Hotel >> Ver registros de um cartão");
        } catch (Exception e) {
            System.out.println("Parou.");
        }
    }

}
