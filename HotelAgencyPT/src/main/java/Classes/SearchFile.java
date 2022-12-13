package Classes;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SearchFile {
    public static List<String> SearchDB() {
        List<String> list = new ArrayList<String>();
        try {
            File file = new File(System.getProperty("user.dir") + "\\BaseDados.txt");
            Scanner br = new Scanner(file);

            while (br.hasNextLine()) {
                String str = br.nextLine();
                String[] array = str.split("-->");
                list.add(array[1]);
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }
}

