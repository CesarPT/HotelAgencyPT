package hotel.agencypt.Controller;


import Classes.DAO.ReservaDAO;
import Classes.Quarto;
import Classes.Reserva;
import DataBase.ConnectionDB;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import static Classes.DAO.ReservaDAO.findReserva;

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

    public void abrirCriarReserva(ActionEvent event) throws Exception {
        try {
            Stage window = (Stage) RoomServices.getScene().getWindow();
            window.close();
            Singleton.open("F_Reserva", "Hotel >> Funcionario >> Criar reserva");
        } catch (Exception e) {
            System.out.println("Erro ao fechar/abrir o stage.");
        }
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


    int ultimaReserv;
    List<Reserva> arrayUltimaReserva = new ArrayList<>();
    String descservico;
    private ListView<String> listServesco;
    private TextField outracoisa;

    @FXML
    public void onteste(){
        arrayUltimaReserva = ReservaDAO.findUltReserva();
        for (Reserva r : arrayUltimaReserva) {
            ultimaReserv = r.getIdreserva();
        }

        System.out.println(ultimaReserv);
        descservico = listServesco.getItems().toString()
                .replace("[", "")
                .replace("]", "")
                .replace(" ", "")
                .replaceAll("[0-9]", "");
        System.out.println(descservico);
        //Soma o que está antes da virgula e depois da virgula
        outracoisa.setText(String.valueOf(descservico.split(",")));
    }
}
