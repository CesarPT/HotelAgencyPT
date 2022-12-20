package hotel.agencypt.Controller;

import javafx.event.ActionEvent;

public class F_ApagarReserva {


    public void voltarAtras(ActionEvent actionEvent) {
        try {
            if (Controller.getInstance().getTipo_user() == 'G'){
                Singleton.open("GestorHotel", "User: " + Controller.getInstance().getUsername() +
                        " | Hotel >> Gestor de Hotel");
            } else {
                Singleton.open("funcionariointerface", "User: " + Controller.getInstance().getUsername() +
                        " | Hotel >> Funcionário");
            }
        } catch (Exception e) {
            System.out.println("Erro ao voltar atrás.");
        }
    }
}
