package hotel.agencypt.Controller;

import Classes.DAO.StockDAO;
import Classes.Stock;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
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

import java.io.*;
import java.lang.reflect.Array;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Classe pública do controlador GH_ImportarJSON.fxml
 */
public class GH_ImportarJSON {
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
     *
     * @param event
     */
    public void importarJSON(ActionEvent event) throws Exception {
        FileChooser fc = new FileChooser();
        fc.setInitialDirectory(new File("C:\\"));
        fc.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Ficheiros JSON", "*.json"));
        File selectedFile = fc.showOpenDialog(null);

        //Se não selecionar
        if (selectedFile != null) {
            listCaminho.getItems().add(selectedFile.getAbsolutePath());
            //Ler ficheiro JSON com o path que escolheu
            Lerjson(Path.of(selectedFile.getAbsolutePath()));
            atualizarTableView();
        } else {
            System.out.println("Ficheiro inválido.");
        }
    }


    public void atualizarTableView() {
        //Limpar tableview
        TableViewStock.getItems().clear();

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

    /**
     * Chama outro método para enviar os produtos para a BD corretamente
     * @throws Exception
     */
    public void confirmarJSON() throws Exception {
        //Enviar para a base de dados
        confirmarJSON2();
        //Notificar
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Informação");
        alert.setHeaderText("Base de Dados");
        alert.setContentText("Base de dados atualizada com o ficheiro XML.");
        alert.showAndWait();
        //Atualizar tableview
        atualizarTableView();
    }

    //IMPORTAR JSON

    String[] code, descricao;
    String[] tipoqtd;
    String[] qtd, preco, VAT, precototal;
    String lineNumber;
    JsonObject line;

    /**
     * Ler ficheiro JSON com GSON
     * @param path
     */
    public void Lerjson(Path path) {
        try {
            String header;
            StringBuilder sb = new StringBuilder();

            //Ler o ficheiro JSON em UTF-8
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(path.toFile()), StandardCharsets.UTF_8));
            //Ler o header
            while ((header = br.readLine()) != null) {
                sb.append(header);
            }
            System.out.println("JSON file: " + sb);
            //Parse do GSON para o JSON
            JsonObject jsonObject = new Gson().fromJson(sb.toString(), JsonObject.class);

            //FORNECEDOR

            //OrderNumber
            String ordernumber = jsonObject.getAsJsonObject("Order").getAsJsonObject("Header").get("OrderNumber").getAsString();
            //OrderDate
            String orderdate = jsonObject.getAsJsonObject("Order").getAsJsonObject("Header").get("OrderDate").getAsString();
            //SupplierParty
            String supplierparty = jsonObject.getAsJsonObject("Order").getAsJsonObject("Header").getAsJsonObject("SupplierParty").get("PartyIdentifier").getAsString();
            //NameAddress - Name
            String name = jsonObject.getAsJsonObject("Order").getAsJsonObject("Header").getAsJsonObject("SupplierParty").getAsJsonObject("NameAddress").get("Name").getAsString();
            //NameAddress - Address
            String address1 = jsonObject.getAsJsonObject("Order").getAsJsonObject("Header").getAsJsonObject("SupplierParty").getAsJsonObject("NameAddress").get("Address1").getAsString();
            //NameAddress - City
            String city = jsonObject.getAsJsonObject("Order").getAsJsonObject("Header").getAsJsonObject("SupplierParty").getAsJsonObject("NameAddress").get("City").getAsString();
            //NameAddress - PostalCode
            String postalcode = jsonObject.getAsJsonObject("Order").getAsJsonObject("Header").getAsJsonObject("SupplierParty").getAsJsonObject("NameAddress").get("PostalCode").getAsString();
            //NameAddress" - Country - ISOCountryCode
            String isocountrycode = jsonObject.getAsJsonObject("Order").getAsJsonObject("Header").getAsJsonObject("SupplierParty").getAsJsonObject("NameAddress").getAsJsonObject("Country").get("ISOCountryCode").getAsString();
            //NameAddress - Country - Value
            String country = jsonObject.getAsJsonObject("Order").getAsJsonObject("Header").getAsJsonObject("SupplierParty").getAsJsonObject("NameAddress").getAsJsonObject("Country").get("Value").getAsString();

            System.out.println("####################");
            System.out.println("Order Number: " + ordernumber);
            System.out.println("Order Date: " + orderdate);
            System.out.println("Supplier Party: " + supplierparty);
            System.out.println("######ADDRESS#######");
            System.out.println("Name: " + name);
            System.out.println("Address1: " + address1);
            System.out.println("City: " + city);
            System.out.println("Postal Code: " + postalcode);
            System.out.println("######COUNTRY#######");
            System.out.println("ISO Country Code: " + isocountrycode);
            System.out.println("Country: " + country);
            System.out.println("#####################");

            //LINES

            JsonArray linenumber = jsonObject.getAsJsonObject("Order").getAsJsonArray("Line");
            JsonArray productidentifier = jsonObject.getAsJsonObject("Order").getAsJsonArray("Line");

            code = new String[10];
            descricao = new String[10];
            tipoqtd = new String[10];
            qtd = new String[10];
            tipoqtd = new String[10];
            preco = new String[10];
            VAT = new String[10];
            precototal = new String[10];
            //Em cada line percorre tudo o que está dentro
            for (int i = 0; i < linenumber.size(); i++) {
                JsonArray pr = productidentifier.get(i).getAsJsonObject().getAsJsonObject("Product").getAsJsonArray("ProductIdentifier");
                lineNumber = linenumber.get(i).getAsJsonObject().get("LineNumber").getAsString(); //Getting the value of the key "Product"
                JsonObject pdescription = productidentifier.get(i).getAsJsonObject().getAsJsonObject("Product");

                //Em cade Product Identifier percorre tudo o que está dentro
                for (int j = 0; j < pr.size(); j++) {
                    line = linenumber.get(i).getAsJsonObject();
                    JsonArray line1 = line.get("InformationalQuantity").getAsJsonArray();
                    JsonObject pidentifier = pr.get(j).getAsJsonObject();
                    code[i] = Arrays.toString(new String[]{pidentifier.get("Code").getAsString()});
                    descricao[i] = Arrays.toString(new String[]{pdescription.get("ProductDescription").getAsString()});
                    qtd[i] = Arrays.toString(new String[]{line.get("Quantity").getAsJsonObject().get("Value").getAsJsonObject().get("Value").getAsString()});
                    tipoqtd[i] = Arrays.toString(new String[]{line1.get(j).getAsJsonObject().get("Value").getAsJsonObject().get("Value").getAsString()});
                    preco[i] = Arrays.toString(new String[]{line.get("MonetaryAdjustment").getAsJsonObject().get("MonetaryAdjustmentStartAmount").getAsJsonObject().get("CurrencyValue").getAsJsonObject().get("Value").getAsString()});
                    VAT[i] = Arrays.toString(new String[]{line.get("MonetaryAdjustment").getAsJsonObject().get("TaxAdjustment").getAsJsonObject().get("TaxPercent").getAsString()});
                    precototal[i] = Arrays.toString(new String[]{line.get("LineBaseAmount").getAsJsonObject().get("CurrencyValue").getAsJsonObject().get("Value").getAsString()});

                    System.out.println("##### LINE " + lineNumber + " #####");
                    System.out.println("Product Identifier: " + Arrays.toString(code));
                    System.out.println("Product Description: " + Arrays.toString(descricao));
                    System.out.println("Qtd: " + Arrays.toString(qtd));
                    System.out.println("Kg (tipo_qtd): " + Arrays.toString(tipoqtd));
                    System.out.println("Preço: " + Arrays.toString(preco));
                    System.out.println("VAT: " + Arrays.toString(VAT));
                    System.out.println("Preço total: " + Arrays.toString(precototal));
                    System.out.println("##############################");

                    code[i] = code[i].replace("[", "").replace("]", "");
                    descricao[i] = descricao[i].replace("[", "").replace("]", "");
                    qtd[i] = qtd[i].replace("[", "").replace("]", "");
                    tipoqtd[i] = tipoqtd[i].replace("[", "").replace("]", "");
                    preco[i] = preco[i].replace("[", "").replace("]", "");
                    VAT[i] = VAT[i].replace("[", "").replace("]", "");
                    precototal[i] = precototal[i].replace("[", "").replace("]", "");

                    //Remover caracteres especiais (' ã é â...) ASCII que faz SQL Injection
                    code[i] = Normalizer
                                    .normalize(code[i], Normalizer.Form.NFD)
                                    .replaceAll("[^\\p{ASCII}]", "");
                    descricao[i] = Normalizer
                            .normalize(descricao[i], Normalizer.Form.NFD)
                            .replaceAll("[^\\p{ASCII}]", "");
                    qtd[i] = Normalizer
                            .normalize(qtd[i], Normalizer.Form.NFD)
                            .replaceAll("[^\\p{ASCII}]", "");
                    tipoqtd[i] = Normalizer
                            .normalize(tipoqtd[i], Normalizer.Form.NFD)
                            .replaceAll("[^\\p{ASCII}]", "");
                    tipoqtd[i] = Normalizer
                            .normalize(tipoqtd[i], Normalizer.Form.NFD)
                            .replaceAll("[^\\p{ASCII}]", "");
                    preco[i] = Normalizer
                            .normalize(preco[i], Normalizer.Form.NFD)
                            .replaceAll("[^\\p{ASCII}]", "");
                    VAT[i] = Normalizer
                            .normalize(VAT[i], Normalizer.Form.NFD)
                            .replaceAll("[^\\p{ASCII}]", "");
                    precototal[i] = Normalizer
                            .normalize(precototal[i], Normalizer.Form.NFD)
                            .replaceAll("[^\\p{ASCII}]", "");
                    obsEntradas.add(new Stock(code[i], descricao[i], qtd[i], tipoqtd[i], preco[i], VAT[i], precototal[i]));
                }
            }
            } catch(IOException e){
                System.out.println("[Erro] LerJSON - Buffers | " + e.getMessage());
            }
    }

    /**
     * Método chamado para enviar produtos para a BD
     */
    public void confirmarJSON2() {
        //Converter para string e inserir na BD
        String idprod;
        String descprod;
        float quantprod;
        String tipo_qtd;
        float prc;
        float vat;
        float preco_total;

        boolean teste = false;
        for (int o = 0; o < code[o].length(); o++) {
            idprod = code[o];
            descprod = descricao[o];
            quantprod = Float.parseFloat(qtd[o]);
            tipo_qtd = tipoqtd[o];
            prc = Float.parseFloat(preco[o]);
            vat = Float.parseFloat(VAT[o]);
            preco_total = Float.parseFloat(precototal[o]);

            StockDAO stockDAO = new StockDAO();
            teste = StockDAO.IFfindItem(idprod);

            if (teste) {
                //Atualiza ‘id’, quantidade
                StockDAO.updateStock(idprod, quantprod);
            } else {
                StockDAO.insertNewStock(idprod, descprod, tipo_qtd, quantprod, prc, vat, preco_total);
            }
        }
    }

    /**
     * Volta atrás consoante o tipo_user
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
