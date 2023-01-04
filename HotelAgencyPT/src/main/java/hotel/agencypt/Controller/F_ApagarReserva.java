package hotel.agencypt.Controller;

import Classes.DAO.ReservaDAO;
import Classes.Reserva;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe pública do Controlador F_ApagarReserva.fxml
 */
public class F_ApagarReserva {

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

    /**
     * Método sem retorno, ao iniciar a scene ele é iniciado
     */
    public void initialize() {
        tableApagarReserva.getSelectionModel().clearSelection();
        tableApagarReserva.getItems().clear();

        arrayReserva = ReservaDAO.temCheckIn();
        for (Reserva r : arrayReserva) {
            obsCheckIn.add(new Reserva(r.getIdcliente(), r.getIdreserva(), r.getCheckIn()));
        }

        //Colocar os valores nas colunas da TableView
        columnIDCliente.setCellValueFactory(new PropertyValueFactory<>("idcliente"));
        columnIDReserva.setCellValueFactory(new PropertyValueFactory<>("idreserva"));
        columnCheckIn.setCellValueFactory(new PropertyValueFactory<>("CheckIn"));
        tableApagarReserva.setItems(obsCheckIn);
    }

    /**
     * Clicar para apagar reserva e atualizar listviews
     */
    public void clicarRemoverReserva() {
        // Verifica se selecionou e se é Sim ou Nao tem checkin
        if (tableApagarReserva.getSelectionModel().getSelectedItem() != null) {
            Reserva selectedPerson = tableApagarReserva.getSelectionModel().getSelectedItem();

            if (selectedPerson.getCheckIn().equals("Sim")) {
                Alert alert2 = new Alert(Alert.AlertType.WARNING);
                alert2.setTitle("Aviso");
                alert2.setHeaderText("Cliente com Check-in");
                alert2.setContentText("O cliente que selecionou tem Check-In realizado, por isso " +
                        "não pode apagar a sua reserva.");
                alert2.showAndWait();
            } else {
                //Insere os valores selecionados da tableview nos sets
                Reserva reserva = tableApagarReserva.getSelectionModel().getSelectedItem();

                //Recebe os valores selecionados nos gets e envia para o controlador
                //usa-se o get do controller no ReservaDAO
                Controller.getInstance().setSelectedRowReserva(reserva.getIdreserva());
                //Remove na BD
                rDAO.deleteReserva();

                Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                alert2.setTitle("Informação");
                alert2.setContentText("Reserva do cliente apagada com sucesso.");
                alert2.showAndWait();

                //Atualiza a tableview
                initialize();
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

    /**
     * Voltar atrás consoante o tipo_user
     * @param actionEvent
     */
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
