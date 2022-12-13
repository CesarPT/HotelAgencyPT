package hotel.agencypt.Controller;

import Classes.DAO.QuartoStockDAO;
import Classes.Quarto;
import Classes.QuartoStock;
import Classes.RegEntrada;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import javafx.util.Callback;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class StockQuartos implements Initializable {
    QuartoStockDAO qsDAO = new QuartoStockDAO();
    List<QuartoStock> arrayQuartoStock = new ArrayList<>();
    ObservableList<QuartoStock> obsQuartoStock = FXCollections.observableArrayList();

    @FXML
    private TableView<QuartoStock> tableStockQuarto;
    @FXML
    private TableColumn<QuartoStock, Integer> columnQuarto;
    @FXML
    private TableColumn<QuartoStock, String> columnDescricao;
    @FXML
    private TableColumn<QuartoStock, Float> columnQuantidade;

    @FXML
    private TableColumn<QuartoStock, String> columnEstado;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
                arrayQuartoStock = qsDAO.findQuartoStock();
                for (QuartoStock qs : arrayQuartoStock) {
                    Integer idquarto = qs.getIdquarto();
                    String pd = qs.getProduct_description();
                    Float qtd = qs.getQuantidade();
                    String estado;
                    if (qtd <= 3){
                        qs.setEstado("Critico!");
                    } else if (qtd <= 9) {
                        qs.setEstado("Pouco stock");
                    } else {
                        qs.setEstado("Stock suficiente");
                    }
                    estado = qs.getEstado();
                    obsQuartoStock.add(new QuartoStock(idquarto, pd, qtd, estado));
            }
        columnQuarto.setCellValueFactory(new PropertyValueFactory<>("idquarto"));
        columnDescricao.setCellValueFactory(new PropertyValueFactory<>("product_description"));
        columnQuantidade.setCellValueFactory(new PropertyValueFactory<>("quantidade"));
        columnEstado.setCellValueFactory(new PropertyValueFactory<>("estado"));
        tableStockQuarto.setItems(obsQuartoStock);
    }


    /**
     * Volta atrás
     * @param actionEvent
     */
    @FXML
    public void voltarAtras(ActionEvent actionEvent) {
        try {
            if (Controller.getInstance().getTipo_user() == 'G'){
                Singleton.open("GestorHotel", "Hotel >> Gestor de Hotel");
            } else {
                Singleton.open("funcionariointerface", "Hotel >> Funcionário");
            }
        } catch (Exception e) {
            System.out.println("Erro ao voltar atrás.");
        }
    }

}
