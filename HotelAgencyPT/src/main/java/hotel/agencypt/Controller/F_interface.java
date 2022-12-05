package hotel.agencypt.Controller;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class F_interface {
    @FXML
    private Button Registerbutton;
    @FXML
    private Button LoginButton;
    @FXML
    private Button RoomServices;
    @FXML
    private Button Stock;
    @FXML
    private Button CheckIn;

    /**
     * Abre a janela para fazer o registo de um novo cliente.
     *
     * @param event Ação do evento
     * @throws Exception Verificação das exceções
     */
    public void ClientRegister(ActionEvent event) throws Exception {
        Stage stage = (Stage) Registerbutton.getScene().getWindow();
        stage.close();
        Singleton.open("Register", "Hotel >> Register");
    }

    /**
     * Abre a janela para fazer o login.
     *
     * @param event Ação do evento
     * @throws Exception Verificação das exceções
     */
    public void switchToLogin(ActionEvent event) throws Exception {
        Stage stage = (Stage) LoginButton.getScene().getWindow();
        stage.close();
        Singleton.open("Login", "Hotel >> Login");
    }

    /**
     * Abre a janela para criar uma reserva.
     *
     * @param event Ação do evento
     */
    public void abrirCriarReserva(ActionEvent event) {
        try {
            Stage window = (Stage) RoomServices.getScene().getWindow();
            window.close();
            Singleton.open("F_Reserva", "Hotel >> Funcionario >> Criar reserva");
        } catch (Exception e) {
            System.out.println("Erro ao fechar/abrir o stage.");
        }
    }

    /**
     * Abre a janela para fazer a configuração do quarto.
     *
     * @param event Ação do evento
     */
    public void abrirConfigQuarto(ActionEvent event) {
        try {
            Stage window = (Stage) RoomServices.getScene().getWindow();
            window.close();
            Singleton.open("GH_ConfigQuarto", "Hotel >> Funcionario >> Configurar quarto");
        } catch (Exception e) {
            System.out.println("Erro ao fechar/abrir o stage.");
        }
    }

    /**
     * Abre a janela para gerir o stock do hotel.
     *
     * @param event Ação do evento
     */
    public void abrirGerirStock(ActionEvent event) {
        try {
            Stage window = (Stage) Stock.getScene().getWindow();
            window.close();
            Singleton.open("GH_GerirStock", "Hotel >> Funcionario >> Gerir Stock");
        } catch (Exception e) {
            System.out.println("Erro ao fechar/abrir o stage.");
        }
    }

    /**
     * Abre a janela para fazer o Check-in.
     *
     * @param event Ação do evento
     * @throws Exception Verificação das exceções
     */
    public void FazerCheckIn(ActionEvent event) throws Exception {
        Stage window = (Stage) CheckIn.getScene().getWindow();
        window.close();
        Singleton.open("Check_In", "Hotel >> Funcionario >> Check In");
    }
}
