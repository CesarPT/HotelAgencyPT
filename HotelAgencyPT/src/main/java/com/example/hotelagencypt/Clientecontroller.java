package com.example.hotelagencypt;

import hotel.agencypt.Controller.Singleton;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class Clientecontroller {

    @FXML
    protected Button creatReserva;

    public void abrirCLienteReserva() {
        try {
            Stage window = (Stage) creatReserva.getScene().getWindow();
            window.close();
            Singleton.open("clientereserva", "Hotel >> Cliente >> Criar reserva");

        } catch (Exception e) {
            System.out.println("erro ao abrir o scene");
        }
    }
}


