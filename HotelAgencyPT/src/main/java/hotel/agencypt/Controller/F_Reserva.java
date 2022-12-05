package hotel.agencypt.Controller;

//Bibliotecas

import Classes.Cliente;
import Classes.DAO.ClienteDAO;
import Classes.DAO.QuartoDAO;
import Classes.DAO.ReservaDAO;
import Classes.DAO.ServicoDAO;
import Classes.Quarto;
import Classes.Reserva;
import Classes.Servico;
import DataBase.ConnectionDB;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.sql.Connection;
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

public class F_Reserva implements Initializable {

    Connection con = ConnectionDB.establishConnection();

    @FXML
    private TextField textPrecoServicos;
    @FXML
    private TextField textPrecoQuarto;
    @FXML
    private TextField textPrecoTotal;
    @FXML
    private TextField textNoites;
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
    ObservableList<String> listTquarto = FXCollections.observableArrayList("Individual", "Duplo", "Familiar");
    @FXML
    private ListView<String> listServtodos;
    @FXML
    private ListView<String> listServesco;

    //var para selecionar na Listview dos servicos
    String servicoselct;
    String servicoselce;
    String escolhaTquarto;
    String preco;
    String idQuarto;
    Integer index = -1;
    List<Servico> arrayServico = new ArrayList<>();
    ServicoDAO servicoDAO = new ServicoDAO();
    QuartoDAO quartoDAO = new QuartoDAO();
    ArrayList<String> listaqq = new ArrayList<>();


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        cboxTquarto.setItems(listTquarto);

        arrayServico = servicoDAO.findServico();
        for (Servico s : arrayServico) {
            System.out.println(s.getDescricao());
            listServtodos.getItems().add(
                    s.getDescricao() + " " + s.getPreco()
            );
        }
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
        //drop box dos tipos de quartos
        cboxTquarto.setItems(listTquarto);
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

