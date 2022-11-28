package hotel.agencypt.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * Classe pública do controlador GestorHotel.fxml
 */
public class GestorHotel {
    @FXML
    protected Button button;

    /**
     * Fecha o stage atual e abre o stage próprio do botão
     */
    @FXML
    protected void abrirRegistrosCartao() {
        try {
            Stage window = (Stage) button.getScene().getWindow();
            window.close();
            Singleton.open("GH_RegistrosCartao", "Hotel >> Gestor de Hotel >> Ver registros de um cartão");
        } catch (Exception e) {
            System.out.println("Erro ao fechar/abrir o stage.");
        }
    }

    /**
     * Fecha o stage atual e abre o stage próprio do botão
     */
    @FXML
    protected void abrirConfigQuarto() {
        try {
            Stage window = (Stage) button.getScene().getWindow();
            window.close();
            Singleton.open("GH_ConfigQuarto", "Hotel >> Gestor de Hotel >> Configurar quarto");
        } catch (Exception e) {
            System.out.println("Erro ao fechar/abrir o stage.");
        }
    }

    /**
     * Fecha o stage atual e abre o stage próprio do botão
     */
    @FXML
    protected void abrirGerirFeedback() {
        try {
            Stage window = (Stage) button.getScene().getWindow();
            window.close();
            Singleton.open("GH_GerirFeedback", "Hotel >> Gestor de Hotel >> Gerir feedback");
        } catch (Exception e) {
            System.out.println("Erro ao fechar/abrir o stage.");
        }
    }

    /**
     * Fecha o stage atual e abre o stage próprio do botão
     */
    @FXML
    protected void abrirGerirStock() {
        try {
            Stage window = (Stage) button.getScene().getWindow();
            window.close();
            Singleton.open("GH_GerirStock", "Hotel >> Gestor de Hotel >> Gerir Stock");
        } catch (Exception e) {
            System.out.println("Erro ao fechar/abrir o stage.");
        }
    }

    /**
     * Fecha o stage atual e abre o stage próprio do botão
     */
    @FXML
    protected void abrirGerirFuncionario() {
        try {
            Stage window = (Stage) button.getScene().getWindow();
            window.close();
            Singleton.open("GH_GerirFuncionario", "Hotel >> Gestor de Hotel >> Gerir Funcionários");
        } catch (Exception e) {
            System.out.println("Erro ao fechar/abrir o stage.");
        }
    }


    @FXML
    protected void abrirCriaReserva() {
        try {
            Stage window = (Stage) button.getScene().getWindow();
            window.close();
            Singleton.open("F_Reserva", "Hotel >> Gestor de Hotel >> Ver registros de um cartão");
        } catch (Exception e) {
            System.out.println("Erro ao fechar/abrir o stage.");
        }
    }

    @FXML
    public void voltarAtras(ActionEvent actionEvent) {
        try {
            Singleton.open("Login", "Hotel Agency PT");
        } catch (Exception e) {
            System.out.println("Erro ao voltar atrás.");
        }
    }
}
