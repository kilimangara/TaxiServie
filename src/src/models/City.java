package models;

import java.util.ArrayList;

public class City {
    static int[][] matrDist;	//Матрица расстояний
    static int[][] matrEdge;	//Матрица ребер
    static ArrayList<Structure> structures;
    ArrayList<Taxi> taxis;
    static int vertexCount; //Количество узлов
    static int arcCount;	//Количество дуг

    public City(String path, String path2){
        this.structures=new ArrayList<>();
        try {

            // Создается построитель документа
            DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            // Создается дерево DOM документа из файла
            Document document = documentBuilder.parse(path);
            // Получаем корневой элемент
            Node root = document.getDocumentElement();

            System.out.println("City's structura:");
            System.out.println();



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
                    y=new Integer(node.getAttributes().getNamedItem("x").getNodeValue());
                    this.structures.add(new Crossroad(firststring,secondstring,x,y));
                    // System.out.println(node.getAttributes().getNamedItem("type").getNodeValue());
                    // System.out.println(node.getAttributes().getNamedItem("firststreet").getNodeValue());
                    // System.out.println(node.getAttributes().getNamedItem("secondstreet").getNodeValue());
                    // System.out.println(node.getAttributes().getNamedItem("x").getNodeValue());
                    //System.out.println(node.getAttributes().getNamedItem("y").getNodeValue());
                }
                System.out.println("");
            }

        } catch (ParserConfigurationException ex) {
            ex.printStackTrace(System.out);
        } catch (SAXException ex) {
            ex.printStackTrace(System.out);
        } catch (IOException ex) {
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
            this.matrDist= new int[100][100];
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
                        this.matrDist[k-1][o-1]=volume;}
                }
            }
        } catch (ParserConfigurationException ex) {
            ex.printStackTrace(System.out);
        } catch (SAXException ex) {
            ex.printStackTrace(System.out);
        } catch (IOException ex) {
            ex.printStackTrace(System.out);
        }
    }
}