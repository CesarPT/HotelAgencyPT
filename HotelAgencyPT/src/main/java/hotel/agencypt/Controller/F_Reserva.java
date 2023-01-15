package hotel.agencypt.Controller;

//Bibliotecas

import Classes.Cliente;
import Classes.DAO.*;
import Classes.*;
import hotel.agencypt.Api.Main;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Date;
import java.time.Duration;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.stream.Stream;

import static Classes.DAO.ReservaServicoDAO.criaReservaServico;
import static Classes.DAO.ServicoDAO.findServicoEsc;

/**
 * Classe pública do Controlador F_Reserva.fxml
 */
public class F_Reserva implements Initializable {
    ObservableList<String> obsPark = FXCollections.observableArrayList();
    @FXML
    private TableView<objparking> tableParque;
    @FXML
    private TableColumn<objparking, String> columnLugar;
    @FXML
    private TableColumn<objparking, Double> columnPreco;
    @FXML
    private TableColumn<objparking, Boolean> columnTipoLugar;
    @FXML
    private TableColumn<objparking, Boolean> columnOcupado;
    @FXML
    private TextField textPrecoServicos;
    @FXML
    private TextField textPrecoQuarto;
    @FXML
    private TextField textPrecoTotal;
    @FXML
    private TextField textNoites;
    @FXML
    private TextField nomeCliente;
    @FXML
    public DatePicker datePickerI = new DatePicker();
    @FXML
    public DatePicker datePickerF = new DatePicker();
    @FXML
    public Label dataILabel = new Label();
    @FXML
    public Label dataFLabel = new Label();
    @FXML
    public Label labelAviso;
    @FXML
    public ComboBox cboxTquarto;
    @FXML
    public TextField textPrecoLugar;
    @FXML
    public TextField textLugar;

    @FXML
    ObservableList<String> listTquarto = FXCollections.observableArrayList("Individual", "Duplo", "Familiar");
    @FXML
    private ListView<String> listServtodos;
    @FXML
    private ListView<String> listServesco;
    @FXML
    private ListView<String> listidClienteInsere;

    //var para selecionar na Listview dos servicos
    String servicoselct;
    String servicoselce;
    String escolhaTquarto;
    String preco;
    Integer index = -1;
    List<Servico> arrayServico = new ArrayList<>();
    List<Utilizador> arrayUtilizador = new ArrayList<>();
    QuartoDAO quartoDAO = new QuartoDAO();

    @FXML
    private ComboBox comboBoxQuantidade;

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

        cboxTquarto.setItems(listTquarto);

        /**
         * percorre todos os serviços da base de dados na tabela Serviços
         */
        arrayServico = ServicoDAO.findServicoAtivo();
        for (Servico s : arrayServico) {
            listServtodos.getItems().add(
                    s.getDescricao() + " " + s.getPreco()
            );
        }

