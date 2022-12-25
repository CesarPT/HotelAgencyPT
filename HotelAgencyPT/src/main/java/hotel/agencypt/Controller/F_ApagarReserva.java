package hotel.agencypt.Controller;

import Classes.DAO.EntregaDAO;
import Classes.DAO.ReservaDAO;
import Classes.Entrega;
import Classes.Reserva;
import Classes.Stock;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import javax.swing.event.ChangeListener;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class F_ApagarReserva implements Initializable {

    @FXML
    private TableView<Reserva> tableApagarReserva;
    @FXML
    private TableColumn<Reserva, Integer> columnIDCliente;
    @FXML
    private TableColumn<Reserva, Integer> columnIDReserva;
    @FXML
    private TableColumn<Reserva, String> columnCheckIn;
    List<Reserva> arrayReserva = new ArrayList<>();
    ReservaDAO rDAO = new ReservaDAO();
        ObservableList<Reserva> obsCheckIn = FXCollections.observableArrayList();
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        arrayReserva = rDAO.temCheckIn();
        for (Reserva r : arrayReserva) {
            obsCheckIn.add(new Reserva(r.getIdcliente(), r.getIdreserva(), r.getCheckIn()));
        }

        //Colocar os valores nas colunas da TableView
        columnIDCliente.setCellValueFactory(new PropertyValueFactory<>("idcliente"));
        columnIDReserva.setCellValueFactory(new PropertyValueFactory<>("idreserva"));
        columnCheckIn.setCellValueFactory(new PropertyValueFactory<>("CheckIn"));
        tableApagarReserva.setItems(obsCheckIn);
    }

    public void clicarRemoverReserva(){
        // Verifica se selecionou e se é Sim ou Nao tem checkin
        if (tableApagarReserva.getSelectionModel().getSelectedItem() != null) {
            Reserva selectedPerson = tableApagarReserva.getSelectionModel().getSelectedItem();

            if(selectedPerson.getCheckIn().equals("Sim")){
                Alert alert2 = new Alert(Alert.AlertType.WARNING);
                alert2.setTitle("Aviso");
                alert2.setHeaderText("Cliente com Check-in");
                alert2.setContentText("O cliente que selecionou tem Check-In realizado, por isso " +
                        "não pode apagar a sua reserva.");
                alert2.showAndWait();
            } else {
                Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                alert2.setTitle("Informação");
                alert2.setContentText("Reserva do cliente apagada com sucesso.");
                alert2.showAndWait();
            }
            //Sem seleção
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Aviso");
            alert.setHeaderText("Sem seleção");
            alert.setContentText("Selecione primeiro um item da tabela.");
            alert.showAndWait();
        }
    }
    public void voltarAtras(ActionEvent actionEvent) {
        try {
            if (Controller.getInstance().getTipo_user() == 'G') {
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
