package hotel.agencypt.Controller;

import org.w3c.dom.Document;

import org.w3c.dom.NodeList;

import javax.xml.xpath.XPathConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;
import java.io.File;

public class XMLpath {

    public static void Lexml()throws Exception  {


        //documento builder para o XML
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true);
        DocumentBuilder builder = factory.newDocumentBuilder();

        //get Document
        Document document = builder.parse(new File("D:\\Escola\\LP3\\TesteXML\\src\\main\\java\\org\\example\\ProductHero.xml"));


        XPathFactory xpathfactory = XPathFactory.newInstance();
        XPath xpath = xpathfactory.newXPath();




        //print do XML na consola
        //ON=OrderNUmber
        //DY=Date-year
        //DM
        //DD
        //SP
        //---------------------HEADER----------------------------------
        //OrderNUmber
        XPathExpression expron = xpath.compile("//Header//OrderNumber/text()");
        Object resulton = expron.evaluate(document, XPathConstants.NODESET);
        NodeList nodeon = (NodeList) resulton;
        for (int i = 0; i < nodeon.getLength(); i++) {
            System.out.println(nodeon.item(i).getNodeValue());
        }

        //DATE
        XPathExpression exprdy = xpath.compile("//Header//OrderDate/Date/Year/text()");
        Object resultdy = exprdy.evaluate(document, XPathConstants.NODESET);
        NodeList nodesdy = (NodeList) resultdy;
        for (int i = 0; i < nodesdy.getLength(); i++) {
            System.out.println(nodesdy.item(i).getNodeValue());
        }

        XPathExpression exprdm = xpath.compile("//Header//OrderDate/Date/Month/text()");
        Object resultdm = exprdm.evaluate(document, XPathConstants.NODESET);
        NodeList nodesdm = (NodeList) resultdm;
        for (int i = 0; i < nodesdm.getLength(); i++) {
            System.out.println(nodesdm.item(i).getNodeValue());
        }

        XPathExpression exprdd = xpath.compile("//Header//OrderDate/Date/Day/text()");
        Object resultdd = exprdd.evaluate(document, XPathConstants.NODESET);
        NodeList nodesdd = (NodeList) resultdd;
        for (int i = 0; i < nodesdd.getLength(); i++) {
            System.out.println(nodesdd.item(i).getNodeValue());
        }

        //Suplier party
        XPathExpression exprsp = xpath.compile("//Header//SupplierParty/PartyIdentifier/text()");
        Object resultsp = exprsp.evaluate(document, XPathConstants.NODESET);
        NodeList nodessp = (NodeList) resultsp;
        for (int i = 0; i < nodessp.getLength(); i++) {
            System.out.println(nodessp.item(i).getNodeValue());
        }
        XPathExpression exprna = xpath.compile("//Header//SupplierParty/NameAddress/Name/text()");
        Object resultna = exprna.evaluate(document, XPathConstants.NODESET);
        NodeList nodesna = (NodeList) resultna;
        for (int i = 0; i < nodesna.getLength(); i++) {
            System.out.println(nodesna.item(i).getNodeValue());
        }
        XPathExpression exprad1 = xpath.compile("//Header//SupplierParty/NameAddress/Address1/text()");
        Object resultad1 = exprad1.evaluate(document, XPathConstants.NODESET);
        NodeList nodesad1 = (NodeList) resultad1;
        for (int i = 0; i < nodesad1.getLength(); i++) {
            System.out.println(nodesad1.item(i).getNodeValue());
        }
        XPathExpression exprad2 = xpath.compile("//Header//SupplierParty/NameAddress/Address2/text()");
        Object resultad2 = exprad2.evaluate(document, XPathConstants.NODESET);
        NodeList nodesad2 = (NodeList) resultad2;
        for (int i = 0; i < nodesad2.getLength(); i++) {
            System.out.println(nodesad2.item(i).getNodeValue());
        }
        XPathExpression exprct = xpath.compile("//Header//SupplierParty/NameAddress/City/text()");
        Object resultct = exprct.evaluate(document, XPathConstants.NODESET);
        NodeList nodesct = (NodeList) resultct;
        for (int i = 0; i < nodesct.getLength(); i++) {
            System.out.println(nodesct.item(i).getNodeValue());
        }
        XPathExpression exprps = xpath.compile("//Header//SupplierParty/NameAddress/PostalCode/text()");
        Object resultps = exprps.evaluate(document, XPathConstants.NODESET);
        NodeList nodesps = (NodeList) resultps;
        for (int i = 0; i < nodesps.getLength(); i++) {
            System.out.println(nodesps.item(i).getNodeValue());
        }
        XPathExpression exprcon = xpath.compile("//Header//SupplierParty/NameAddress/Country/text()");
        Object resultcon = exprcon.evaluate(document, XPathConstants.NODESET);
        NodeList nodescon = (NodeList) resultcon;
        for (int i = 0; i < nodescon.getLength(); i++) {
            System.out.println(nodescon.item(i).getNodeValue());
        }

        //lINE
        //product
        XPathExpression exprp = xpath.compile("//Line//Product/text()");
        Object resultp = exprp.evaluate(document, XPathConstants.NODESET);
        NodeList nodesp = (NodeList) resultp;
        for (int i = 0; i < nodesp.getLength(); i++) {
            System.out.println(nodesp.item(i).getNodeValue());
        }
        XPathExpression exprpi = xpath.compile("//Line//Product/ProductIdentifier/text()");
        Object resultpi = exprpi.evaluate(document, XPathConstants.NODESET);
        NodeList nodespi = (NodeList) resultpi;
        for (int i = 0; i < nodespi.getLength(); i++) {
            System.out.println(nodespi.item(i).getNodeValue());
        }
        XPathExpression exprpd = xpath.compile("//Line//Product/ProductDescription/text()");
        Object resultpd = exprpd.evaluate(document, XPathConstants.NODESET);
        NodeList nodespd = (NodeList) resultpd;
        for (int i = 0; i < nodespd.getLength(); i++) {
            System.out.println(nodespd.item(i).getNodeValue());
        }

