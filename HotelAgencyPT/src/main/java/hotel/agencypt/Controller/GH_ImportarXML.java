package hotel.agencypt.Controller;

import Classes.DAO.EntregaDAO;
import Classes.DAO.StockDAO;
import Classes.Entrega;
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
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;
import java.io.File;
import java.nio.file.Path;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe pública do controlador GH_GerirStock.fxml
 */
public class GH_ImportarXML {
    @FXML
    private ListView listCaminho;

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


    StockDAO sDAO = new StockDAO();
    List<Stock> arrayStock = new ArrayList<>();

    /**
     * Clicar no botão de Importar XML
     *
     * @param event
     */
    public void clicarBtnImportar(ActionEvent event) throws Exception {
        FileChooser fc = new FileChooser();
        fc.setInitialDirectory(new File("C:\\"));
        fc.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Ficheiros XML", "*.xml"));
        File selectedFile = fc.showOpenDialog(null);

        //Se não selecionar
        if (selectedFile != null) {
            listCaminho.getItems().add(selectedFile.getAbsolutePath());
            //Ler ficheiro XML com o path que escolheu
            Lexml(Path.of(selectedFile.getAbsolutePath()));
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
        TableColumDescricao.setCellValueFactory(new PropertyValueFactory<>("product_description"));
        TableColumnQtd.setCellValueFactory(new PropertyValueFactory<>("quantidade"));
        TableColumnTipoQtd.setCellValueFactory(new PropertyValueFactory<>("tipo_qtd"));
        TableColumnPreco.setCellValueFactory(new PropertyValueFactory<>("preco"));
        TableColumnVAT.setCellValueFactory(new PropertyValueFactory<>("vat"));
        TableColumnPrecoTotal.setCellValueFactory(new PropertyValueFactory<>("preco_total"));
        TableViewStock.setItems(obsEntradas);
    }

    public void confirmarXML() throws Exception {
        //Enviar para a base de dados
        confirmarXML2();
        //Notificar
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Informação");
        alert.setHeaderText("Base de Dados");
        alert.setContentText("Base de dados atualizada com o ficheiro XML.");
        alert.showAndWait();
        //Atualizar tableview
        atualizarTableView();
    }

    //IMPORTAR XML


    String[] pind;
    String[] pd;
    String[] iq;
    int[] qt;
    double[] pu;
    double[] tax;
    Float[] cv;
    float[] taxp;
    double[] taxa;
    String[] taxl;
    Float[] lba;
    StockDAO stockDAO = new StockDAO();
    NodeList nodeslba;

    public void Lexml(Path path) throws Exception {

        //documento builder para o XML
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true);
        DocumentBuilder builder = factory.newDocumentBuilder();

        //get Document
        Document document = builder.parse(new File(path.toUri()));


        XPathFactory xpathfactory = XPathFactory.newInstance();
        XPath xpath = xpathfactory.newXPath();


        //print do XML na consola
        //---------------------HEADER----------------------------------
        String on = "";
        Date data;
        SimpleDateFormat originalFormat = new SimpleDateFormat("yyyyMMdd");
        String datanc = "";
        //Data
        String dy = "";
        String dm = "";
        String dd = "";
        String pi;
        String emp;
        String rua = "";
        String np;
        String ct;
        String pc;
        String con;

        EntregaDAO entregaDAO = new EntregaDAO();
        Entrega entrega = new Entrega();

        //OrderNUmber
        XPathExpression expron = xpath.compile("//Header//OrderNumber/text()");
        Object resulton = expron.evaluate(document, XPathConstants.NODESET);
        NodeList nodeon = (NodeList) resulton;
        for (int i = 0; i < nodeon.getLength(); i++) {
            on = nodeon.item(i).getNodeValue();
            entrega.setOrderNumber(on);
        }


        //DATE
        XPathExpression exprdy = xpath.compile("//Header//OrderDate/Date/Year/text()");
        Object resultdy = exprdy.evaluate(document, XPathConstants.NODESET);
        NodeList nodesdy = (NodeList) resultdy;
        for (int i = 0; i < nodesdy.getLength(); i++) {
            dy = (nodesdy.item(i).getNodeValue());
        }

        XPathExpression exprdm = xpath.compile("//Header//OrderDate/Date/Month/text()");
        Object resultdm = exprdm.evaluate(document, XPathConstants.NODESET);
        NodeList nodesdm = (NodeList) resultdm;
        for (int i = 0; i < nodesdm.getLength(); i++) {
            dm = (nodesdm.item(i).getNodeValue());
        }

        XPathExpression exprdd = xpath.compile("//Header//OrderDate/Date/Day/text()");
        Object resultdd = exprdd.evaluate(document, XPathConstants.NODESET);
        NodeList nodesdd = (NodeList) resultdd;
        for (int i = 0; i < nodesdd.getLength(); i++) {
            dd = (nodesdd.item(i).getNodeValue());
            datanc = dy + "-" + dm + "-" + dd;
            data = Date.valueOf((datanc));
            System.out.println(data);
            entrega.setData_entrega(data);
        }

        //Suplier party
        XPathExpression exprsp = xpath.compile("//Header//SupplierParty/PartyIdentifier/text()");
        Object resultsp = exprsp.evaluate(document, XPathConstants.NODESET);
        NodeList nodessp = (NodeList) resultsp;
        for (int i = 0; i < nodessp.getLength(); i++) {
            pi = (nodessp.item(i).getNodeValue());
            entrega.setParty_identifier(pi);
        }


        XPathExpression exprna = xpath.compile("//Header//SupplierParty/NameAddress/Name/text()");
        Object resultna = exprna.evaluate(document, XPathConstants.NODESET);
        NodeList nodesna = (NodeList) resultna;
        for (int i = 0; i < nodesna.getLength(); i++) {
            emp = (nodesna.item(i).getNodeValue());
            entrega.setEmpresa(emp);
        }

        XPathExpression exprad1 = xpath.compile("//Header//SupplierParty/NameAddress/Address1/text()");
        Object resultad1 = exprad1.evaluate(document, XPathConstants.NODESET);
        NodeList nodesad1 = (NodeList) resultad1;
        for (int i = 0; i < nodesad1.getLength(); i++) {
            rua = (nodesad1.item(i).getNodeValue());
            entrega.setRua(rua);
        }
        XPathExpression exprad2 = xpath.compile("//Header//SupplierParty/NameAddress/Address2/text()");
        Object resultad2 = exprad2.evaluate(document, XPathConstants.NODESET);
        NodeList nodesad2 = (NodeList) resultad2;
        for (int i = 0; i < nodesad2.getLength(); i++) {
            np = (nodesad2.item(i).getNodeValue());
            entrega.setN_porta(np);
        }
        XPathExpression exprct = xpath.compile("//Header//SupplierParty/NameAddress/City/text()");
        Object resultct = exprct.evaluate(document, XPathConstants.NODESET);
        NodeList nodesct = (NodeList) resultct;
        for (int i = 0; i < nodesct.getLength(); i++) {
            ct = (nodesct.item(i).getNodeValue());
            entrega.setCidade(ct);
        }
        XPathExpression exprpc = xpath.compile("//Header//SupplierParty/NameAddress/PostalCode/text()");
        Object resultpc = exprpc.evaluate(document, XPathConstants.NODESET);
        NodeList nodespc = (NodeList) resultpc;
        for (int i = 0; i < nodespc.getLength(); i++) {
            pc = (nodespc.item(i).getNodeValue());
            entrega.setCp(pc);
        }

        XPathExpression exprcon = xpath.compile("//Header//SupplierParty/NameAddress/Country/text()");
        Object resultcon = exprcon.evaluate(document, XPathConstants.NODESET);
        NodeList nodescon = (NodeList) resultcon;
        for (int i = 0; i < nodescon.getLength(); i++) {
            con = (nodescon.item(i).getNodeValue());
            entrega.setPais(con);
        }

        //entregaDAO.insertEntradaInfo(entrega);


        String prodid;
        //lINE

        //product id
        pind = new String[10];
        XPathExpression exprpi = xpath.compile("//Line//Product/ProductIdentifier/text()");
        Object resultpi = exprpi.evaluate(document, XPathConstants.NODESET);
        NodeList nodespi = (NodeList) resultpi;
        for (int i = 0; i < nodespi.getLength(); i++) {
            pind[i] = (nodespi.item(i).getNodeValue());
        }


        //product descriçao
        pd = new String[10];
        XPathExpression exprpd = xpath.compile("//Line//Product/ProductDescription/text()");
        Object resultpd = exprpd.evaluate(document, XPathConstants.NODESET);
        NodeList nodespd = (NodeList) resultpd;
        for (int i = 0; i < nodespd.getLength(); i++) {
            pd[i] = (nodespd.item(i).getNodeValue());
        }


        //peso
        iq = new String[10];
        XPathExpression expriq = xpath.compile("//Line//InformationalQuantity//Value[@UOM='Kilogram']/text()");
        Object resultiq = expriq.evaluate(document, XPathConstants.NODESET);
        NodeList nodesiq = (NodeList) resultiq;
        for (int i = 0; i < nodesiq.getLength(); i++) {
            iq[i] = nodesiq.item(i).getNodeValue();
            System.out.println(iq[i]);
        }


        //Quantidade
        qt = new int[10];
        XPathExpression exprqt = xpath.compile("//Line//Quantity//Value[@UOM='Unit']/text()");
        Object resultqt = exprqt.evaluate(document, XPathConstants.NODESET);
        NodeList nodesqt = (NodeList) resultqt;
        for (int i = 0; i < nodesqt.getLength(); i++) {
            qt[i] = Integer.parseInt((nodesqt.item(i).getNodeValue()));
        }


        //preço unitario
        pu = new double[10];
        XPathExpression exprpu = xpath.compile("//Line//PricePerUnit//CurrencyValue/text()");
        Object resultpu = exprpu.evaluate(document, XPathConstants.NODESET);
        NodeList nodespu = (NodeList) resultpu;
        for (int i = 0; i < nodespu.getLength(); i++) {
            pu[i] = Double.parseDouble((nodespu.item(i).getNodeValue()));
            System.out.println(pu[i]);
        }

        //Taxas
        tax = new double[10];
        XPathExpression exprtax = xpath.compile("//Line//MonetaryAdjustment//MonetaryAdjustmentLine/text()");
        Object resulttax = exprtax.evaluate(document, XPathConstants.NODESET);
        NodeList nodestax = (NodeList) resulttax;
        for (int i = 0; i < nodestax.getLength(); i++) {
            tax[i] = Double.parseDouble((nodestax.item(i).getNodeValue()));
            System.out.println(tax[i]);
        }

        //CurrencyValue (preço sem taxa)
        cv = new Float[10];
        XPathExpression exprcv = xpath.compile("//Line//MonetaryAdjustment//MonetaryAdjustmentStartAmount/CurrencyValue/text()");
        Object resultcv = exprcv.evaluate(document, XPathConstants.NODESET);
        NodeList nodescv = (NodeList) resultcv;
        for (int i = 0; i < nodescv.getLength(); i++) {
            cv[i] = Float.parseFloat((nodescv.item(i).getNodeValue()));
            System.out.println(cv[i]);
        }


        //Tax percent
        taxp = new float[10];
        XPathExpression exprtaxp = xpath.compile("//Line//TaxAdjustment//TaxPercent/text()");
        Object resulttaxp = exprtaxp.evaluate(document, XPathConstants.NODESET);
        NodeList nodestaxp = (NodeList) resulttaxp;
        for (int i = 0; i < nodestaxp.getLength(); i++) {
            taxp[i] = Float.parseFloat((nodestaxp.item(i).getNodeValue()));
            System.out.println(taxp[i]);
        }

        //Tax Amount
        taxa = new double[10];
        XPathExpression exprtaxa = xpath.compile("//Line//TaxAdjustment//TaxAmount/CurrencyValue/text()");
        Object resulttaxa = exprtaxa.evaluate(document, XPathConstants.NODESET);
        NodeList nodestaxa = (NodeList) resulttaxa;
        for (int i = 0; i < nodestaxa.getLength(); i++) {
            taxa[i] = Double.parseDouble((nodestaxa.item(i).getNodeValue()));
            System.out.println(taxa[i]);
        }

        //Tax Locationm
        taxl = new String[10];
        XPathExpression exprtaxl = xpath.compile("//Line//TaxAdjustment//TaxLocation/text()");
        Object resulttaxl = exprtaxl.evaluate(document, XPathConstants.NODESET);
        NodeList nodestaxl = (NodeList) resulttaxl;
        for (int i = 0; i < nodestaxl.getLength(); i++) {
            taxl[i] = (nodestaxl.item(i).getNodeValue());
            System.out.println(taxl[i]);
        }

        //LineBaseAmount
        lba = new Float[10];
        XPathExpression exprlba = xpath.compile("//Line//LineBaseAmount//CurrencyValue/text()");
        Object resultlba = exprlba.evaluate(document, XPathConstants.NODESET);
        nodeslba = (NodeList) resultlba;
        for (int i = 0; i < nodeslba.getLength(); i++) {
            lba[i] = Float.valueOf(nodeslba.item(i).getNodeValue());
            System.out.println(lba[i]);
        }
        //Adicionar ao observablelist para atualizar a tableview
        for (int i = 0; i < nodeslba.getLength(); i++) {
            obsEntradas.add(new Stock(pind[i], pd[i], qt[i], iq[i], cv[i], taxp[i], (lba[i])));
        }
    }

    public void confirmarXML2() {
        //Converter para string e inserir na BD
        int quantprod;
        String idprod;
        String descprod;
        String tipo_qtd;
        float preco;
        float vat;
        float preco_total;

        boolean teste = false;
        for (int i = 0; i < nodeslba.getLength(); i++) {
            idprod = pind[i];
            descprod = pd[i];
            quantprod = qt[i];
            tipo_qtd = iq[i];
            preco = cv[i];
            vat = taxp[i];
            preco_total = lba[i];

            teste = stockDAO.IFfindItem(idprod);

            if (teste == true) {
               StockDAO.updateStock(idprod, quantprod);
            } else {
                StockDAO.insertNewStock(idprod, descprod, tipo_qtd, quantprod, preco, vat, preco_total);
            }
        }
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
