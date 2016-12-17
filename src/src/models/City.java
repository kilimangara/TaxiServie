package models;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;

public class City {
    private static City instance;
    private int[][] matr;	//Матрица смежности
    private ArrayList<Structure> structures;
    private ArrayList<Taxi> taxis;
    public int vertexCount; //Количество узлов

    public int[][] getMatr() {
        return matr;
    }

    public void setMatr(int[][] matr) {
        this.matr = matr;
    }

    public ArrayList<Structure> getStructures() {
        return structures;
    }

    public void setStructures(ArrayList<Structure> structures) {
        this.structures = structures;
    }

    public ArrayList<Taxi> getTaxis() {
        return taxis;
    }

    public void setTaxis(ArrayList<Taxi> taxis) {
        this.taxis = taxis;
    }

    private int arcCount;	//Количество дуг
    public static City getInstance(){
        if(instance ==  null){
            instance = new City();
        }
        return instance;

    }

    public void init(String path, String path2){
        getXML(path, path2);

    }
    private City(){
        this.structures = new ArrayList<>();
        this.taxis = new ArrayList<>();

    }
    public void reinit(String path, String path2){
       this.structures = new ArrayList<>();
        this.taxis = new ArrayList<>();
        getXML(path, path2);
    }


    private void getXML(String path, String path2){

        try {

            // Создается построитель документа
            DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            // Создается дерево DOM документа из файла
            Document document = documentBuilder.parse(path);
            // Получаем корневой элемент
            Node root = document.getDocumentElement();
//
  //          System.out.println("City's structura:");
    //        System.out.println();



            // Просматриваем все подэлементы корневого - т.е. книги
            NodeList books = root.getChildNodes(); // все объекты типа структура
            for (int i = 1; i < books.getLength();i++) {
                Node node = books.item(i);
                i=i+1;
                if(node.getAttributes().getNamedItem("type").getNodeValue().compareTo("0")==0){
                    // building
                    Integer number=0,x=0,y=0;
                    String street;
                    street= node.getAttributes().getNamedItem("street").getNodeValue();
                    number= new Integer(node.getAttributes().getNamedItem("number").getNodeValue());
                    x= new Integer(node.getAttributes().getNamedItem("x").getNodeValue() );
                    y= new Integer(node.getAttributes().getNamedItem("y").getNodeValue());
                    this.structures.add(new Building(street,number,x,y));
                    // System.out.println(node.getAttributes().getNamedItem("type").getNodeValue());
                    // System.out.println(node.getAttributes().getNamedItem("street").getNodeValue());
                    // System.out.println(node.getAttributes().getNamedItem("number").getNodeValue());
                    //  System.out.println(node.getAttributes().getNamedItem("x").getNodeValue());
                    //  System.out.println(node.getAttributes().getNamedItem("y").getNodeValue());
                }
                else{
                    // Crossroad
                    String firststring,secondstring;
                    Integer x,y;
                    firststring=node.getAttributes().getNamedItem("firststreet").getNodeValue();
                    secondstring=node.getAttributes().getNamedItem("secondstreet").getNodeValue();
                    x=new Integer(node.getAttributes().getNamedItem("x").getNodeValue());
                    y=new Integer(node.getAttributes().getNamedItem("y").getNodeValue());
                    this.structures.add(new Crossroad(firststring,secondstring,x,y));
                    // System.out.println(node.getAttributes().getNamedItem("type").getNodeValue());
                    // System.out.println(node.getAttributes().getNamedItem("firststreet").getNodeValue());
                    // System.out.println(node.getAttributes().getNamedItem("secondstreet").getNodeValue());
                     //System.out.println(node.getAttributes().getNamedItem("x").getNodeValue());
                    //System.out.println(node.getAttributes().getNamedItem("y").getNodeValue());
                }
               // System.out.println("");
            }

        } catch (ParserConfigurationException | SAXException | IOException ex) {
            ex.printStackTrace(System.out);
        }
        // парсинг xml matr
        try {
            DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            // Создается дерево DOM документа из файла
            Document document = documentBuilder.parse(path2);
            // Получаем корневой элемент
            Node root = document.getDocumentElement();
            System.out.println("Matrix structura:");
            System.out.println();
            this.matr= new int[100][100];
            NodeList spisok = root.getChildNodes();
            int k=0;
            for (int i = 1; i < spisok.getLength();i++){
                NodeList end = spisok.item(i).getChildNodes();
                i++;
                k++;
                int o=0;

                for(int j=1; j < end.getLength();j++){
                    Node hod=end.item(j);
                    j++;
                    if (hod.getNodeType() != Node.TEXT_NODE) {
                        Integer volume =new Integer(hod.getChildNodes().item(0).getTextContent());
                        System.out.println(hod.getChildNodes().item(0).getTextContent());
                        o++;
                        this.matr[k-1][o-1]=volume;}
                }
            }
        } catch (ParserConfigurationException | SAXException | IOException ex) {
            ex.printStackTrace(System.out);
        }


    }
}