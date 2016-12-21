package models;

import com.sun.security.ntlm.Client;

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
    private int position;

    public Taxi(String name, String car, String number, int position){
        this.name = name;
        this.car = car;
        this.number = number;
        this.position = position;
        peopleInside = 0;
        route = route;
    }

    public void pickClient(Client client){
        if(clients.size()<4){
            clients.add(client);
        }

    }
    public void setRoute(int start, int end){
        this.route= new Route(start, end);



    }



}
