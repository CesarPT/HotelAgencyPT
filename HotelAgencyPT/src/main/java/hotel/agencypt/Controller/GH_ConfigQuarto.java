package hotel.agencypt.Controller;

import Classes.DAO.QuartoDAO;
import Classes.DAO.QuartoStockDAO;
import Classes.DAO.StockDAO;
import Classes.Quarto;
import Classes.QuartoStock;
import Classes.Stock;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

/**
 * Classe pública do controlador GH_ConfigQuarto.fxml
 */
public class GH_ConfigQuarto {

    String quartoEscolhido;

    List<Quarto> arrayDescricaoQuarto = new ArrayList<>();
    @FXML
    private TextArea textAlterarDescricao;
    @FXML
    private ComboBox<String> comboBoxPisoID;
    @FXML
    private ComboBox<String> comboBoxQuartoID;
    @FXML
    private Button verificarPrecoID;
    @FXML
    private Button verDescricaoID;
    @FXML
    private Button verificarDescricao;
    @FXML
    private TextArea textPreco;
    @FXML
    private TextArea textAlterarPreco;
    @FXML
    private ListView<String> listProdutosStock;
    @FXML
    private ListView<String> listProdutosQuarto;
    QuartoDAO qDAO = new QuartoDAO();
    StockDAO sDAO = new StockDAO();
    QuartoStockDAO qsDAO = new QuartoStockDAO();
    List<Quarto> arrayQuartos = new ArrayList<>();
    List<Quarto> arrayPreco = new ArrayList<>();
    List<Stock> arrayStock = new ArrayList<>();
    List <QuartoStock> arrayQuartoStock = new ArrayList<>();
    String prodSelct;
    String prodSelct2;

