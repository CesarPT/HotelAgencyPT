package hotel.agencypt.Controller;

import Classes.CheckIn;
import DataBase.ConnectionDB;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


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


    ObservableList<CheckIn> CheckInObservableList = FXCollections.observableArrayList();


    Connection con = ConnectionDB.establishConnection();

    public void Pesquisar(ActionEvent event) {
        if (!userTextField.getText().isBlank()) {
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Aviso");
            alert.setHeaderText("Preencha o campo 'Username'.");
            alert.showAndWait();
        }
    }

    public void table() {
        String query = "Select idreserva, idquarto, datai, dataf from Reserva";
        try {
            PreparedStatement stm = con.prepareStatement(query);
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                Integer queryReservID = rs.getInt("idreserva");
                Integer queryRoomID = rs.getInt("idquarto");
                String queryDateI = rs.getString("datai");
                String queryDateF = rs.getString("dataf");
                CheckInObservableList.add(new CheckIn(queryReservID, queryRoomID, queryDateI, queryDateF));
            }


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

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void BackToStaff(ActionEvent event) throws Exception {
        Stage stage = (Stage) BackButton.getScene().getWindow();
        stage.close();
        Singleton.open("funcionariointerface", "Hotel >> Funcionário");
    }

    public void Clean(ActionEvent event) throws Exception {
        Singleton.open("Check_In", "Hotel >> Funcionario >> Check In");
    }


}
