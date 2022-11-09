package com.example.hotelagencypt.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class ConfigQuarto {

    /**
     * Volta atrás para a View GestorHotel.fxml
     * @param actionEvent
     */
    @FXML
    public void voltarAtras(ActionEvent actionEvent) {
        try {
            Singleton.open("GestorHotel", "Hotel >> Gestor de Hotel");
        } catch (Exception e) {
            System.out.println("Erro ao voltar atrás.");
        }
    }
}
