package hotel.agencypt.Controller;


import Classes.DAO.RegEntradaDAO;
import Classes.DAO.ReservaDAO;
import Classes.RegEntrada;
import Classes.Reserva;
import DataBase.ConnectionDB;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


/**
 * Classe pública do controlador GH_RegistrosCartao.fxml
 */
public class GH_RegistrosCartao implements Initializable {
    private Connection con;
    @FXML
    protected Button button;
    @FXML
    private TableView<RegEntrada> TableViewRegistros;
    @FXML
    private TableColumn<RegEntrada, Integer> NumColumn;
    @FXML
    private TableColumn<RegEntrada, String> LocalColumn;
    @FXML
    private TableColumn<RegEntrada, String> DataColumn;
    @FXML
    private TextField textNum;
    ObservableList<RegEntrada> obsRegEntrada = FXCollections.observableArrayList();

    /**
     * Insere valores nas TableViews e executa a barra de pesquisa
     * @param location
     * The location used to resolve relative paths for the root object, or
     * {@code null} if the location is not known.
     *
     * @param resources
     * The resources used to localize the root object, or {@code null} if
     * the root object was not localized.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        String sql = "SELECT RegEntrada.numcartao, RegEntrada.data, RegEntrada.local\n" +
                "FROM RegEntrada\n" +
                "INNER JOIN Cartao\n" +
                "ON Cartao.numcartao = RegEntrada.numcartao\n" +
                "INNER JOIN Reserva\n" +
                "ON Reserva.numcartao = Cartao.numcartao\n" +
                "INNER JOIN Cliente\n" +
                "ON Cliente.idcliente = Reserva.idcliente\n" +
                "INNER JOIN Utilizador\n" +
                "ON Utilizador.iduser = Cliente.iduser";

        PreparedStatement stmt = null;
        ResultSet rs = null;

        //Limpar tudo e Adicionar todos os cartões
        try {
            con = ConnectionDB.establishConnection();
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Integer numcartao = rs.getInt("numcartao");
                String local = rs.getString("local");
                String data = rs.getString("data");
                obsRegEntrada.add(new RegEntrada(numcartao, local, data));
            }
            //Colocar os valores nas colunas da TableView
            NumColumn.setCellValueFactory(new PropertyValueFactory<>("numcartao"));
            LocalColumn.setCellValueFactory(new PropertyValueFactory<>("data"));
            DataColumn.setCellValueFactory(new PropertyValueFactory<>("local"));
            TableViewRegistros.setItems(obsRegEntrada);

            //Barra de pesquisa
            FilteredList<RegEntrada> filteredData = new FilteredList<>(obsRegEntrada, b -> true);
            textNum.textProperty().addListener((observable, oldValue, newValue) -> {
                filteredData.setPredicate(RegEntrada -> {
                    //Se não pesquisar nada
                    if(newValue.isEmpty() || newValue.isBlank() || newValue == null){
                        return true;
                    }
                    String searchKeyboard = newValue.toLowerCase();

                    //Encontrou uma palavra igual ao que escreveu
                    if (RegEntrada.getLocal().toLowerCase().contains(searchKeyboard)){
                        return true;
                    } else if (RegEntrada.getData().toLowerCase().contains(searchKeyboard)){
                        return true;
                    } else {
                        String num = Integer.toString(RegEntrada.getNumcartao());
                        return num.contains(searchKeyboard); //não encontrou
                    }
                });
            });
            SortedList<RegEntrada> sortedData = new SortedList<>(filteredData);
            sortedData.comparatorProperty().bind(TableViewRegistros.comparatorProperty());
            TableViewRegistros.setItems(sortedData);

        } catch (SQLException e) {
            System.err.println("[ERRO]: initialize " + e.getMessage());
        } finally {
            ConnectionDB.closeConnection(con, stmt, rs);
        }
    }

    /**
     * Volta atrás para a View GestorHotel.fxml
     *
     * @param actionEvent
     */
    @FXML
    public void voltarAtras(ActionEvent actionEvent) {
        try {
            Singleton.open("GestorHotel", "Hotel >> Gestor de Hotel");
        } catch (Exception e) {
            System.out.println("Erro ao voltar atrás.");
        }
    }

}
