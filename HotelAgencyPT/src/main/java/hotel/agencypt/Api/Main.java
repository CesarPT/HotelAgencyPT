package hotel.agencypt.Api;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner myObj = new Scanner(System.in);  // Create a Scanner object

        System.out.println("1 para todos os lugares, \n" +
                "2 para todos os tickets, \n");
        int escolha = Integer.parseInt(myObj.nextLine());  // Read user input

        if(escolha==1) {
            GetTodosLugares();
        }
        if(escolha==2){
            GetTodosTickets();
        }

    }


    public static void GetTodosLugares(){
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
            while ((line = in.readLine()) != null) {
                System.out.println(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void GetTodosTickets(){
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
            while ((line = in.readLine()) != null) {
                System.out.println(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
