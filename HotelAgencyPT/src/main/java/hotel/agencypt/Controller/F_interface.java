package hotel.agencypt.Controller;


import DataBase.ConnectionDB;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.sql.Connection;

public class F_interface {
    @FXML
    private Button Registerbutton;
    @FXML
    private Button LoginButton;
    @FXML
    private Button RoomServices;
    @FXML
    private Button Stock;

    Connection con = ConnectionDB.establishConnection();

    public void ClientRegister(ActionEvent event) throws Exception {
        Stage stage = (Stage) Registerbutton.getScene().getWindow();
        stage.close();
        Singleton.open("Register", "Hotel >> Login");
    }

    public void switchToLogin(ActionEvent event) throws Exception {
        Stage stage = (Stage) LoginButton.getScene().getWindow();
        stage.close();
        Singleton.open("Login", "Hotel >> Login");
    }

    public void abrirConfigQuarto(ActionEvent event) throws Exception {
        try {
            Stage window = (Stage) RoomServices.getScene().getWindow();
            window.close();
            Singleton.open("GH_ConfigQuarto", "Hotel >> Gestor de Hotel >> Configurar quarto");
        } catch (Exception e) {
            System.out.println("Erro ao fechar/abrir o stage.");
        }
    }

    public void abrirGerirStock(ActionEvent event) throws Exception {
        try {
            Stage window = (Stage) Stock.getScene().getWindow();
            window.close();
            Singleton.open("GH_GerirStock", "Hotel >> Gestor de Hotel >> Gerir Stock");
        } catch (Exception e) {
            System.out.println("Erro ao fechar/abrir o stage.");
        }
    }
}
