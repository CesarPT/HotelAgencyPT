package hotel.agencypt.Controller;

import Classes.DAO.ServicoDAO;
import Classes.Servico;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Classe pública do GH_ConfigServicos.fxml
 */
public class GH_ConfigServicos implements Initializable {

    @FXML
    private ListView<String> listServtodos;
    @FXML
    private TextField descricaoServ, precoServ, estadoServ;


    String servicoselct, descricao, preco,estado, ativa,inativa;
    List<Servico> arrayServico = new ArrayList<>();
    ServicoDAO servicoDAO = new ServicoDAO();
    Alert alertInativa = new Alert(Alert.AlertType.NONE);



    /**
     * Método usado ao iniciar a scene
     * s.getDescricao(),s.getPreco(),s.getEstado()
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

        /**
         * percorre todos os serviços da base de dados na tabela Serviços
         */

        arrayServico = ServicoDAO.findServico();
        for (Servico s : arrayServico) {
            listServtodos.getItems().add(
                    s.getDescricao()
            );
        }

        /**
         * deixa selecionar
         */
        listServtodos.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                servicoselct = listServtodos.getSelectionModel().getSelectedItem();
                mandaDescriçao(servicoselct);
                mandaPreco(servicoselct);
                mandaEstado(servicoselct);
            }
        });

    }

    /**
     * Adiciona descrição ao serviço escolhido
     * @param escolhido
     */
    public void mandaDescriçao(String escolhido){
        descricaoServ.setText(escolhido);
    }

    /**
     * Adiciona preço ao serviço escolhido
     * @param escolhido
     */
    public void mandaPreco(String escolhido){

        arrayServico = servicoDAO.findPrecoServico(escolhido);

        for (Servico s : arrayServico) {
            precoServ.setText(String.valueOf(s.getPreco()));
        }
   }

    /**
     * Adiciona o estado ativo/inativo no serviço escolhido
     * @param escolhido
     */
    public void mandaEstado(String escolhido){

        arrayServico = servicoDAO.findEstadoServico(escolhido);

        for (Servico s : arrayServico) {
            estadoServ.setText(s.getEstado());
        }
    }

    public boolean verificaAdiciona(String descricao){
        int valida=0;
        arrayServico= ServicoDAO.findServicoEsc(descricao);
        for (Servico s : arrayServico) {
            valida=s.getIdServico();
        }

        if(valida!=0){
            System.out.println("existe");
            return true;
        }else {
            return false;
        }
    }


    /**
     * Metodo para Adicionar um serviço a base de dados
     */
    @FXML
    public void onAdicionaServiço() {
        descricao = descricaoServ.getText();
        preco = precoServ.getText();
        estado=estadoServ.getText();
        boolean valida=verificaAdiciona(descricao);

        if(!valida) {

            ServicoDAO.criaServico(descricao, Float.parseFloat(preco), estado);

        }
    }


    /**
     * Metodo para Ativar um servilo escolhido na base de dados
     */
    @FXML
    public void onAtivarServico() {
        if (servicoselct == null) {
            alertInativa.setAlertType(Alert.AlertType.WARNING);
            alertInativa.setContentText("Selecione um serviço para Ativar");
            alertInativa.show();
        } else {

            ativa = servicoselct;
            ativa=ativa.trim();

            ServicoDAO.ativaServico(ativa);
        }

    }

    /**
     * Metodo para Inativar um serviço escolhido na base de dados
     */
    @FXML
    public void onInativarServico() {
        if (servicoselct == null) {
            alertInativa.setAlertType(Alert.AlertType.WARNING);
            alertInativa.setContentText("Selecione um serviço para Inativar");
            alertInativa.show();
        } else {

            inativa = servicoselct;
            inativa=inativa.trim();

            ServicoDAO.inativaServico(inativa);
        }

    }

    /**
     * Método para voltar atrás
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
