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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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
    CartaoDAO cDAO = new CartaoDAO();
    ReservaDAO rDAO = new ReservaDAO();
    RegEntradaDAO reDAO = new RegEntradaDAO();
    List<Cartao> arrayCartao = new ArrayList<>();
    List<Reserva> arrayReserva = new ArrayList<>();
    List<RegEntrada> arrayRegEntrada = new ArrayList<>();
    String reservasel;

    public void initialize() {
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
        arrayRegEntrada = reDAO.findRegEntradaQuarto();
        for (RegEntrada r : arrayRegEntrada) {
            listRegEntrada.getItems().add(
                    "Entrada: " + r.getLocal() + " | " + r.getData()
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
        RegEntradaDAO daoRegEntrada = new RegEntradaDAO();
        RegEntrada regentrada = new RegEntrada();

        //Receber a data e hora atual do Computador
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();

        //Adiciona numcartao, local e data
        regentrada.setLocal("Quarto");
        regentrada.setData(dtf.format(now));
        regentrada.setNumcartao(Integer.parseInt(numcartao.getText()));
        daoRegEntrada.insertRegEntrada(regentrada);

        //Atualizar listviews
        initialize();
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

    /**
     * Método que abre um Scene para criar uma reserva
     */
    public void abrirCLienteReserva() {
        try {
            Stage window = (Stage) creatReserva.getScene().getWindow();
            window.close();
            Singleton.open("C_Reserva", "Hotel >> Cliente >> Criar uma reserva");

        } catch (Exception e) {
            System.out.println("Erro ao fechar/abrir o scene.");
        }
    }

}
