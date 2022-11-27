package hotel.agencypt.Controller;

//Bibliotecas
import Classes.DAO.ReservaDAO;
import Classes.DAO.ServicoDAO;
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
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;
import java.util.ResourceBundle;

public class C_Reserva implements Initializable {

    Connection con = ConnectionDB.establishConnection();

    @FXML
    public DatePicker datePickerI=new DatePicker();
    @FXML
    public DatePicker datePickerF=new DatePicker();
    @FXML
    public Label dataILabel=new Label();
    @FXML
    public Label dataFLabel=new Label();
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
    Integer index = -1;

    List<Servico> arrayServico = new ArrayList<>();


    ServicoDAO servicoDAO=new ServicoDAO();


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        cboxTquarto.setItems(listTquarto);

        arrayServico = servicoDAO.findServico();
        for (Servico s : arrayServico) {
            System.out.println(s.getDescricao());
            listServtodos.getItems().add(
                  s.getDescricao()+" "+s.getPreco()
            );
        }
        listServtodos.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                servicoselct = listServtodos.getSelectionModel().getSelectedItem();
            }
        });
        //drop box dos tipos de quartos
        cboxTquarto.setItems(listTquarto);
    }

    /**
     * Adicionar item da lista selecionada para a outra lista
     */
    public void onAdicionar () {
        index = listServtodos.getSelectionModel().getSelectedIndex();
        if (index == -1){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Aviso");
            alert.setHeaderText("Sem seleção");
            alert.setContentText("Selecione primeiro um serviço da lista.");
            alert.showAndWait();
        } else {
            listServesco.getItems().add(servicoselct);
            listServtodos.getItems().remove(servicoselct);
        }
    }

    /**
     * Remover item da lista selecionada
     */
    public void onRemover() {
        index = listServesco.getSelectionModel().getSelectedIndex();
        if (index == -1){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Aviso");
            alert.setHeaderText("Sem seleção");
            alert.setContentText("Selecione primeiro um serviço da lista.");
            alert.showAndWait();
        } else {
            listServtodos.getItems().add(listServesco.getSelectionModel().getSelectedItem());
            listServesco.getItems().remove(listServesco.getSelectionModel().getSelectedItem());
        }
    }


    Date datai;
    @FXML
    public void getDateI(ActionEvent event){
        ZoneId defaultZoneId = ZoneId.systemDefault();

        try {
            LocalDate myDate = datePickerI.getValue();
            dataILabel.setText(myDate.toString());
            datai= (Date) Date.from(myDate.atStartOfDay(defaultZoneId).toInstant());
        }
        catch(Exception e) {
            System.out.println(e);
        }
    }

    Date dataf;
    @FXML
    public void getDateF(ActionEvent event){
        ZoneId defaultZoneId = ZoneId.systemDefault();

        try {
            LocalDate myDate = datePickerF.getValue();
            dataFLabel.setText(myDate.toString());

            dataf= (Date) Date.from(myDate.atStartOfDay(defaultZoneId).toInstant());
        }
        catch(Exception e) {
            System.out.println(e);
        }
    }


    @FXML
    public void onEsTquarto(){
        escolhaTquarto= (String) cboxTquarto.getValue();
        if(escolhaTquarto=="Individual"){
            
        } else if (escolhaTquarto=="Duplo") {
            
        } else {

        }
    }

    @FXML
 public void onCriaReserva(){
        if (datePickerI.getValue() == null || datePickerF.getValue() == null){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Aviso");
            alert.setHeaderText("Sem seleção");
            alert.setContentText("Selecione uma data de inicio e fim.");
            alert.showAndWait();
        } else if (datePickerF.getValue().compareTo(datePickerI.getValue())<0) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Aviso");
            alert.setHeaderText("Datas inválidas");
            alert.setContentText("A data de fim deve ser maior que a data de inicio.");
            alert.showAndWait();
        } else {
     ReservaDAO reservaDAO=new ReservaDAO();
     Reserva reserva=new Reserva();

     //testestar dps colocar os valores inseridos
     reserva.setIdcliente(1);
     reserva.setIdquarto(1);
     reserva.setIdservico(1);
     reserva.setNumcartao(1);
     reserva.setDataI(datai);
     reserva.setDataF(dataf);

     reservaDAO.criaReserva(reserva);
        }
 }

    /**
     * Método para voltar atrás
     * @param actionEvent
     */
    @FXML
    public void voltarAtras(ActionEvent actionEvent) {
        try {
            Singleton.open("Cliente", "Hotel >> Cliente");
        } catch (Exception e) {
            System.out.println("Erro ao voltar atrás.");
        }
    }
}
