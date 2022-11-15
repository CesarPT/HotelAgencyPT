package com.example.hotelagencypt.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

/**
 * Classe pública do controlador GH_RegistrosCartao.fxml
 */
public class Menu {
    /**
     * Volta atrás para a View GestorHotel.fxml
     * @param actionEvent
     */
    @FXML
    public void voltarAtras(ActionEvent actionEvent) {
        try {
            Singleton.open("Login", "Hotel Agency PT");
        } catch (Exception e) {
            System.out.println("Erro ao voltar atrás.");
        }
    }
}
