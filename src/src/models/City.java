package models;

import java.util.ArrayList;

public class City {
    static int[][] matr;	//Матрица смежности
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
