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
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;


public class City {
    private static City instance;
    private List<Taxi> taxis;
    private List<Client> clients;
    public  ArrayList<LinkedList<Integer>> connections;
    public  int vertexCount=77;          //Количество узлов
    public  int arcCount=76;	         //Количество дуг
    public  int[][] masPoint ;           // x,y,idPoint

    private Date startDate = new Date();
    private int count;

    public Date getStartDate() {
        return startDate;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getCount() {
        return count;
    }

    public int getXMasPoint(int n) {  // getter x for idPoint= n
        return masPoint[0][n];
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }

    public int getYMasPoint(int n) {  // getter y for idPoint= n
        return masPoint[1][n];
    }

    public List<Taxi> getTaxis() {
        return taxis;
    }

    public List<Client> getClients() {
        return clients;
    }

    public void setTaxis(List<Taxi> taxis) {
        this.taxis = taxis;
    }


    public static City getInstance(){
        if(instance ==  null){
            instance = new City();
        }
        return instance;

    }

    public void init(String path){
        getXML(path);

    }
    private City(){
        masPoint = new int[2][vertexCount];
        this.taxis = new LinkedList<>();
        this.clients = new LinkedList<>();
        this.connections = new ArrayList<>();
        for(int i=0; i<vertexCount;++i){
            connections.add(new LinkedList<>());
        }
    }

    private void getXML(String path){

        try {

            // Создается построитель документа
            DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            // Создается дерево DOM документа из файла
            Document document = documentBuilder.parse(path);
            // Получаем корневой элемент
            Node root = document.getDocumentElement();

            NodeList books = root.getChildNodes(); // все объекты типа структура
            System.out.println("LENGTH: "+books.getLength());
            for (int i = 1; i < books.getLength();i++) {
                Node node = books.item(i);
                i=i+1;

                Integer id = 0, x = 0, y = 0;
                id = new Integer(node.getAttributes().getNamedItem("id").getNodeValue());
                x = new Integer(node.getAttributes().getNamedItem("x").getNodeValue());
                y = new Integer(node.getAttributes().getNamedItem("y").getNodeValue());
                this.masPoint[0][id]=x;
                this.masPoint[1][id]=y;
                NodeList relates = node.getChildNodes();
                Node node1 = relates.item(1);
                for(int ii=1;ii<5;++ii) {
                    if(!node1.getAttributes().getNamedItem("id"+ii).getNodeValue().equals("0")) {
                        Integer id1 = new Integer(node1.getAttributes().getNamedItem("id" + ii).getNodeValue());
                        connections.get(id - 1).add(id1);
                    }
                }
            }

        } catch (ParserConfigurationException | SAXException | IOException ex) {
            ex.printStackTrace(System.out);
        }


    }
}