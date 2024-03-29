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
 * Classe pública do controlador GH_GerirFuncionario.fxml
 */
public class GH_GerirFuncionario {

    @FXML
    private ListView<String> listFuncionarios;

    List<EstadoFunc> arrayFuncionario = new ArrayList<>();
    UtilizadorDAO utilizadorDAO = new UtilizadorDAO();

    String utilizadorselect;

    /**
     * Método chamado ao iniciar a scene
     * @param location
     * The location used to resolve relative paths for the root object, or
     * {@code null} if the location is not known.
     *
     * @param resources
     * The resources used to localize the root object, or {@code null} if
     * the root object was not localized.
     */
    public void initialize() {
        listFuncionarios.getItems().clear();
        listFuncionarios.getSelectionModel().clearSelection();

        arrayFuncionario = UtilizadorDAO.findAllFuncionarios();
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

    /**
     * Ativar o funcionário escolhido para a BD
     */
    @FXML
    public void AtivarFunc() {
        index = utilizadorselect.indexOf("-");
        textoformatado = utilizadorselect.substring(0, index);
        textoformatado = textoformatado.replace("Nome:", "");
        textoformatado = textoformatado.trim();
        System.out.println(textoformatado);

        FuncionarioDAO.updateAtiva(textoformatado);
        //Atualizar listview
        initialize();
    }

    @FXML
    public void InativarFunc() {

        index = utilizadorselect.indexOf("-");
        textoformatado = utilizadorselect.substring(0, index);
        textoformatado = textoformatado.replace("Nome:", "");
        textoformatado = textoformatado.trim();
        System.out.println(textoformatado);

        FuncionarioDAO.updateInativa(textoformatado);
        //Atualizar listview
        initialize();
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


}
