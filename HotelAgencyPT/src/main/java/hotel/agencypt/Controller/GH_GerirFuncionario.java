package hotel.agencypt.Controller;

import Classes.DAO.FuncionarioDAO;
import Classes.DAO.UtilizadorDAO;
import Classes.EstadoFunc;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Classe pública do controlador GH_GerirStock.fxml
 */
public class GH_GerirFuncionario implements Initializable {

    @FXML
    private ListView<String> listFuncionarios;

    List<EstadoFunc> arrayFuncionario = new ArrayList<>();
    UtilizadorDAO utilizadorDAO = new UtilizadorDAO();

    String utilizadorselect;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        arrayFuncionario = utilizadorDAO.findAllFuncionarios();
        for (EstadoFunc estadoFunc : arrayFuncionario) {
            listFuncionarios.getItems().add("Nome: " + estadoFunc.getNomefunc() + " - " + estadoFunc.getEstado()
            );
        }

        /**
         * deixa selecionar
         */
        listFuncionarios.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                utilizadorselect = listFuncionarios.getSelectionModel().getSelectedItem();
            }
        });

    }


    String textoformatado;
    int index;

    @FXML
    public void AtivarFunc() {


        index = utilizadorselect.indexOf("-");
        textoformatado = utilizadorselect.substring(0, index);
        textoformatado = textoformatado.replace("Nome:", "");
        textoformatado = textoformatado.trim();
        System.out.println(textoformatado);

        FuncionarioDAO.updateAtiva(textoformatado);
    }
    @FXML
    public void InativarFunc() {

        index = utilizadorselect.indexOf("-");
        textoformatado = utilizadorselect.substring(0, index);
        textoformatado = textoformatado.replace("Nome:", "");
        textoformatado = textoformatado.trim();
        System.out.println(textoformatado);

        FuncionarioDAO.updateInativa(textoformatado);
    }


    /**
     * Volta atrás para a View GestorHotel.fxml
     *
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
