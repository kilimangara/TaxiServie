package models;

import java.util.ArrayList;

public class City {
    int[][] matr;
    ArrayList<Structure> structures;
    ArrayList<Taxi> taxis;

    public City(String path){

        extractFromXML(path);

    }

    private void extractFromXML(String path) {


    }


}