    /**
     * Iniciar/Atualizar a scene
     */
    public void initialize() {
        //Limpar tudo e inserir valores na combobox
        comboBoxPisoID.getSelectionModel().clearSelection();
        comboBoxPisoID.getItems().clear();

        comboBoxPisoID.getItems().addAll(
                "Piso 1",
                "Piso 2"
        );
        arrayStock = sDAO.findStock();

        listProdutosQuarto.getItems().clear();
        for (Stock s : arrayStock) {
            listProdutosStock.getItems().add(s.getProduct_description()+"||Qtd: "+s.getQuantidade());
        }

        listProdutosStock.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                prodSelct = listProdutosStock.getSelectionModel().getSelectedItem();
            }
        });
        listProdutosQuarto.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                prodSelct2 = listProdutosQuarto.getSelectionModel().getSelectedItem();
            }
        });
    }


    public void verificarPiso(ActionEvent event) {
        if (Objects.equals(comboBoxPisoID.getSelectionModel().getSelectedItem(), "Piso 1")) {
            Controller.getInstance().setPiso(1);
            //Limpar combobox
            comboBoxQuartoID.getSelectionModel().clearSelection();
            comboBoxQuartoID.getItems().clear();
            //Pesquisar e Adicionar da base de dados para a combobox
            arrayQuartos = qDAO.findQuarto();
            for (Quarto q : arrayQuartos) {
                comboBoxQuartoID.getItems().add(
                        "Num Quarto: " + q.getIdQuarto() + " Descricao: " + q.getDescricao()
                );
            }
            //Ativar combobox
            comboBoxQuartoID.setDisable(false);
        } else if (Objects.equals(comboBoxPisoID.getSelectionModel().getSelectedItem(), "Piso 2")) {
            Controller.getInstance().setPiso(2);
            //Limpar combobox
            comboBoxQuartoID.getSelectionModel().clearSelection();
            comboBoxQuartoID.getItems().clear();
            //Pesquisar e Adicionar da base de dados para a combobox
            arrayQuartos = qDAO.findQuarto();
            for (Quarto q : arrayQuartos) {
                comboBoxQuartoID.getItems().add(
                        "Num Quarto: " + q.getIdQuarto() + " Descricao: " + q.getDescricao()
                );
            }
            //Ativar combobox
            comboBoxQuartoID.setDisable(false);
        }
    }

    public void verificarQuarto(ActionEvent event) {
        arrayQuartoStock.clear();
        listProdutosQuarto.getItems().clear();
        listProdutosQuarto.getSelectionModel().clearSelection();
        //Envia para o controlador a seleção de quarto
        //Pega só no número na combobox
        String quartoEscolhido = comboBoxQuartoID.getValue()
                .replaceAll("[a-zA-Z]", "")
                .replace(":", "")
                .replace(" ", "");
        System.out.println(quartoEscolhido);
        Controller.getInstance().setIdquarto(Integer.parseInt(quartoEscolhido));
        //Se não selecionou um quarto
        int index = comboBoxQuartoID.getSelectionModel().getSelectedIndex();
        if (index == -1) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Aviso");
            alert.setHeaderText("Sem seleção");
            alert.setContentText("Selecione primeiro um quarto.");
            alert.showAndWait();
        } else {
            arrayPreco = qDAO.findPreco();
            for (Quarto q : arrayPreco) {
                textPreco.setText(String.valueOf(q.getPreco()));
            }
            arrayQuartoStock = qsDAO.findQuartoStock();
            for (QuartoStock qs : arrayQuartoStock) {
                listProdutosQuarto.getItems().add(qs.getProduct_description()+"||Qtd: "+qs.getQuantidade());
            }
            //Ativar botões e limpar texto na TextArea preço
            verificarPrecoID.setDisable(false);
            verDescricaoID.setDisable(false);
            verificarDescricao.setDisable(false);
            textAlterarPreco.clear();
            //Ativar textarea
            textAlterarPreco.setEditable(true);
        }
    }

    /**
     * Verificações do preço introduzido quando clica no botão
     */
    public void verificarAlterarPreco() {
        //Se o preço que inseriu é igual ao match de:
        //Qualquer dígito de 0 a 9 com 3 digitos no máximo
        //Com casas decimais de 1 minimo e 2 máximo
        //Possívies matches: 99.9, 99.99, 999.9, 999.99
        if (textAlterarPreco.getText().matches("^\\d+(,\\d{3})*(\\.\\d{1,2})?$")) {
            QuartoDAO daoQuarto = new QuartoDAO();
            Quarto quarto = new Quarto();

            //Update: Alterar preço na base de dados
            quarto.setPreco(Float.parseFloat(textAlterarPreco.getText()));
            daoQuarto.updatePreco(quarto);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Informação");
            alert.setHeaderText(null);
            alert.setContentText("Atualizou com sucesso.");
            alert.showAndWait();
            //Atualiza o preço
            verificarQuarto(new ActionEvent());
            //Se não emite aviso
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Aviso");
            alert.setHeaderText("Preço inválido");
            alert.setContentText("Exemplos de formatos de preços possíves:\n" +
                    "99.9\n" +
                    "99.99\n" +
                    "999.9\n" +
                    "999.99"
            );
            alert.showAndWait();
        }
    }

    /**
     * Volta atrás para a View GestorHotel.fxml
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

    /**
     * Verificações do input da TextArea ao clicar no botão
     */
    public void verificarAlterarDescricao() {
        //Verifica se o texto tem mais de 200 carateres
        if (textAlterarDescricao.getText().length() <= 200) {
            QuartoDAO daoQuarto = new QuartoDAO();
            Quarto quarto = new Quarto();

            //Update: Alterar descricao na base de dados
            quarto.setDescricao(textAlterarDescricao.getText());
            daoQuarto.updateDescricao(quarto);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Informação");
            alert.setHeaderText(null);
            alert.setContentText("Atualizou com sucesso.");
            alert.showAndWait();
            //Atualiza a descrição
            verificarQuarto(new ActionEvent());
            //Se não emite aviso
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Aviso");
            alert.setHeaderText("Texto inválido");
            alert.setContentText("Apenas é possível inserir uma descrição até 200 carateres");
            alert.showAndWait();
        }
    }

    /**
     * Botão para ver a descrição do quarto da base de dados
     * @param Event
     */
    public void verDescricao(ActionEvent Event) {
        quartoEscolhido = comboBoxQuartoID.getSelectionModel().getSelectedItem()
                .replaceAll("[a-zA-Z]", "")
                .replace(":", "")
                .replace(" ", "");

        Controller.getInstance().setIdquarto(Integer.parseInt(quartoEscolhido));

        arrayDescricaoQuarto = qDAO.findDescricaoQuarto();
        for (Quarto q : arrayDescricaoQuarto) {
            textAlterarDescricao.setText(q.getDescricaoQuarto());
        }

    }

    String prodFinal;

    /**
     * Verificações, adicionar produtos no quarto e atualizar listviews
     * @param Event
     * @throws InterruptedException
     */
    public void addProdutoQuarto(ActionEvent Event) throws InterruptedException {
        quartoEscolhido = comboBoxQuartoID.getSelectionModel().getSelectedItem()
                .replaceAll("[a-zA-Z]", "")
                .replace(":", "")
                .replace(" ", "");

        Controller.getInstance().setIdquarto(Integer.parseInt(quartoEscolhido));

        int index = listProdutosStock.getSelectionModel().getSelectedIndex();
        if (index == -1) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Aviso");
            alert.setHeaderText("Sem seleção");
            alert.setContentText("Selecione primeiro um serviço da lista.");
            alert.showAndWait();
        } else {
            prodFinal = prodSelct.substring(0, prodSelct.indexOf("||")).trim();
            Controller.getInstance().setProdutosEscolhidos(prodFinal);

            arrayStock = sDAO.decrementarStock();
            arrayQuartoStock = qsDAO.updateStockQuarto();
            limparTudo();
        }
    }
    String prodFinal2;
    /**
     * Verificações, remover produtos no quarto e atualizar listviews
     * @param Event
     */
    public void deleteProdutoQuarto(ActionEvent Event){
        quartoEscolhido = comboBoxQuartoID.getSelectionModel().getSelectedItem()
                .replaceAll("[a-zA-Z]", "")
                .replace(":", "")
                .replace(" ", "");

        Controller.getInstance().setIdquarto(Integer.parseInt(quartoEscolhido));

        int index = listProdutosQuarto.getSelectionModel().getSelectedIndex();
        if (index == -1) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Aviso");
            alert.setHeaderText("Sem seleção");
            alert.setContentText("Selecione primeiro um serviço da lista.");
            alert.showAndWait();
        } else {
            prodFinal2 = prodSelct2.substring(0, prodSelct2.indexOf("||")).trim();
            Controller.getInstance().setProdutosEscolhidos(prodFinal2);
            arrayQuartoStock = qsDAO.RemoveStockQuarto();
            limparTudo();
        }
    }

    /**
     * Método sem retorno chamado para atualizar as listviews
     */
    public void limparTudo(){
        listProdutosQuarto.getItems().clear();
        listProdutosStock.getItems().clear();
        listProdutosQuarto.getSelectionModel().clearSelection();
        listProdutosStock.getSelectionModel().clearSelection();
        arrayStock = sDAO.findStock();
        for (Stock s : arrayStock) {
            listProdutosStock.getItems().add(s.getProduct_description() + "||Qtd: " + s.getQuantidade());
        }
        arrayQuartoStock = qsDAO.findQuartoStock();
        for (QuartoStock qs : arrayQuartoStock) {
            listProdutosQuarto.getItems().add(qs.getProduct_description()+"||Qtd: "+qs.getQuantidade());
        }

    }
}
