package hotel.agencypt.Controller;

import Classes.DAO.ServicoDAO;
import Classes.Servico;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


public class GH_ConfigServicos implements Initializable {

@FXML
private ListView<String> listServtodos;
@FXML
private TextField descricaoServ, precoServ;

String servicoselct,descricao,preco,testeapaga;
List<Servico> arrayServico = new ArrayList<>();
ServicoDAO servicoDAO = new ServicoDAO();
Alert alertapaga= new Alert(Alert.AlertType.NONE);

    public GH_ConfigServicos() {
    }


    public void initialize(URL location, ResourceBundle resources) {

        /**
         * percorre todos os serviços da base de dados na tabela Serviços
         */
        arrayServico = servicoDAO.findServico();
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
    }

    @FXML
    public void onCriarReserva(){
        descricao = descricaoServ.getText();
        preco = precoServ.getText();
        servicoDAO.criaServico(descricao, Float.parseFloat(preco));
    }

    @FXML
    public void onApagarReserva(){


        if(testeapaga==null){
            alertapaga.setAlertType(Alert.AlertType.WARNING);
            alertapaga.setContentText("Selecione um serviço para apagar");
            alertapaga.show();
        } else {

            testeapaga=servicoselct;
            testeapaga = testeapaga.replace(".","")
                    .replaceAll("[0-9]","");
            testeapaga=testeapaga.substring(0,testeapaga.length() - 1);

            servicoDAO.apagaServico(testeapaga);
        }

    }


    @FXML
    public void voltarAtras(ActionEvent actionEvent) {
        try {
            Singleton.open("GestorHotel", "Hotel >> Gestor de Hotel");
        } catch (Exception e) {
            System.out.println("Erro ao voltar atrás.");
        }
    }
}
