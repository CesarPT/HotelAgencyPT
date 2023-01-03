package hotel.agencypt.Controller;

import Classes.DAO.QuartoStockDAO;
import Classes.DAO.StockDAO;
import Classes.QuartoStock;
import Classes.Stock;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe pública do controlador F_DecrementarStock.fxml
 */
public class F_DecrementarStock {
    @FXML
    public TextArea textIDQuarto;
    @FXML
    public ComboBox<String> comboQuantidade;
    @FXML
    public ListView<String> listConsumivel;

    @FXML
    public ListView<String> listQuarto;
    @FXML
    public ListView<String> listStock;
    Integer index = -1;
    Integer index2 = -1;

    ObservableList<String> obsProdutos = FXCollections.observableArrayList();
    ObservableList<String> obsStock = FXCollections.observableArrayList();
    ObservableList<String> obsQuarto = FXCollections.observableArrayList();
    List<Stock> arrayQtd = new ArrayList<>();
    List<QuartoStock> arrayQuarto = new ArrayList<>();
    @FXML
    ObservableList<String> listQtd = FXCollections.observableArrayList("1", "2", "3", "4", "5");
    StockDAO sDAO = new StockDAO();
    QuartoStockDAO qDAO = new QuartoStockDAO();
    List<Stock> arrayStock = new ArrayList<>();

    public void initialize() {
        //Setar todos os valores
        textIDQuarto.setText(String.valueOf(Controller.getInstance().getIdQuarto()));

        comboQuantidade.setItems(listQtd);

        arrayStock = sDAO.findStockConsumivel();
        for (Stock s : arrayStock) {
            obsProdutos.add(s.getProduct_description());
        }
        listConsumivel.setItems(obsProdutos);

        //Descrição e Quantidade do Stock
        arrayQtd = sDAO.findStockConsumivel();
        for (Stock s : arrayQtd) {
            obsStock.add(s.getProduct_description() + " | Quantidade: " + s.getQuantidade());
        }
        listStock.setItems(obsStock);

        //Produtos no quarto
        arrayQuarto = qDAO.findIDQuartoStock();
        for (QuartoStock q : arrayQuarto) {
            obsQuarto.add(q.getProduct_description() + " | Quantidade: " + q.getQuantidade());
        }
        listQuarto.setItems(obsQuarto);
    }

    /**
     * Adicionar produto escolhido ao quarto com a quantidade
     */
    public void adicionarConsumivel() {
        index = listConsumivel.getSelectionModel().getSelectedIndex();
        index2 = comboQuantidade.getSelectionModel().getSelectedIndex();
        //Se não selecionou um item da listview
        if (index == -1) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Aviso");
            alert.setHeaderText("Sem seleção");
            alert.setContentText("Selecione primeiro um consumível da lista.");
            alert.showAndWait();
            //Se não selecionou a combobox
        } else if (index2 == -1) {
            Alert alert2 = new Alert(Alert.AlertType.WARNING);
            alert2.setTitle("Aviso");
            alert2.setHeaderText("Sem seleção");
            alert2.setContentText("Selecione primeiro uma quantidade.");
            alert2.showAndWait();
        } else {
            //Decrementar tabela Stock
            sDAO.updateStockQtd(Integer.parseInt(comboQuantidade.getSelectionModel().getSelectedItem()), listConsumivel.getSelectionModel().getSelectedItem());
            //Atualizar produtos no quarto
            Controller.getInstance().setIdquarto(Integer.parseInt(textIDQuarto.getText()));
            QuartoStock quarto = new QuartoStock();
            quarto.setQuantidade(Float.parseFloat(comboQuantidade.getSelectionModel().getSelectedItem()));
            Controller.getInstance().setProdutosEscolhidos(listConsumivel.getSelectionModel().getSelectedItem());
            qDAO.updateQtd(quarto);

            Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
            alert2.setTitle("Informação");
            alert2.setContentText("Stock e produtos do quarto atualizados com sucesso.");
            alert2.showAndWait();
            //Atualizar listviews
            limparTudo();
        }
    }

    /**
     * Limpar todas as listviews
     */
    public void limparTudo() {
        listConsumivel.getItems().clear();
        listConsumivel.getSelectionModel().clearSelection();
        listStock.getItems().clear();
        listStock.getSelectionModel().clearSelection();
        listQuarto.getItems().clear();
        listQuarto.getSelectionModel().clearSelection();
        obsQuarto.clear();
        obsStock.clear();
        obsProdutos.clear();
        initialize();
    }

    /**
     * Volta atrás para a View GestorHotel.fxml
     *
     * @param actionEvent
     */
    @FXML
    public void voltarAtras(ActionEvent actionEvent) {
        try {
            Singleton.open("F_Reserva", "User: " + Controller.getInstance().getUsername() + " | Hotel >> " + Controller.getInstance().getTipo_user());
        } catch (Exception e) {
            System.out.println("Erro ao voltar atrás.");
        }
    }

}