    @FXML
    public void getDateI(ActionEvent event) {
        ZoneId defaultZoneId = ZoneId.systemDefault();

        try {
            LocalDate myDate = datePickerI.getValue();
            dataILabel.setText(myDate.toString());
            datai = (Date) Date.from(myDate.atStartOfDay(defaultZoneId).toInstant());
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    Date myDateF;
    Date dataf;

    @FXML
    public void getDateF(ActionEvent event) {
        ZoneId defaultZoneId = ZoneId.systemDefault();

        try {
            LocalDate myDate = datePickerF.getValue();
            dataFLabel.setText(myDate.toString());

            dataf = (Date) Date.from(myDate.atStartOfDay(defaultZoneId).toInstant());
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    List<Quarto> arrayPrimQuarto = new ArrayList<>();
    List<Quarto> arrayPrecoQuarto = new ArrayList<>();
    int idQuartoesc = 0;

    @FXML
    public void onEsTquarto() {
        escolhaTquarto = (String) cboxTquarto.getValue();

        if (Objects.equals(escolhaTquarto, "Individual")) {
            arrayPrimQuarto = quartoDAO.findQuartoIndividual();

        } else if (Objects.equals(escolhaTquarto, "Duplo")) {
            arrayPrimQuarto = quartoDAO.findQuartoDuplo();

        } else if (Objects.equals(escolhaTquarto, "Familiar")) {
            arrayPrimQuarto = quartoDAO.findQuartoFamiliar();

        }
    }


    String idCliente;
    int idClientV = 0;
    @FXML
    TextField idClienteInsere;
    List<Cliente> arrayCliente = new ArrayList<>();
    ClienteDAO clienteDAO = new ClienteDAO();

    @FXML
    public void onidClienteInsere() {
        idCliente = idClienteInsere.getText();

        //System.out.println(idCliente);
        //System.out.println(Integer.parseInt(idCliente));
        arrayCliente = clienteDAO.findClienteCid(Integer.parseInt(idCliente));
        for (Cliente c : arrayCliente) {
            System.out.println(c.getIdCliente());
            idClientV = c.getIdCliente();
        }

    }


    public void atualizarPrecos(ActionEvent event) {
        if (datePickerI.getValue() == null || datePickerF.getValue() == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Aviso");
            alert.setHeaderText("Sem seleção");
            alert.setContentText("Selecione uma data de inicio e fim.");
            alert.showAndWait();
        } else if (datePickerF.getValue().compareTo(datePickerI.getValue()) < 0) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Aviso");
            alert.setHeaderText("Datas inválidas");
            alert.setContentText("A data de fim deve ser maior que a data de inicio.");
            alert.showAndWait();
        } else if (cboxTquarto.getSelectionModel().isEmpty() || idClienteInsere.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Aviso");
            alert.setHeaderText("Sem seleção");
            alert.setContentText("Selecione um tipo de quarto e um quarto disponível.");
            alert.showAndWait();
        } else if (!idClienteInsere.getText().matches("^[1-500]*$")) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Aviso");
            alert.setHeaderText("Texto inválido: id cliente");
            alert.setContentText("Escreva o seu número de cliente.");
            alert.showAndWait();
        } else if (!listServesco.getItems().toString().contains("Base")) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Aviso");
            alert.setHeaderText("Sem o serviço Base");
            alert.setContentText("A reserva tem que ter o serviço: base que está incluida em todos os quartos.");
            alert.showAndWait();
        } else {
            idQuarto = idClienteInsere.getText();

            //Preço do quarto, noites e Preço total
            Controller.getInstance().setIdquarto(Integer.parseInt(idQuarto));
            arrayPrecoQuarto = quartoDAO.findPreco();
            for (Quarto q : arrayPrecoQuarto) {
                textPrecoQuarto.setText(String.valueOf(q.getPreco()));
            }
            Duration diff = Duration.between(datePickerF.getValue().atStartOfDay(), datePickerI.getValue().atStartOfDay());
            long diffDays = diff.toDays();
            textNoites.setText(String.valueOf(diffDays).replace("-", ""));

            //SOMAR TUDO
            int noites = Integer.parseInt(textNoites.getText());
            float precoQuarto = Float.parseFloat(textPrecoQuarto.getText());
            float precoServico = Float.parseFloat(textPrecoServicos.getText());
            float precoTotal = precoQuarto * noites + precoServico;
            textPrecoTotal.setText(String.valueOf(precoTotal));
        }
    }

    ReservaDAO reservaDAO;
    Reserva reserva = new Reserva();

    @FXML
    public void onCriaReserva(ActionEvent event) {
        if (datePickerI.getValue() == null || datePickerF.getValue() == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Aviso");
            alert.setHeaderText("Sem seleção");
            alert.setContentText("Selecione uma data de inicio e fim.");
            alert.showAndWait();
        } else if (datePickerF.getValue().compareTo(datePickerI.getValue()) < 0) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Aviso");
            alert.setHeaderText("Datas inválidas");
            alert.setContentText("A data de fim deve ser maior que a data de inicio.");
            alert.showAndWait();
        } else if (cboxTquarto.getSelectionModel().isEmpty() || idClienteInsere.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Aviso");
            alert.setHeaderText("Sem seleção");
            alert.setContentText("Selecione um tipo de quarto e um quarto disponível.");
            alert.showAndWait();
        } else if (!idClienteInsere.getText().matches("^[1-500]*$")) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Aviso");
            alert.setHeaderText("Texto inválido: id cliente");
            alert.setContentText("Escreva o seu número de cliente.");
            alert.showAndWait();
        } else if (!listServesco.getItems().toString().contains("Base")) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Aviso");
            alert.setHeaderText("Sem o serviço Base");
            alert.setContentText("A reserva tem que ter o serviço: base que está incluida em todos os quartos.");
            alert.showAndWait();
        } else {
            onEsTquarto();

            if (idClientV != 0 || myDateI != null || myDateI != null) {
                //Criar a reserva
                reserva.setIdcliente(idClientV);
                reserva.setIdquarto(idQuartoesc);
                reserva.setNumcartao(1);
                reserva.setDataI(myDateI);
                reserva.setDataF(myDateF);

                reservaDAO.criaReserva(reserva);

                //Relação de tabela
                RelacionaResServ();
            }
        }

    }


    int ultimaReserv;
    List<Reserva> arrayUltimaReserva = new ArrayList<>();
    int relacionaservico;
    String descservico;


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
    }


    /**
     * Método para voltar atrás
     *
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

    public void onIdCliente(ActionEvent event) {
    }


}



