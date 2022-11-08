package com.example.hotelagencypt;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * Classe pública do controlador GestorHotel.fxml
 */
public class RegistrosCartao {
    @FXML
    protected Button button;
    @FXML
    protected void abrirRegistrosCartao() throws Exception {
        Stage window = (Stage) button.getScene().getWindow();
        window.close();
        Singleton.open("RegistrosCartao", "Hotel >> Gestor de Hotel >> Ver registros de um cartão");
    }

}