        //Quantity
        XPathExpression exprq = xpath.compile("//Line//Quantity//text()");
        Object resultq = exprq.evaluate(document, XPathConstants.NODESET);
        NodeList nodesq = (NodeList) resultq;
        for (int i = 0; i < nodesq.getLength(); i++) {
            System.out.println(nodesq.item(i).getNodeValue());
        }

        //peso
        XPathExpression expriq = xpath.compile("//Line//InformationalQuantity//Value[@UOM='Kilogram']/text()");
        Object resultiq = expriq.evaluate(document, XPathConstants.NODESET);
        NodeList nodesiq = (NodeList) resultiq;
        for (int i = 0; i < nodesiq.getLength(); i++) {
            System.out.println(nodesiq.item(i).getNodeValue());
        }
        //Quantidade
        XPathExpression exprqt = xpath.compile("//Line//InformationalQuantity//Value[@UOM='Unit']/text()");
        Object resultqt = exprqt.evaluate(document, XPathConstants.NODESET);
        NodeList nodesqt = (NodeList) resultqt;
        for (int i = 0; i < nodesqt.getLength(); i++) {
            System.out.println(nodesqt.item(i).getNodeValue());
        }


        //preço unitario
        XPathExpression exprpu = xpath.compile("//Line//PricePerUnit//CurrencyValue/text()");
        Object resultpu = exprpu.evaluate(document, XPathConstants.NODESET);
        NodeList nodespu = (NodeList) resultpu;
        for (int i = 0; i < nodespu.getLength(); i++) {
            System.out.println(nodespu.item(i).getNodeValue());
        }
        //Taxas
        XPathExpression exprtax = xpath.compile("//Line//MonetaryAdjustment//MonetaryAdjustmentLine/text()");
        Object resulttax = exprtax.evaluate(document, XPathConstants.NODESET);
        NodeList nodestax = (NodeList) resulttax;
        for (int i = 0; i < nodestax.getLength(); i++) {
            System.out.println(nodestax.item(i).getNodeValue());
        }

        //CurrencyValue
        XPathExpression exprcv = xpath.compile("//Line//MonetaryAdjustment//MonetaryAdjustmentStartAmount/CurrencyValue/text()");
        Object resultcv = exprcv.evaluate(document, XPathConstants.NODESET);
        NodeList nodescv = (NodeList) resultcv;
        for (int i = 0; i < nodescv.getLength(); i++) {
            System.out.println(nodescv.item(i).getNodeValue());
        }


        //Tax percent
        XPathExpression exprtaxp= xpath.compile("//Line//TaxAdjustment//TaxPercent/text()");
        Object resulttaxp = exprtaxp.evaluate(document, XPathConstants.NODESET);
        NodeList nodestaxp = (NodeList) resulttaxp;
        for (int i = 0; i < nodestaxp.getLength(); i++) {
            System.out.println(nodestaxp.item(i).getNodeValue());
        }

        //Tax Amount
        XPathExpression exprtaxa= xpath.compile("//Line//TaxAdjustment//TaxAmount/CurrencyValue/text()");
        Object resulttaxa = exprtaxa.evaluate(document, XPathConstants.NODESET);
        NodeList nodestaxa = (NodeList) resulttaxa;
        for (int i = 0; i < nodestaxa.getLength(); i++) {
            System.out.println(nodestaxa.item(i).getNodeValue());
        }

        //Tax Locationm
        XPathExpression exprtaxl= xpath.compile("//Line//TaxAdjustment//TaxLocation/text()");
        Object resulttaxl = exprtaxl.evaluate(document, XPathConstants.NODESET);
        NodeList nodestaxl = (NodeList) resulttaxl;
        for (int i = 0; i < nodestaxl.getLength(); i++) {
            System.out.println(nodestaxl.item(i).getNodeValue());
        }

        //LineBaseAmount
        XPathExpression exprlba= xpath.compile("//Line//LineBaseAmount//CurrencyValue/text()");
        Object resultlba = exprlba.evaluate(document, XPathConstants.NODESET);
        NodeList nodeslba = (NodeList) resultlba;
        for (int i = 0; i < nodeslba.getLength(); i++) {
            System.out.println(nodeslba.item(i).getNodeValue());
        }

            /*
            try {
            //normalizar a estrutura XML
            document.getDocumentElement().normalize();


            //get de todos os elementos da pela tag name
            NodeList headerlist = document.getElementsByTagName("Header");

            for (int i = 0; i < headerlist.getLength(); i++) {
                Node nheader = headerlist.item(i);

                if (nheader.getNodeType() == Node.ELEMENT_NODE) {

                    Element hElement = (Element) nheader;
                    System.out.println("Teste" + headerlist);

                    NodeList productDetails = nheader.getChildNodes();

                    for (int j = 0; j < productDetails.getLength(); j++) {
                        Node details = productDetails.item(j);
                        if (details.getNodeType() == Node.ELEMENT_NODE) {
                            Element detailElement = (Element) details;
                            System.out.println("    " + detailElement.getTagName() + ":" + detailElement.getAttribute("ProductIdentifier"));
                        }
                    }
                }

            }

        } finally {

        }

             */
    }
}