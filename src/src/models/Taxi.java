package models;

import java.util.ArrayList;

public class Taxi {
    private static final int MAX_CAR_PLACES=4;
    private String name;
    private String car;
    private String number;
    private int peopleInside;
    private ArrayList<Client> clients;
    private Route route;
    private boolean freePlaces;
    private Structure position;

    public Taxi(String name, String car, String number, Structure position){
        this.name = name;
        this.car = car;
        this.number = number;
        this.position = position;
        peopleInside = 0;
        route = null;
    }

    public void pickClient(Client client){
        if(clients.size()<4){
            clients.add(client);
        }

    }
    public void setRoute(Structure start, Structure end){
        this.route= new Route(start, end);



    }



}
