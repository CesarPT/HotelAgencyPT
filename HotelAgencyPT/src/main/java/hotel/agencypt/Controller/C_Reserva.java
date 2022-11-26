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
import javafx.scene.control.*;

import java.net.URL;
import java.sql.Connection;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
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

    List<Servico> arrayServico = new ArrayList<>();


    ServicoDAO servicoDAO=new ServicoDAO();


    @Override
    public void initialize(URL location, ResourceBundle resources) {

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

    public void onAdicionar () {
            listServesco.getItems().add(servicoselct);
            listServtodos.getItems().remove(servicoselct);
    }

    public void onRemover() {
        listServtodos.getItems().add(servicoselce);
        listServesco.getItems().remove(servicoselce);
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
