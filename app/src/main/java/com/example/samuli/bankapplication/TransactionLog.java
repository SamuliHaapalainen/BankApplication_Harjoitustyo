package com.example.samuli.bankapplication;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

public class TransactionLog {
    public static final String xmlFilePath = "C:\\Users\\Samuli\\Documents\\Koulu\\Olio-ohjelmointi\\files\\xmlfile.xml";
    public static void main(String argv[]){
        try{
            DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();

            Document document = documentBuilder.newDocument();
            Element root = document.createElement("Transaction");
            document.appendChild(root);

            Element money = document.createElement("Money");
            root.appendChild(money);

            Element transType = document.createElement("Tranaction type");
            root.appendChild(transType);

            Element accoFrom = document.createElement("Invoiced account");
            root.appendChild(accoFrom);

            Element accoTo = document.createElement("Deposited account");
            root.appendChild(accoFrom);

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();

            DOMSource domSource = new DOMSource(document);
            StreamResult streamResult = new StreamResult(new File(xmlFilePath));

            transformer.transform(domSource,streamResult);

            System.out.println("XML file creation done successfully");

        }catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        }catch(TransformerException tfe) {
            tfe.printStackTrace();
        }
    }

}
