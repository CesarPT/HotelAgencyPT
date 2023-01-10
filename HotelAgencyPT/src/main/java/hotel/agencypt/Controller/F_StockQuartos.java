package hotel.agencypt.Controller;

import Classes.DAO.QuartoStockDAO;
import Classes.QuartoStock;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Classe pública do Controlador F_StockQuartos.fxml
 */
public class F_StockQuartos implements Initializable {
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

    /**
     * Método sem retorno, ao iniciar a scene ele é iniciado
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
        arrayQuartoStock = qsDAO.findQuartoStock();
        for (QuartoStock qs : arrayQuartoStock) {
            Integer idquarto = qs.getIdquarto();
            String pd = qs.getProduct_description();
            Float qtd = qs.getQuantidade();
            String estado;
            if (qtd <= 3) {
                qs.setEstado("Critico!");
            } else if (qtd <= 9) {
                qs.setEstado("Pouco stock");
            } else {
                qs.setEstado("Stock suficiente");
            }
            estado = qs.getEstado();
            obsQuartoStock.add(new QuartoStock(idquarto, pd, qtd, Float.parseFloat(estado)));
        }
        columnQuarto.setCellValueFactory(new PropertyValueFactory<>("idquarto"));
        columnDescricao.setCellValueFactory(new PropertyValueFactory<>("product_description"));
        columnQuantidade.setCellValueFactory(new PropertyValueFactory<>("quantidade"));
        columnEstado.setCellValueFactory(new PropertyValueFactory<>("estado"));
        tableStockQuarto.setItems(obsQuartoStock);
    }


    /**
     * Voltar atrás consoante o tipo_user
     */
    @FXML
    public void voltarAtras(ActionEvent actionEvent) {
        try {
            if (Controller.getInstance().getTipo_user() == 'G') {
                Singleton.open("GestorHotel", "User: " + Controller.getInstance().getUsername() + " | Hotel >> Gestor de Hotel");
            } else {
                Singleton.open("funcionariointerface", "User: " + Controller.getInstance().getUsername() + " | Hotel >> Funcionário");
            }
        } catch (Exception e) {
            System.out.println("Erro ao voltar atrás.");
        }
    }

}
