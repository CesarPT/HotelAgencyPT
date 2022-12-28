package hotel.agencypt.Controller;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Arrays;

/**
 * Imports para serem usados pelo JSON
 * Utilizado: GSON (builde.gradle), BufferedReader e FileReader
 * <a href="https://howtodoinjava.com/gson/gson-jsonparser/">...</a>
 * <a href="https://stackoverflow.com/questions/8233542/parse-a-nested-json-using-gson">...</a>
 */
public class JSONpath {

    String[] code, descricao;
    float[] tipoqtd, qtd;
    float[] preco;
    String[] VAT;
    float[] precototal;
    String lineNumber;

    /**
     * Ler ficheiro JSON com GSON
     *
     * @param path
     */
    public void Lerjson(Path path) {
        try {
            String header;
            StringBuilder sb = new StringBuilder();

            //Ler o ficheiro JSON
            BufferedReader br = new BufferedReader(new FileReader(path.toFile()));
            //Ler o header
            while ((header = br.readLine()) != null) {
                sb.append(header);
            }
            System.out.println("JSON file: " + sb.toString());
            //Parse do GSON para o JSON
            JsonObject jsonObject = new Gson().fromJson(sb.toString(), JsonObject.class);

            //FORNECEDOR

            //OrderNumber
            String ordernumber = jsonObject.getAsJsonObject("Order").getAsJsonObject("Header").get("OrderNumber").getAsString();
            //OrderDate
            String orderdate = jsonObject.getAsJsonObject("Order").getAsJsonObject("Header").get("OrderDate").getAsString();
            //SupplierParty
            String supplierparty = jsonObject.getAsJsonObject("Order").getAsJsonObject("Header").getAsJsonObject("SupplierParty").get("PartyIdentifier").getAsString();
            //NameAddress - Name
            String name = jsonObject.getAsJsonObject("Order").getAsJsonObject("Header").getAsJsonObject("SupplierParty").getAsJsonObject("NameAddress").get("Name").getAsString();
            //NameAddress - Address
            String address1 = jsonObject.getAsJsonObject("Order").getAsJsonObject("Header").getAsJsonObject("SupplierParty").getAsJsonObject("NameAddress").get("Address1").getAsString();
            //NameAddress - City
            String city = jsonObject.getAsJsonObject("Order").getAsJsonObject("Header").getAsJsonObject("SupplierParty").getAsJsonObject("NameAddress").get("City").getAsString();
            //NameAddress - PostalCode
            String postalcode = jsonObject.getAsJsonObject("Order").getAsJsonObject("Header").getAsJsonObject("SupplierParty").getAsJsonObject("NameAddress").get("PostalCode").getAsString();
            //NameAddress" - Country - ISOCountryCode
            String isocountrycode = jsonObject.getAsJsonObject("Order").getAsJsonObject("Header").getAsJsonObject("SupplierParty").getAsJsonObject("NameAddress").getAsJsonObject("Country").get("ISOCountryCode").getAsString();
            //NameAddress - Country - Value
            String country = jsonObject.getAsJsonObject("Order").getAsJsonObject("Header").getAsJsonObject("SupplierParty").getAsJsonObject("NameAddress").getAsJsonObject("Country").get("Value").getAsString();

            System.out.println("####################");
            System.out.println("Order Number: " + ordernumber);
            System.out.println("Order Date: " + orderdate);
            System.out.println("Supplier Party: " + supplierparty);
            System.out.println("######ADDRESS#######");
            System.out.println("Name: " + name);
            System.out.println("Address1: " + address1);
            System.out.println("City: " + city);
            System.out.println("Postal Code: " + postalcode);
            System.out.println("######COUNTRY#######");
            System.out.println("ISO Country Code: " + isocountrycode);
            System.out.println("Country: " + country);
            System.out.println("#####################");

            //LINES

            JsonArray linenumber = jsonObject.getAsJsonObject("Order").getAsJsonArray("Line");
            JsonArray productidentifier = jsonObject.getAsJsonObject("Order").getAsJsonArray("Line");

            //Em cada line percorre tudo o que está dentro
            for (int i = 0; i < linenumber.size(); i++) {
                JsonArray pr = productidentifier.get(i).getAsJsonObject().getAsJsonObject("Product").getAsJsonArray("ProductIdentifier");
                lineNumber = linenumber.get(i).getAsJsonObject().get("LineNumber").getAsString(); //Getting the value of the key "Product"
                JsonObject pdescription = productidentifier.get(i).getAsJsonObject().getAsJsonObject("Product");

                //Em cade Product Identifier percorre tudo o que está dentro
                for (int j = 0; j < pr.size(); j++) {

                    JsonObject line = linenumber.get(i).getAsJsonObject();
                    JsonArray line1 = line.get("InformationalQuantity").getAsJsonArray();
                    JsonObject pidentifier = pr.get(j).getAsJsonObject();
                    code = new String[]{pidentifier.get("Code").getAsString()};
                    descricao = new String[]{pdescription.get("ProductDescription").getAsString()};
                    tipoqtd = new float[]{Float.parseFloat(line1.get(j).getAsJsonObject().get("Value").getAsJsonObject().get("UOM").getAsString())};
                    qtd = new float[]{Float.parseFloat(line.get("Quantity").getAsJsonObject().get("Value").getAsJsonObject().get("Value").getAsString())};
                    preco = new float[]{Float.parseFloat(line.get("MonetaryAdjustment").getAsJsonObject().get("MonetaryAdjustmentStartAmount").getAsJsonObject().get("CurrencyValue").getAsJsonObject().get("Value").getAsString())};
                    VAT = new String[]{line.get("MonetaryAdjustment").getAsJsonObject().get("TaxAdjustment").getAsJsonObject().get("TaxPercent").getAsString()};
                    precototal = new float[]{Float.parseFloat(line.get("LineBaseAmount").getAsJsonObject().get("CurrencyValue").getAsJsonObject().get("Value").getAsString())};

                    System.out.println("##### LINE " + lineNumber + " #####");
                    System.out.println("Product Identifier: " + Arrays.toString(code));
                    System.out.println("Product Description: " + Arrays.toString(descricao));
                    System.out.println("Kg (tipo_qtd): " + Arrays.toString(tipoqtd));
                    System.out.println("Qtd: " + Arrays.toString(qtd));
                    System.out.println("Preço: " + Arrays.toString(preco));
                    System.out.println("VAT: " + Arrays.toString(VAT));
                    System.out.println("Preço total: " + Arrays.toString(precototal));
                    System.out.println("##############################");
                }
            }
        } catch (IOException e) {
            System.out.println("[Erro] LerJSON - Buffers | " + e.getMessage());
        }

    }

    /*
    public void confirmarJSON() {
        //Converter para string e inserir na BD
        int quantprod;
        String idprod;
        String descprod;
        String tipo_qtd;
        float preco;
        float vat;
        float preco_total;

        boolean teste = false;
        for (int i = 0; i < line.getLength(); i++) {
            idprod = [i];
            descprod = pd[i];
            quantprod = qt[i];
            tipo_qtd = iq[i];
            preco = cv[i];
            vat = taxp[i];
            preco_total = lba[i];

            teste = stockDAO.IFfindItem(idprod);

            if (teste == true) {
                StockDAO.updateStock(idprod, quantprod);
            } else {
                StockDAO.insertNewStock(idprod, descprod, tipo_qtd, quantprod, preco, vat, preco_total);
            }
        }
    }
     */
}