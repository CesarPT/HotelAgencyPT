package hotel.agencypt.Controller;

import Classes.Cartao;
import Classes.DAO.CartaoDAO;
import Classes.DAO.RegEntradaDAO;
import Classes.DAO.ReservaDAO;
import Classes.RegEntrada;
import Classes.Reserva;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.paint.Color;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Cliente {
    @FXML
    protected Button creatReserva;
    @FXML
    private ListView<String> listReserva;
    @FXML
    private ListView<String> listRegEntrada;
    @FXML
    private Label teste;
    @FXML
    private Label labelSemCartao;
    @FXML
    private TextArea numcartao;
    @FXML
    private TextArea datacriacao;
    @FXML
    private TextArea dataexp;
    @FXML
    private ChoiceBox<String> opcoesEntrada;
    private String[] opEntrada = {"Restaurante", "Piscina Privada", "Spá", "Sala de Conferência", "Evento Cultural"};
    CartaoDAO cDAO = new CartaoDAO();
    ReservaDAO rDAO = new ReservaDAO();
    RegEntradaDAO reDAO = new RegEntradaDAO();
    List<Cartao> arrayCartao = new ArrayList<>();
    List<Reserva> arrayReserva = new ArrayList<>();
    List<RegEntrada> arrayRegEntrada = new ArrayList<>();
    String reservasel;

    public void initialize() {
        //Limpar e aparecer opcoes da choicebox
        opcoesEntrada.getSelectionModel().clearSelection();
        opcoesEntrada.getItems().clear();
        opcoesEntrada.getItems().addAll(opEntrada);
        opcoesEntrada.setOnAction(this::getopEntrada);

        //numcartao, datacriacao, dataexp do username do cliente
        arrayCartao = cDAO.findCartao();
        for (Cartao c : arrayCartao) {
            numcartao.setText(String.valueOf(c.getNumcartao()));
            datacriacao.setText(String.valueOf(c.getDatacriacao()));
            dataexp.setText(String.valueOf(c.getDataexp()));
        }
        //Se o cliente não tiver cartão
        if (numcartao.getText().isEmpty()) {
            labelSemCartao.setTextFill(Color.RED);
            labelSemCartao.setText("Sem cartão.\nFaça uma reserva.");
        } else {
            labelSemCartao.setTextFill(Color.GREEN);
            labelSemCartao.setText("Cartão validado.");
        }

        //Limpar tudo e Adicionar reservas com o username
        listReserva.getItems().clear();
        arrayReserva.clear();
        arrayReserva = rDAO.findReserva();
        for (Reserva r : arrayReserva) {
            listReserva.getItems().add(
                    "Reserva " + r.getIdreserva()
            );
        }
        listReserva.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                reservasel = listReserva.getSelectionModel().getSelectedItem();
                teste.setText(reservasel);
            }
        });

        //Limpar tudo e Adicionar registros de entrada
        listRegEntrada.getItems().clear();
        arrayRegEntrada.clear();
        arrayRegEntrada = reDAO.findRegEntradaQuarto();
        for (RegEntrada r : arrayRegEntrada) {
            listRegEntrada.getItems().add(
                    "Reserva " + r.getIdreserva() + " | " + r.getLocal() + " | " + r.getData() + " | " + r.getHora()
            );
        }
        listRegEntrada.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                reservasel = listRegEntrada.getSelectionModel().getSelectedItem();
            }
        });

    }


    //Adicionar RegEntrada de quarto na base de dados
    @FXML
    public void registroEntradaQuarto(ActionEvent actionEvent) {
        if (listReserva.getSelectionModel().getSelectedItem() == null){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Aviso");
            alert.setHeaderText("Sem seleção");
            alert.setContentText("Selecione uma reserva da lista.");
            alert.showAndWait();
        } else {
            RegEntradaDAO daoRegEntrada = new RegEntradaDAO();
            RegEntrada regentrada = new RegEntrada();

            //Receber a data e hora atual do Computador
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
            LocalDateTime data = LocalDateTime.now();

            //Adiciona numcartao, local, data e hora
            regentrada.setNumcartao(Integer.parseInt(numcartao.getText()));
            regentrada.setLocal("Quarto");
            regentrada.setData(dtf.format(data));
            regentrada.setHora(new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime()));
            daoRegEntrada.insertRegEntrada(regentrada);

            //Atualizar listviews
            initialize();
        }
    }

    public void getopEntrada(ActionEvent event) {
        String opEntrada = opcoesEntrada.getValue();
        if (listReserva.getSelectionModel().getSelectedItem() == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Aviso");
            alert.setHeaderText("Sem seleção");
            alert.setContentText("Selecione uma reserva da lista.");
            alert.showAndWait();
        }else {
            //Se a opcao selecionada na ChoiceBox for Restaurante
            if (opcoesEntrada.getSelectionModel().getSelectedItem() == "Restaurante") {
                RegEntradaDAO daoRegEntrada = new RegEntradaDAO();
                RegEntrada regentrada = new RegEntrada();

                //Receber a data e hora atual do Computador
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
                LocalDateTime data = LocalDateTime.now();

                //Adiciona numcartao, local, data e hora
                regentrada.setNumcartao(Integer.parseInt(numcartao.getText()));
                regentrada.setLocal("Restaurante");
                regentrada.setData(dtf.format(data));
                regentrada.setHora(new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime()));
                daoRegEntrada.insertRegEntrada(regentrada);

                //Atualizar listviews
                initialize();

                //Se a opcao selecionada na ChoiceBox for Piscina Privada
            } else if (opcoesEntrada.getSelectionModel().getSelectedItem() == "Piscina Privada") {
                RegEntradaDAO daoRegEntrada = new RegEntradaDAO();
                RegEntrada regentrada = new RegEntrada();

                //Receber a data e hora atual do Computador
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
                LocalDateTime data = LocalDateTime.now();

                //Adiciona numcartao, local, data e hora
                regentrada.setNumcartao(Integer.parseInt(numcartao.getText()));
                regentrada.setLocal("Piscina privada");
                regentrada.setData(dtf.format(data));
                regentrada.setHora(new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime()));
                daoRegEntrada.insertRegEntrada(regentrada);

                //Atualizar listviews
                initialize();

                //Se a opcao selecionada na ChoiceBox for Spá
            } else if (opcoesEntrada.getSelectionModel().getSelectedItem() == "Spá") {
                RegEntradaDAO daoRegEntrada = new RegEntradaDAO();
                RegEntrada regentrada = new RegEntrada();

                //Receber a data e hora atual do Computador
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
                LocalDateTime data = LocalDateTime.now();

                //Adiciona numcartao, local, data e hora
                regentrada.setNumcartao(Integer.parseInt(numcartao.getText()));
                regentrada.setLocal("Spá");
                regentrada.setData(dtf.format(data));
                regentrada.setHora(new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime()));
                daoRegEntrada.insertRegEntrada(regentrada);

                //Atualizar listviews
                initialize();

                //Se a opcao selecionada na ChoiceBox for Sala de Conferência
            } else if (opcoesEntrada.getSelectionModel().getSelectedItem() == "Sala de Conferência") {
                RegEntradaDAO daoRegEntrada = new RegEntradaDAO();
                RegEntrada regentrada = new RegEntrada();

                //Receber a data e hora atual do Computador
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
                LocalDateTime data = LocalDateTime.now();

                //Adiciona numcartao, local, data e hora
                regentrada.setNumcartao(Integer.parseInt(numcartao.getText()));
                regentrada.setLocal("Sala de Conferência");
                regentrada.setData(dtf.format(data));
                regentrada.setHora(new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime()));
                daoRegEntrada.insertRegEntrada(regentrada);

                //Atualizar listviews
                initialize();

                //Se a opcao selecionada na ChoiceBox for Evento Cultural
            } else if (opcoesEntrada.getSelectionModel().getSelectedItem() == "Evento Cultural") {
                RegEntradaDAO daoRegEntrada = new RegEntradaDAO();
                RegEntrada regentrada = new RegEntrada();

                //Receber a data e hora atual do Computador
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
                LocalDateTime data = LocalDateTime.now();

                //Adiciona numcartao, local, data e hora
                regentrada.setNumcartao(Integer.parseInt(numcartao.getText()));
                regentrada.setLocal("Evento Cultural");
                regentrada.setData(dtf.format(data));
                regentrada.setHora(new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime()));
                daoRegEntrada.insertRegEntrada(regentrada);

                //Atualizar listviews
                initialize();
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
            Singleton.open("Login", "Login");
        } catch (Exception e) {
            System.out.println("Erro ao voltar atrás.");
        }
    }

    public void onCreateReserva() {

    }


}
