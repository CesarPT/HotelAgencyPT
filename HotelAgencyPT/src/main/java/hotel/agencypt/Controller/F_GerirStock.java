package hotel.agencypt.Controller;

import Classes.DAO.EntregaDAO;
import Classes.DAO.StockDAO;
import Classes.Entrega;
import Classes.Stock;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Classe pública do controlador F_GerirStock.fxml
 */
public class F_GerirStock implements Initializable {
    @FXML
    protected Button button;
    @FXML
    private TableView<Stock> TableViewStock;
    @FXML
    private TableColumn<Stock, String> TableColumnIDP;
    @FXML
    private TableColumn<Stock, String> TableColumDescricao;
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

    @FXML
    private TableView<Entrega> TableViewEntregas;
    @FXML
    private TableColumn<Entrega, String> ColumnNumOrdem;
    @FXML
    private TableColumn<Entrega, Date> ColumnData;
    @FXML
    private TableColumn<Entrega, String> ColumnId;
    @FXML
    private TableColumn<Entrega, String> ColumnEmpresa;
    @FXML
    private TableColumn<Entrega, String> ColumnRua;
    @FXML
    private TableColumn<Entrega, String> ColumnPorta;
    @FXML
    private TableColumn<Entrega, String> ColumnCidade;
    @FXML
    private TableColumn<Entrega, String> ColumnCP;
    @FXML
    private TableColumn<Entrega, String> ColumnPais;
    ObservableList<Entrega> obsEntregas = FXCollections.observableArrayList();
    EntregaDAO eDAO = new EntregaDAO();
    List<Entrega> arrayEntrega = new ArrayList<>();
    StockDAO sDAO = new StockDAO();
    List<Stock> arrayStock = new ArrayList<>();

    /**
     * Método sem retorno, ao iniciar a scene ele é iniciado
     * Insere valores nas TableViews e executa a barra de pesquisa
     *
     * @param location  The location used to resolve relative paths for the root object, or
     *                  {@code null} if the location is not known.
     * @param resources The resources used to localize the root object, or {@code null} if
     *                  the root object was not localized.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        String pid;
        String pd;
        float qtd;
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
        TableColumDescricao.setCellValueFactory(new PropertyValueFactory<>("product_description"));
        TableColumnQtd.setCellValueFactory(new PropertyValueFactory<>("quantidade"));
        TableColumnTipoQtd.setCellValueFactory(new PropertyValueFactory<>("tipo_qtd"));
        TableColumnPreco.setCellValueFactory(new PropertyValueFactory<>("preco"));
        TableColumnVAT.setCellValueFactory(new PropertyValueFactory<>("vat"));
        TableColumnPrecoTotal.setCellValueFactory(new PropertyValueFactory<>("preco_total"));
        TableViewStock.setItems(obsEntradas);

        //TableView da Entrega
        String oN;
        Date data;
        String pi;
        String empresa;
        String rua;
        String porta;
        String cidade;
        String cp;
        String pais;

        arrayEntrega = eDAO.findEntrega();
        for (Entrega e : arrayEntrega) {
            oN = e.getOrderNumber();
            data = e.getData_entrega();
            pi = e.getPais();
            empresa = e.getEmpresa();
            rua = e.getRua();
            porta = e.getN_porta();
            cidade = e.getCidade();
            cp = e.getCp();
            pais = e.getPais();
            obsEntregas.add(new Entrega(oN, data, pi, empresa, rua, porta, cidade, cp, pais));
        }

        //Colocar os valores nas colunas da TableView
        ColumnNumOrdem.setCellValueFactory(new PropertyValueFactory<>("orderNumber"));
        ColumnData.setCellValueFactory(new PropertyValueFactory<>("data_entrega"));
        ColumnId.setCellValueFactory(new PropertyValueFactory<>("party_identifier"));
        ColumnEmpresa.setCellValueFactory(new PropertyValueFactory<>("empresa"));
        ColumnRua.setCellValueFactory(new PropertyValueFactory<>("rua"));
        ColumnPorta.setCellValueFactory(new PropertyValueFactory<>("n_porta"));
        ColumnCidade.setCellValueFactory(new PropertyValueFactory<>("cidade"));
        ColumnCP.setCellValueFactory(new PropertyValueFactory<>("cp"));
        ColumnPais.setCellValueFactory(new PropertyValueFactory<>("pais"));
        TableViewEntregas.setItems(obsEntregas);


    }

    /**
     * Abrir a view StockQuartos.fxml
     */
    public void abrirStockQuartos() {
        try {
            Stage window = (Stage) TableViewStock.getScene().getWindow();
            window.close();
            Singleton.open("F_StockQuartos", "User: " + Controller.getInstance().getUsername() + " | Hotel >> Stock de Quartos");
        } catch (Exception e) {
            System.out.println("Erro ao abrir/fechar o scene");
        }
    }

    /**
     * Volta atrás para funcionariointerface.fxml
     */
    @FXML
    public void voltarAtras(ActionEvent actionEvent) {
        try {
            Singleton.open("funcionariointerface", "User: " + Controller.getInstance().getUsername()
                    + " Hotel >> Funcionário");
        } catch (Exception e) {
            System.out.println("Erro ao voltar atrás.");
        }
    }
}
