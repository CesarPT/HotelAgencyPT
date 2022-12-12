package hotel.agencypt.Controller;

import Classes.Entrega;
import Classes.Stock;
import DataBase.ConnectionDB;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.ResourceBundle;

/**
 * Classe pública do controlador GH_GerirStock.fxml
 */
public class GH_GerirStock implements Initializable {
    private Connection con = ConnectionDB.establishConnection();
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
        String sql = "SELECT product_identifier, product_description, quantidade, tipo_qtd," +
                "     preco, vat, preco_total\n" +
                "FROM Stock";
        String sql2 = "SELECT orderNumber, data_entrega, party_identifier, empresa,\n" +
                "\t   rua, n_porta, cidade, cp, pais\n" +
                "FROM Entrega";


        PreparedStatement stmt = null;
        ResultSet rs = null;

        //Limpar tudo e Adicionar todas as entradas de stock
        try {
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                String pid = rs.getString("product_identifier");
                String pd = rs.getString("product_description");
                Integer qtd = rs.getInt("quantidade");
                String tipo_qtd = rs.getString("tipo_qtd");
                Float preco = rs.getFloat("preco");
                Float vat = rs.getFloat("vat");
                Float preco_total = rs.getFloat("preco_total");

                obsEntradas.add(new Stock(pid, pd, qtd, tipo_qtd, preco, vat, preco_total));
            }
        } catch (SQLException e) {
            System.out.println("[ERRO]: Inserir valores no observableList obsEntradas");
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

        try {
            stmt = con.prepareStatement(sql2);
            rs = stmt.executeQuery();

            while (rs.next()) {
                String oN = rs.getString("orderNumber");
                Date data = rs.getDate("data_entrega");
                String pi = rs.getString("party_identifier");
                String empresa = rs.getString("empresa");
                String rua = rs.getString("rua");
                String porta = rs.getString("n_porta");
                String cidade = rs.getString("cidade");
                String cp = rs.getString("cp");
                String pais = rs.getString("pais");

                obsEntregas.add(new Entrega(oN, data, pi, empresa, rua, porta, cidade, cp, pais));
            }
        } catch (SQLException e) {
            System.out.println("[ERRO]: Inserir valores no observableList obsEntregas");
        } finally {
            ConnectionDB.closeConnection(con, stmt, rs);
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
