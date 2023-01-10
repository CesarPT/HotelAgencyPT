package hotel.agencypt.Controller;

import Classes.CheckIn;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import static Classes.DAO.CheckInDAO.*;

/**
 *
 *  Classe pública do controlador Check_In.fxml
 *
 */
public class CheckInController {
    @FXML
    private Button BackButton;
    @FXML
    private TableView<CheckIn> ReservaTableView;
    @FXML
    private TableColumn<CheckIn, Integer> ReservaTableColumn;
    @FXML
    private TableColumn<CheckIn, Integer> QuartoTableColumn;
    @FXML
    private TableColumn<CheckIn, String> DataITableColumn;
    @FXML
    private TableColumn<CheckIn, String> DataFTableColumn;
    @FXML
    private TextField SearchBar;
    @FXML
    private TextField userTextField;
    @FXML
    private TextField RoomN;
    @FXML
    private TextField StartD;
    @FXML
    private TextField FinalD;
    @FXML
    private TextField Floor;
    @FXML
    private TextField RoomType;

    public static ObservableList<CheckIn> CheckInObservableList = FXCollections.observableArrayList();


    /**
     * Validações da pesquisa das reservas do cliente
     * @param event Recebe o clique no botão 'Selecionar User'
     */
    public void Pesquisar(ActionEvent event) {
        String username = userTextField.getText();
        boolean verify = VerifyExists(username);
        if (!username.isBlank()) {
            if (verify) {
                table();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Aviso");
            alert.setHeaderText("Preencha o campo 'Username'.");
            alert.showAndWait();
        }
    }

    /**
     * Cria o check-in e insere os parametros dessa reserva nos textfields
     * @param event Recebe o clique no botão 'Check-In'
     */
    public void CheckIn(ActionEvent event) {
        try {
            String room = String.valueOf(ReservaTableView.getSelectionModel().getSelectedItem().getIdquarto());
            RoomN.setText(room);
            String iDate = ReservaTableView.getSelectionModel().getSelectedItem().getDatai();
            StartD.setText(iDate);
            String fDate = ReservaTableView.getSelectionModel().getSelectedItem().getDataf();
            FinalD.setText(fDate);
            Floor.setText(String.valueOf(check.getFloor()));
            RoomType.setText(check.getType());
            Integer idReserv = ReservaTableView.getSelectionModel().getSelectedItem().getIdreserva();
            VerifyCheckInExists(idReserv);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Cria o Chekout dessa reserva do cliente
     * @param event Recebe o clique no botão 'Check-Out'
     */
    public void CheckOut(ActionEvent event) {
        try {
            String room = String.valueOf(ReservaTableView.getSelectionModel().getSelectedItem().getIdquarto());
            RoomN.setText(room);
            String iDate = ReservaTableView.getSelectionModel().getSelectedItem().getDatai();
            StartD.setText(iDate);
            String fDate = ReservaTableView.getSelectionModel().getSelectedItem().getDataf();
            FinalD.setText(fDate);
            Floor.setText(String.valueOf(check.getFloor()));
            RoomType.setText(check.getType());
            Integer idReserv = ReservaTableView.getSelectionModel().getSelectedItem().getIdreserva();
            VerifyCheckOutExists(idReserv);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Devolve as reservas desse mesmo cliente
     */
    public void table() {
        ReservaTableColumn.setCellValueFactory(new PropertyValueFactory<>("idreserva"));
        QuartoTableColumn.setCellValueFactory(new PropertyValueFactory<>("idquarto"));
        DataITableColumn.setCellValueFactory(new PropertyValueFactory<>("datai"));
        DataFTableColumn.setCellValueFactory(new PropertyValueFactory<>("dataf"));

        ReservaTableView.setItems(CheckInObservableList);

        FilteredList<CheckIn> filteredData = new FilteredList<>(CheckInObservableList, b -> true);

        SearchBar.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(CheckIn -> {
                if (newValue.isEmpty() || newValue.isBlank() || newValue == null) {
                    return true;
                }
                String searchWord = newValue.toLowerCase();


                if (CheckIn.getIdreserva().toString().indexOf(searchWord) > -1) {
                    return true;
                } else if (CheckIn.getIdquarto().toString().indexOf(searchWord) > -1) {
                    return true;

                } else if (CheckIn.getDatai().toLowerCase().indexOf(searchWord) > -1) {
                    return true;
                } else return CheckIn.getDataf().toLowerCase().indexOf(searchWord) > -1;
            });
        });

        SortedList<CheckIn> sortedData = new SortedList<>(filteredData);

        sortedData.comparatorProperty().bind(ReservaTableView.comparatorProperty());

        ReservaTableView.setItems(sortedData);
    }

    /**
     * Botão para voltar para a página principal do funcionário
     * @param event Recebe o clique no botão para voltar para trás
     */
    public void BackToStaff(ActionEvent event) {
        try {
        Stage stage = (Stage) BackButton.getScene().getWindow();
        stage.close();
        Singleton.open("funcionariointerface", "Hotel >> Funcionário");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Botão para limpar toda a interface
     * @param event Recebe o clique no botão 'Limpar'
     */
    public void Clean(ActionEvent event) {
        try {
            CheckInObservableList.clear();
            RoomN.clear();
            StartD.clear();
            FinalD.clear();
            Floor.clear();
            RoomType.clear();
        } catch (Exception ex) {
            ex.printStackTrace();

        }
    }
}

