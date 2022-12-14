package hotel.agencypt.Controller;


import Classes.RegEntrada;
import DataBase.ConnectionDB;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;


/**
 * Classe pública do controlador GH_RegistrosCartao.fxml
 */
public class GH_RegistrosCartao implements Initializable {
    private Connection con = ConnectionDB.establishConnection();
    @FXML
    protected Button button;
    @FXML
    private TableView<RegEntrada> TableViewRegistros;
    @FXML
    private TableColumn<RegEntrada, Integer> NumColumn;
    @FXML
    private TableColumn<RegEntrada, Integer> NumClienteColumn;
    @FXML
    private TableColumn<RegEntrada, Integer> NumReservaColumn;
    @FXML
    private TableColumn<RegEntrada, Integer> NumQuartoColumn;
    @FXML
    private TableColumn<RegEntrada, String> LocalColumn;
    @FXML
    private TableColumn<RegEntrada, String> DataColumn;
    @FXML
    private TableColumn<RegEntrada, String> HoraColumn;
    @FXML
    private TextField textNum;
    ObservableList<RegEntrada> obsRegEntrada = FXCollections.observableArrayList();

    /**
     * Insere valores nas TableViews e executa a barra de pesquisa
     *
     * @param location  The location used to resolve relative paths for the root object, or
     *                  {@code null} if the location is not known.
     * @param resources The resources used to localize the root object, or {@code null} if
     *                  the root object was not localized.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        String sql = "SELECT RegEntrada.numcartao, Reserva.idcliente, Reserva.idreserva, Reserva.idquarto, RegEntrada.local, RegEntrada.data, RegEntrada.hora\n" +
                "                                FROM RegEntrada\n" +
                "                                INNER JOIN Cartao\n" +
                "                                ON Cartao.numcartao = RegEntrada.numcartao\n" +
                "                                INNER JOIN Reserva\n" +
                "                                ON Reserva.numcartao = Cartao.numcartao\n" +
                "                                INNER JOIN Cliente\n" +
                "                                ON Cliente.idcliente = Reserva.idcliente\n" +
                "                                INNER JOIN Utilizador\n" +
                "                                ON Utilizador.iduser = Cliente.iduser";

        PreparedStatement stmt = null;
        ResultSet rs = null;

        //Limpar tudo e Adicionar todos os cartões
        try {
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Integer numcartao = rs.getInt("numcartao");
                Integer idcliente = rs.getInt("idcliente");
                Integer idreserva = rs.getInt("idreserva");
                Integer idquarto = rs.getInt("idquarto");
                String local = rs.getString("local");
                String data = rs.getString("data");
                String hora = rs.getString("hora");
                obsRegEntrada.add(new RegEntrada(numcartao, idcliente, idreserva, idquarto, local, data, hora));
            }
            //Colocar os valores nas colunas da TableView
            NumColumn.setCellValueFactory(new PropertyValueFactory<>("numcartao"));
            NumClienteColumn.setCellValueFactory(new PropertyValueFactory<>("idcliente"));
            NumReservaColumn.setCellValueFactory(new PropertyValueFactory<>("idreserva"));
            NumQuartoColumn.setCellValueFactory(new PropertyValueFactory<>("idquarto"));
            LocalColumn.setCellValueFactory(new PropertyValueFactory<>("local"));
            DataColumn.setCellValueFactory(new PropertyValueFactory<>("data"));
            HoraColumn.setCellValueFactory(new PropertyValueFactory<>("hora"));
            TableViewRegistros.setItems(obsRegEntrada);

            //Barra de pesquisa
            FilteredList<RegEntrada> filteredData = new FilteredList<>(obsRegEntrada, b -> true);
            textNum.textProperty().addListener((observable, oldValue, newValue) -> {
                filteredData.setPredicate(RegEntrada -> {
                    //Se não pesquisar nada
                    if (newValue.isEmpty() || newValue.isBlank() || newValue == null) {
                        return true;
                    }
                    String searchKeyboard = newValue.toLowerCase();

                    //Encontrou uma palavra igual ao que escreveu
                    if (RegEntrada.getLocal().toLowerCase().contains(searchKeyboard)) {
                        return true;
                    } else if (RegEntrada.getData().toLowerCase().contains(searchKeyboard)) {
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
