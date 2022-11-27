package hotel.agencypt.Controller;


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
<<<<<<< .merge_file_ZuR1Mt
import javafx.scene.control.*;

=======
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
>>>>>>> .merge_file_SYmckF
import java.net.URL;
import java.sql.Connection;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;
import java.util.ResourceBundle;

<<<<<<< .merge_file_ZuR1Mt
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
=======
public class C_Reserva {
>>>>>>> .merge_file_SYmckF
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
    Integer index = -1;

<<<<<<< .merge_file_ZuR1Mt
    List<Servico> arrayServico = new ArrayList<>();


    ServicoDAO servicoDAO=new ServicoDAO();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
=======
    public void initialize() {
        cboxquarto.setItems(listTquarto);
>>>>>>> .merge_file_SYmckF

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


    String escolhaTquarto;
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
