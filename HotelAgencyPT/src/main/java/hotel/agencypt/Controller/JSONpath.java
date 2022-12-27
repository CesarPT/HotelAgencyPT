package hotel.agencypt.Controller;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.FileNotFoundException;
import java.io.FileReader;

import org.json.simple.parser.JSONParser;



public class JSONpath {
    static String filePath = "C:\\ISEP\\LP3\\grupo-2-lp3\\HotelAgencyPT\\src\\main\\java\\hotel\\agencypt\\Lindustrie Alimentaire.json";

    public static void lerJSON() throws JSONException, FileNotFoundException {
        try {
            FileReader reader = new FileReader(filePath);
            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = new JSONObject(reader);
            JSONArray jsonArray = jsonObject.getJSONArray("Order");
            System.out.println(jsonObject);

            for(int i = 0; i < jsonArray.length(); i++){
                int ordernumber = jsonArray.("OrderNumber");
            }
        }catch (Exception e) {
            System.out.println(e);
        }
        System.out.println(ordernumber);
    }
}