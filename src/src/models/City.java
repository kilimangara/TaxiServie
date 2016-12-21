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
import java.util.LinkedList;

public class City {
    private static City instance;
    // private int[][] matr;	//Матрица смежности
    private ArrayList structures;
    private ArrayList<Taxi> taxis;
    public ArrayList<LinkedList<Integer>> connections;
    public int vertexCount=10; //Количество узлов
    private   int[][] masPoint = new int[2][vertexCount];// x,y,idPoint

    public int getXMasPoint(int n) {  // getter x for idPoint= n
        return masPoint[1][n];
    }

    public int getYMasPoint(int n) {  // getter y for idPoint= n
        return masPoint[2][n];
    }

    public ArrayList getStructures() {
        return structures;
    }

    public void setStructures(ArrayList structures) {
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
        this.connections = new ArrayList<>();
        for(int i=0; i<10;++i){
            connections.add(new LinkedList());
        }
        System.out.println("length "+connections.size());

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

            NodeList books = root.getChildNodes(); // все объекты типа структура
            for (int i = 1; i < books.getLength();i++) {
                Node node = books.item(i);
                i=i+1;

                Integer id = 0, x = 0, y = 0;
                id = new Integer(node.getAttributes().getNamedItem("id").getNodeValue());
                x = new Integer(node.getAttributes().getNamedItem("x").getNodeValue());
                y = new Integer(node.getAttributes().getNamedItem("y").getNodeValue());
                System.out.println("id "+id+" x "+x+" y "+y );
                //this.structures.add(new Point(id,x,y));
                this.masPoint[0][id]=id;
                this.masPoint[1][id]=x;
                this.masPoint[2][id]=y;
                NodeList relates = node.getChildNodes();
                Node node1 = relates.item(1);
                for(int ii=1;ii<5;++ii) {
                    if(!node1.getAttributes().getNamedItem("id"+ii).getNodeValue().equals("0")) {
                        Integer id1 = new Integer(node1.getAttributes().getNamedItem("id" + ii).getNodeValue());
                        System.out.println("id " + id1);
                        connections.get(id - 1).add(id1);
                    }
                }
            }

        } catch (ParserConfigurationException | SAXException | IOException ex) {
            ex.printStackTrace(System.out);
        }


    }
}