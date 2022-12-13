package Classes;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SearchFile {
    public static List<String> SearchDB(){
        List<String> list = new ArrayList<String>();
            try{
            File file = new File("src/main/java/DataBase/BaseDados.txt");
            Scanner br = new Scanner(file);

            while (br.hasNextLine()){
                list.add(br.nextLine());
            }
            br.close();
            }catch (IOException e){
                e.printStackTrace();
            }
        return list;
    }
}

