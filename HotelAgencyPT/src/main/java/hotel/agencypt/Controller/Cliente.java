package hotel.agencypt.Controller;

import Classes.Cartao;
import Classes.DAO.CartaoDAO;
import Classes.DAO.ReservaDAO;
import Classes.Reserva;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Cliente implements Initializable {
    @FXML
    protected Button creatReserva;
    @FXML
    private ListView<String> listReserva;
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
    List<Cartao> arrayCartao = new ArrayList<>();
    List<Reserva> arrayReserva = new ArrayList<>();
    String reservasel;

    /**
     * @param location
     * The location used to resolve relative paths for the root object, or
     * {@code null} if the location is not known.
     *
     * @param resources
     * The resources used to localize the root object, or {@code null} if
     * the root object was not localized.
     *
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
            //numcartao, datacriacao, dataexp do username do cliente
            arrayCartao = cDAO.findCartao();
            for (Cartao c : arrayCartao) {
                numcartao.setText(String.valueOf(c.getNumcartao()));
                datacriacao.setText(String.valueOf(c.getDatacriacao()));
                dataexp.setText(String.valueOf(c.getDataexp()));
            }
                //Se o cliente não tiver cartão
                if (numcartao.getText().isEmpty()){
                    labelSemCartao.setTextFill(Color.RED);
                    labelSemCartao.setText("Sem cartão.\nFaça uma reserva.");
                } else {
                    labelSemCartao.setTextFill(Color.GREEN);
                    labelSemCartao.setText("Cartão validado.");
                }

                //Adicionar reservas com o username
        arrayReserva = rDAO.findReserva();
        for (Reserva r : arrayReserva) {
            listReserva.getItems().add(
                    "Reserva " + r.getIdreserva()
            );
        }
        listReserva.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                reservasel=listReserva.getSelectionModel().getSelectedItem();
                teste.setText(reservasel);
            }
        });
    }

    /**
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

    public void onCreateReserva(){

    }

    /**
     *
     */
    public void abrirCLienteReserva(){
        try {
            Stage window = (Stage) creatReserva.getScene().getWindow();
            window.close();
            Singleton.open("C_Reserva", "Hotel >> Cliente >> Informações do Cliente");

        }
        catch (Exception e){
            System.out.println("erro ao abrir o scene");
        }
    }

}
