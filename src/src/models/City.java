package models;

import java.util.ArrayList;

public class City {
    static int[][] matrDist;	//Матрица расстояний
    static int[][] matrEdge;	//Матрица ребер
    static ArrayList<Structure> structures;
    ArrayList<Taxi> taxis;
    static int vertexCount; //Количество узлов
    static int arcCount;	//Количество дуг

    public City(String path){

        extractFromXML(path);

    }

    private void extractFromXML(String path) {


    }


}