        /**
         * deixa selecionar
         */
        listServtodos.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                servicoselct = listServtodos.getSelectionModel().getSelectedItem();
            }
        });

        listServesco.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                servicoselce = listServesco.getSelectionModel().getSelectedItem();
            }
        });

        arrayUtilizador = UtilizadorDAO.findClientepReserva();
        for (Utilizador u : arrayUtilizador) {
            listidClienteInsere.getItems().add(String.valueOf(u.getNomeUtilizador()));
        }

        listidClienteInsere.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                idCliente = listidClienteInsere.getSelectionModel().getSelectedItem();
            }
        });

        //drop box dos tipos de quartos
        cboxTquarto.setItems(listTquarto);


        //Parque de estacionamento (API)
        ObservableList<objparking> obsPark = hotel.agencypt.Api.Main.GetTodosLugares();

        columnLugar.setCellValueFactory(new PropertyValueFactory<>("ParkingSpot"));
        columnPreco.setCellValueFactory(new PropertyValueFactory<>("Price"));
        //columnTipoLugar.setCellValueFactory(new PropertyValueFactory<>("Indoor"));
        columnTipoLugar.setCellValueFactory(new PropertyValueFactory<>("IndoorString"));
        columnOcupado.setCellValueFactory(new PropertyValueFactory<>("OccupiedString"));
        tableParque.setItems(obsPark);


    }


    /**
     * Adicionar item da lista selecionada para a outra lista
     */
    public void onAdicionar() {
        index = listServtodos.getSelectionModel().getSelectedIndex();
        if (index == -1) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Aviso");
            alert.setHeaderText("Sem seleção");
            alert.setContentText("Selecione primeiro um serviço da lista.");
            alert.showAndWait();
        } else {
            //Atualizar preço dos serviços
            listServesco.getItems().add(servicoselct);
            /**
             * Recebe os valores da listview
             * Resultado original:  [Restaurante: 15.0] por exemplo
             * Retira-se [ ] espaços
             * Altera qualquer caracter de A a Z por espaço
             * Resultado final: 15.0,valor_seguinte,...
             */
            preco = listServesco.getItems().toString()
                    .replace("[", "")
                    .replace("]", "")
                    .replace(" ", "")
                    .replaceAll("[a-zA-Z]", "");
            //Soma o que está antes da virgula e depois da virgula
            textPrecoServicos.setText(String.valueOf(Stream.of(preco.split(","))
                    .mapToDouble(Double::parseDouble).sum()));

            listServtodos.getItems().remove(servicoselct);
        }

    }

    /**
     * Remover item da lista selecionada
     */
    public void onRemover() {
        index = listServesco.getSelectionModel().getSelectedIndex();
        if (index == -1) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Aviso");
            alert.setHeaderText("Sem seleção");
            alert.setContentText("Selecione primeiro um serviço da lista.");
            alert.showAndWait();
        } else {
            //Atualizar preço dos serviços
            listServtodos.getItems().add(listServesco.getSelectionModel().getSelectedItem());
            listServesco.getItems().remove(listServesco.getSelectionModel().getSelectedItem());
            /**
             * Recebe os valores da listview
             * Resultado original:  [Restaurante: 15.0] por exemplo
             * Retira-se [ ] espaços
             * Altera qualquer caracter de A a Z por espaço
             * Resultado final: 15.0,valor_seguinte,...
             */
            //Resolver erro do empty String quando não tem nada na listview:
            if (listServesco.getItems().isEmpty()) {
                textPrecoServicos.setText("0");
            } else {
                preco = listServesco.getItems().toString()
                        .replace("[", "")
                        .replace("]", "")
                        .replace(" ", "")
                        .replaceAll("[a-zA-Z]", "");
                System.out.println(preco);
                //Soma o que está antes da virgula e depois da virgula
                textPrecoServicos.setText(String.valueOf(Stream.of(preco.split(","))
                        .mapToDouble(Double::parseDouble).sum()));
            }
        }
    }


    Date myDateI;
    Date datai;

    /**
     * Data de inicio
     * @param event
     */
    @FXML
    public void getDateI(ActionEvent event) {
        ZoneId defaultZoneId = ZoneId.systemDefault();

        try {
            LocalDate myDate = datePickerI.getValue();
            myDateI = Date.valueOf(datePickerI.getValue());
            dataILabel.setText(myDateI.toString());
            datai = Date.valueOf(dataILabel.getText());

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    Date myDateF;
    Date dataf;

    /**
     * Data de fim
     * @param event
     */
    @FXML
    public void getDateF(ActionEvent event) {
        ZoneId defaultZoneId = ZoneId.systemDefault();

        try {
            LocalDate myDate = datePickerF.getValue();
            myDateF = Date.valueOf(datePickerF.getValue());
            dataFLabel.setText(myDateF.toString());
            dataf = Date.valueOf(dataFLabel.getText());


        } catch (Exception e) {
            System.out.println(e);
        }

    }

    List<Quarto> arrayPrimQuarto = new ArrayList<>();
    String primQuarto;
    List<Quarto> arrayPrecoQuarto = new ArrayList<>();
    int idQuartoesc = 0;

    /**
     * Escolher tipo de quarto
     */
    @FXML
    public void onEsTquarto() {
        escolhaTquarto = (String) cboxTquarto.getValue();

        if (Objects.equals(escolhaTquarto, "Individual")) {
            arrayPrimQuarto = quartoDAO.findQuartoIndividual();
            for (Quarto q : arrayPrimQuarto) {
                idQuartoesc = q.getIdQuarto();
            }


        } else if (Objects.equals(escolhaTquarto, "Duplo")) {
            arrayPrimQuarto = quartoDAO.findQuartoDuplo();
            for (Quarto q : arrayPrimQuarto) {
                idQuartoesc = q.getIdQuarto();
            }


        } else if (Objects.equals(escolhaTquarto, "Familiar")) {
            arrayPrimQuarto = quartoDAO.findQuartoFamiliar();
            for (Quarto q : arrayPrimQuarto) {
                idQuartoesc = q.getIdQuarto();
            }
        }
    }

    @FXML
    int idClient;
    String idCliente;
    int idClientV = 0;
    ClienteDAO clienteDAO = new ClienteDAO();

    List<Classes.Cliente> arrayIDCliente = new ArrayList<>();
    /**
     * Cliente escolhido na listview
     */
    public void clientescolhido() {
        Controller.getInstance().setNomeCliente(idCliente);
        arrayIDCliente = clienteDAO.findIDClientePeloNome();
        for (Cliente c : arrayIDCliente) {
            idClient = c.getIdCliente();
        }
    }

    String pesquisadcliente;

    /**
     * Pesquisar nome do cliente
     */
    @FXML
    public void onNomeCliente() {
        pesquisadcliente = nomeCliente.getText();
        //System.out.println(pesquisadcliente);
        UtilizadorDAO.findClientepReservaCnome(pesquisadcliente);
        listidClienteInsere.getItems().clear();
        listidClienteInsere.getSelectionModel().clearSelection();
        listidClienteInsere.getItems().add(pesquisadcliente);
    }


    ReservaDAO reservaDAO = new ReservaDAO();
    Reserva reserva = new Reserva();


    /**
     * Abre uma scene para escolher produtos do quarto
     */
    public void escolherProdutos() {
        try {
            //Se não escolher um tipo de quarto
            System.out.println(Controller.getInstance().getIdQuarto());
            Stage stage = (Stage) listServesco.getScene().getWindow();
            stage.close();
            Singleton.open("F_DecrementarStock", "User: " + Controller.getInstance().getUsername()
                    + " | Hotel >> " + Controller.getInstance().getTipo_user());
        } catch (Exception e) {
            System.out.println("Erro ao abrir/fechar scene.");
        }
    }

    //PARQUE DE ESTACIONAMENTO

    /**
     * Ao clicar no botão addPark adiciona o preço + o lugar
     */
    public void addPark(){
        textPrecoLugar.setText(String.valueOf(tableParque.getSelectionModel().getSelectedItem().Price));
        textLugar.setText(String.valueOf(tableParque.getSelectionModel().getSelectedItem().ParkingSpot));
    }

    /**
     * Ao clicar no botão removePark
     * @param event
     */
    public void removePark(ActionEvent event){
        textPrecoLugar.setText("0");
        textLugar.setText("---");
    }

    /**
     * Botão de atualizar preços
     * @param event
     */
    public void atualizarPrecos(ActionEvent event) {
        if (datePickerI.getValue() == null || datePickerF.getValue() == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Aviso");
            alert.setHeaderText("Sem seleção");
            alert.setContentText("Selecione uma data de inicio e fim.");
            alert.showAndWait();
        } else if (datePickerF.getValue().isBefore(datePickerI.getValue())) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Aviso");
            alert.setHeaderText("Datas inválidas");
            alert.setContentText("A data de fim deve ser maior que a data de inicio.");
            alert.showAndWait();
        } else if (cboxTquarto.getSelectionModel().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Aviso");
            alert.setHeaderText("Sem seleção");
            alert.setContentText("Selecione um tipo de quarto e um quarto disponível.");
            alert.showAndWait();
        } else if (!listServesco.getItems().toString().contains("Base")) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Aviso");
            alert.setHeaderText("Sem o serviço Base");
            alert.setContentText("A reserva tem que ter o serviço: base que está incluida em todos os quartos.");
            alert.showAndWait();
        } else if (datePickerI.getValue().isBefore(java.time.LocalDate.now())) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Aviso");
            alert.setHeaderText("Data de ínicio errada");
            alert.setContentText("A data de ínicio tem que ser maior ou igual à data atual.");
            alert.showAndWait();
        } else {

            //Preço do quarto, noites e Preço total
            Controller.getInstance().setIdquarto(idQuartoesc);
            arrayPrecoQuarto = quartoDAO.findPreco();
            for (Quarto q : arrayPrecoQuarto) {
                textPrecoQuarto.setText(String.valueOf(q.getPreco()));
            }
            Duration diff = Duration.between(datePickerF.getValue().atStartOfDay(), datePickerI.getValue().atStartOfDay());
            long diffDays = diff.toDays();
            textNoites.setText(String.valueOf(diffDays).replace("-", ""));

            //SOMAR TUDO
            int noites = Integer.parseInt(textNoites.getText());
            double precoQuarto = Double.parseDouble(textPrecoQuarto.getText());
            double precoServico = Double.parseDouble(textPrecoServicos.getText());
            double precoPark = Double.parseDouble(textPrecoLugar.getText());
            precoPark = precoPark*noites;
            textPrecoLugar.setText(String.valueOf(precoPark));
            double precoTotal = (precoQuarto * noites) + precoPark + precoServico;
            textPrecoTotal.setText(String.valueOf(precoTotal));
        }
    }



    // CRIAR RESERVA


    /**
     * Verificações e criar a reserva para o cliente
     * @param event
     */
    @FXML
    public void onCriaReserva(ActionEvent event) {
        //Verificações
        if (datePickerI.getValue() == null || datePickerF.getValue() == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Aviso");
            alert.setHeaderText("Sem seleção");
            alert.setContentText("Selecione uma data de inicio e fim.");
            alert.showAndWait();
        } else if (datePickerF.getValue().isBefore(datePickerI.getValue())) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Aviso");
            alert.setHeaderText("Datas inválidas");
            alert.setContentText("A data de fim deve ser maior que a data de inicio.");
            alert.showAndWait();
        } else if (cboxTquarto.getSelectionModel().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Aviso");
            alert.setHeaderText("Sem seleção");
            alert.setContentText("Selecione um tipo de quarto e um quarto disponível.");
            alert.showAndWait();
        } else if (!listServesco.getItems().toString().contains("Base")) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Aviso");
            alert.setHeaderText("Sem o serviço Base");
            alert.setContentText("A reserva tem que ter o serviço: base que está incluida em todos os quartos.");
            alert.showAndWait();
        } else if (datePickerI.getValue().isBefore(java.time.LocalDate.now())) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Aviso");
            alert.setHeaderText("Data de ínicio errada");
            alert.setContentText("A data de ínicio tem que ser maior ou igual à data atual.");
            alert.showAndWait();
        } else {
            onEsTquarto();

            idClient = listidClienteInsere.getSelectionModel().getSelectedIndex();

           // nomeC = String.valueOf(listidClienteInsere.getSelectionModel().getSelectedIndex());

            clientescolhido();


            if (idClient != 0 || myDateI != null || myDateF != null) {
                //Criar a reserva
                reserva.setIdcliente(idClient);
                reserva.setIdquarto(idQuartoesc);
                reserva.setDataI(datai);
                reserva.setDataF(dataf);

                ReservaDAO.criaReserva(reserva);
                //Relação de tabela
                RelacionaResServ();

                if (Objects.equals(textPrecoLugar.getText(), "0")){
                    System.out.println("Não foi criado um ticket, sem seleção.");
                } else {
                    //Parque de estacionamento (API) POST - Criar ticket
                    int idCliente = idClient;
                    LocalDate StartDate = datePickerI.getValue();
                    LocalDate EndDate = datePickerF.getValue();
                    String ParkingSpot = textLugar.getText();
                    hotel.agencypt.Api.Main.PostTicket(idCliente, StartDate, EndDate, ParkingSpot);
                }
            }
        }
    }


    int ultimaReserv;
    List<Reserva> arrayUltimaReserva = new ArrayList<>();
    int relacionaservico;
    String descservico;
    //cona


    public void RelacionaResServ() {

        //List tipo reserva com o valor da ultima reserva encontrada
        arrayUltimaReserva = ReservaDAO.findUltReserva();

        for (Reserva r : arrayUltimaReserva) {
            ultimaReserv = r.getIdreserva();
        }
        System.out.println(ultimaReserv);

        //------------------------------------------------------

        descservico = listServesco.getItems().toString()
                .replace("[", "")
                .replace("]", "")
                .replace(" ", "")
                .replace(".", "")
                .replaceAll("[0-9]", "");

        //-------------------------------
        String[] bdservico = descservico.split(",");

        for (int i = 0; i < bdservico.length; i++) {
            System.out.println(bdservico[i]);

            arrayServico = findServicoEsc(bdservico[i]);


            for (Servico s : arrayServico) {
                relacionaservico = s.getIdServico();

                criaReservaServico(ultimaReserv, relacionaservico);

            }
        }
        escolherProdutos();
    }
    //------------------------------------------------------


    /**
     * Método para voltar atrás
     * @param actionEvent
     */
    @FXML
    public void voltarAtras(ActionEvent actionEvent) {
        try {
            Singleton.open("funcionariointerface", "Hotel >> Funcionario");
        } catch (Exception e) {
            System.out.println("Erro ao voltar atrás.");
        }
    }

}