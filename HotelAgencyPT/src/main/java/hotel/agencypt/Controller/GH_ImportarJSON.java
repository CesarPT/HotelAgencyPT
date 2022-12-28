package hotel.agencypt.Controller;

import Classes.DAO.StockDAO;
import Classes.Stock;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;

import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe pública do controlador GH_ImportarJSON.fxml
 */
public class GH_ImportarJSON {
    JSONpath instanceJSON = new JSONpath();
    @FXML
    private TableView<Stock> TableViewStock;
    @FXML
    private TableColumn<Stock, String> TableColumnIDP;
    @FXML
    private TableColumn<Stock, String> TableColumnDescricao;
    @FXML
    private TableColumn<Stock, Integer> TableColumnQtd;
    @FXML
    private TableColumn<Stock, String> TableColumnTipoQtd;
    @FXML
    private TableColumn<Stock, Float> TableColumnPreco;
    @FXML
    private TableColumn<Stock, Float> TableColumnVAT;
    @FXML
    private TableColumn<Stock, Float> TableColumnPrecoTotal;
    ObservableList<Stock> obsEntradas = FXCollections.observableArrayList();


    StockDAO sDAO = new StockDAO();
    List<Stock> arrayStock = new ArrayList<>();
    @FXML
    private ListView listCaminho;

    /**
     * Clicar no botão de Importar JSON
     * @param event
     */
    public void clicarBtnImportar(ActionEvent event) throws Exception {
        FileChooser fc = new FileChooser();
        fc.setInitialDirectory(new File("C:\\"));
        fc.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Ficheiros JSON", "*.json"));
        File selectedFile = fc.showOpenDialog(null);

        //Se não selecionar
        if (selectedFile != null) {
            listCaminho.getItems().add(selectedFile.getAbsolutePath());
            //Ler ficheiro JSON com o path que escolheu
            instanceJSON.Lerjson(Path.of(selectedFile.getAbsolutePath()));
            atualizarTableView();
        } else {
            System.out.println("Ficheiro inválido.");
        }
    }


    public void atualizarTableView() {
        //Limpar tableview
        TableViewStock.getItems().clear();
        //Mostrar na tableview
        String pid;
        String pd;
        int qtd;
        String tipo_qtd;
        float preco;
        float vat;
        float preco_total;

        arrayStock = sDAO.findStock();
        for (Stock s : arrayStock) {
            pid = s.getProduct_identifier();
            pd = s.getProduct_description();
            qtd = s.getQuantidade();
            tipo_qtd = s.getTipo_qtd();
            preco = s.getPreco();
            vat = s.getVat();
            preco_total = s.getPreco_total();
            obsEntradas.add(new Stock(pid, pd, qtd, tipo_qtd, preco, vat, preco_total));
        }

        //Colocar os valores nas colunas da TableView
        TableColumnIDP.setCellValueFactory(new PropertyValueFactory<>("product_identifier"));
        TableColumnDescricao.setCellValueFactory(new PropertyValueFactory<>("product_description"));
        TableColumnQtd.setCellValueFactory(new PropertyValueFactory<>("quantidade"));
        TableColumnTipoQtd.setCellValueFactory(new PropertyValueFactory<>("tipo_qtd"));
        TableColumnPreco.setCellValueFactory(new PropertyValueFactory<>("preco"));
        TableColumnVAT.setCellValueFactory(new PropertyValueFactory<>("vat"));
        TableColumnPrecoTotal.setCellValueFactory(new PropertyValueFactory<>("preco_total"));
        TableViewStock.setItems(obsEntradas);
    }


    public void confirmarJSON() throws Exception {
        //Enviar para a base de dados
        //instanceJSON.confirmarJSON();
        //Notificar
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Informação");
        alert.setHeaderText("Base de Dados");
        alert.setContentText("Base de dados atualizada com o ficheiro XML.");
        alert.showAndWait();
        //Atualizar tableview
        atualizarTableView();
    }

    /**
     * Volta atrás para a View funcionariointerface.fxml
     *
     * @param actionEvent
     */
    @FXML
    public void voltarAtras(ActionEvent actionEvent) {
        try {
            Singleton.open("GestorHotel", "User: " + Controller.getInstance().getUsername()
                    + " | Hotel >> Gestor de Hotel");
        } catch (Exception e) {
            System.out.println("Erro ao voltar atrás.");
        }
    }
}
