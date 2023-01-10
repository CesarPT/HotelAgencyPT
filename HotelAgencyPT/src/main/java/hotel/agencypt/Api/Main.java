package hotel.agencypt.Api;

import Classes.GETparking;
import Classes.objparking;
import com.google.gson.Gson;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;
import java.util.Scanner;

public class Main {
    static Gson gson = new Gson();
    static ObservableList<objparking> obsPark = FXCollections.observableArrayList();

    public static void main(String[] args) {

        Scanner myObj = new Scanner(System.in);  // Create a Scanner object

        System.out.println("1 para todos os lugares, \n" +
                "2 para todos os tickets, \n"+
                "3 para todos os tickets, \n"+
                "4 para todos os tickets, \n");
        int escolha = Integer.parseInt(myObj.nextLine());  // Read user input

        if(escolha==1) {
            GetTodosLugares();
        }
        if(escolha==2){
            GetTodosTickets();
        }
        if(escolha==3){
            PostTeste();
        }
        if(escolha==4){
            PostOutroTeste();
        }
    }


    public static ObservableList<objparking> GetTodosLugares(){

        try {
            URL url = new URL(" https://services.inapa.com/parking4hotel/api/park/");
            String encoding = Base64.getEncoder().encodeToString(("EG2:SJ$pEgYO(Y").getBytes("UTF-8"));

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setDoOutput(true);
            connection.setRequestProperty("Authorization", "Basic " + encoding);
            InputStream content = (InputStream) connection.getInputStream();
            BufferedReader in =
                    new BufferedReader(new InputStreamReader(content));
            String line;
            String line2="";
            while ((line = in.readLine()) != null) {
                line2 += line;
            }
            //Passar o JSON encontrado para objetos com nomes iguais
            GETparking gp = gson.fromJson(line2, GETparking.class);

            //Colocar tudo em um ObservableList a ser usado no F_Reserva.java
            int i=0;
            for (i=0; i < gp.Parking.size(); i++) {
                obsPark.add(new objparking(gp.getParking().get(i).ParkingSpot, gp.getParking().get(i).Price,
                        gp.getParking().get(i).Indoor, gp.getParking().get(i).Occupied));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return obsPark;
    }

    public static void GetTodosTickets(){
        try {
            URL url = new URL("https://services.inapa.com/parking4hotel/api/park/");
            String encoding = Base64.getEncoder().encodeToString(("EG2:SJ$pEgYO(Y").getBytes("UTF-8"));

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setDoOutput(true);
            connection.setRequestProperty("Authorization", "Basic " + encoding);
            InputStream content = (InputStream) connection.getInputStream();
            BufferedReader in = new BufferedReader(new InputStreamReader(content));

            String line;
            while ((line = in.readLine()) != null) {
                System.out.println(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public static  void PostTeste() {

        try {
            URL url = new URL("https://services.inapa.com/parking4hotel/api/ticket/");
            String encoding = Base64.getEncoder().encodeToString(("EG2:SJ$pEgYO(Y").getBytes("UTF-8"));
            String postData = "{\n" +
                    "    \"ClientId\": \"teste\",\n" +
                    "    \"LicencePlate\": \"Rocket\",\n" +
                    "    \"StartDate\": \"2023-01-01 15:00\",\n" +
                    "    \"EndDate\": \"2023-01-02 15:00\",\n" +
                    "    \"ParkingSpot\": \"P08\"\n" +
                    "}";
            //informaçao tipo json que ira ser enviada nao vai ser preciso parsar o ficheiro


            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST"); // PUT is another valid option
            connection.setDoOutput(true);
            connection.setRequestProperty("Authorization", "Basic " + encoding);
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Content-Length", Integer.toString(postData.length()));
            connection.setUseCaches(false);

            try (DataOutputStream dos = new DataOutputStream((connection.getOutputStream()))) {
                dos.writeBytes(postData);
            }
            try (BufferedReader br = new BufferedReader(new InputStreamReader(
                    connection.getInputStream())))
            {
                String line;
                while ((line = br.readLine()) != null) {
                    System.out.println(line);
                }
            }


        }  catch (Exception e) {
            e.printStackTrace();
        }

    }



    public static void PostOutroTeste(){
        try {
            URL url = new URL("https://services.inapa.com/parking4hotel/api/park/");
            String encoding = Base64.getEncoder().encodeToString(("EG2:SJ$pEgYO(Y").getBytes("UTF-8"));

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.setRequestProperty("Authorization", "Basic " + encoding);
            InputStream content = (InputStream) connection.getInputStream();
            BufferedReader in =
                    new BufferedReader(new InputStreamReader(content));
            String line;
            while ((line = in.readLine()) != null) {
                System.out.println(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
