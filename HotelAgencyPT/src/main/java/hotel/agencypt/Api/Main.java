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

        System.out.println("1 para todos os lugares, \n" +//primeito serviço
                "2 para todos os tickets, \n"+ //Sexto serviço
                "3 para insereir um ticket, \n"+ //segundo serviço
                "4 para buscar um ticket \n"+ //terceiro serviço
                "5 para apagar um ticket, \n"+ //quarto serviço
                "6 para mudar estado de ticket \n"); //quinto serviço


        int escolha = Integer.parseInt(myObj.nextLine());  // Read user input

        if(escolha==1) {
            GetTodosLugares();
        }
        if(escolha==2){
            GetTodosTickets();
        }
        if(escolha==3){
            PostTicket();
        }
        if(escolha==4){
            getUmTicket();
        }
        if(escolha==5){
            deleteUmTicket();
        }
        if(escolha==6){
            putUmTicket();
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



    public static  void PostTicket() {

        try {
            URL url = new URL("https://services.inapa.com/parking4hotel/api/ticket/");
            String encoding = Base64.getEncoder().encodeToString(("EG2:SJ$pEgYO(Y").getBytes("UTF-8"));
            String postData = "{\n" +
                    "    \"ClientId\": \"teste\",\n" +
                    "    \"LicencePlate\": \"Rocket\",\n" +
                    "    \"StartDate\": \"2023-01-01 15:00\",\n" +
                    "    \"EndDate\": \"2023-01-02 15:00\",\n" +
                    "    \"ParkingSpot\": \"P05\"\n" +
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



    public static void getUmTicket() {
        try {
            Scanner myObj = new Scanner(System.in);  // Create a Scanner object
            String test = "9b509d6b-b9ee-4a11-bac2-3506311cd15a";
            System.out.println("escolha um ticket expemplo" + test);
            String idticket = myObj.nextLine();  // Read user input


            URL url = new URL("https://services.inapa.com/parking4hotel/api/ticket/" + idticket);
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

    public static void deleteUmTicket() {
        try {
            String test = "edd51405-a739-456a-b038-661b66b0cea0";

            Scanner myObj = new Scanner(System.in);  // Create a Scanner object
            System.out.println("escolha um ticket expemplo" + test);
            String idticket = myObj.nextLine();  // Read user input


            URL url = new URL("https://services.inapa.com/parking4hotel/api/ticket/"+idticket);
            String encoding = Base64.getEncoder().encodeToString(("EG2:SJ$pEgYO(Y").getBytes("UTF-8"));

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("DELETE");
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


    public static void putUmTicket() {
        try {
            String test = "f6eccd09-a1ac-4270-a34c-de1c9fbad8a2";
/*
            Scanner myObj = new Scanner(System.in);  // Create a Scanner object
            System.out.println("escolha um ticket expemplo" + test);
            String idticket = myObj.nextLine();  // Read user input
 */
            URL url = new URL("https://services.inapa.com/parking4hotel/api/ticket/"+test);
            String encoding = Base64.getEncoder().encodeToString(("EG2:SJ$pEgYO(Y").getBytes("UTF-8"));
            String putDATA = "{\n" +
                    "    \"ClientId\": \"teste\",\n" +
                    "    \"LicencePlate\": \"Rocket\",\n" +
                    "    \"StartDate\": \"2023-01-01 15:00\",\n" +
                    "    \"EndDate\": \"2023-01-02 15:00\",\n" +
                    "    \"ParkingSpot\": \""+test+"\"\n" +
                    "}";

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setRequestMethod("PUT");

            connection.setRequestProperty("Authorization", "Basic " + encoding);
            connection.setRequestProperty("Content-Length", Integer.toString(putDATA.length()));

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


}
