package models;

import java.util.ArrayList;

public class Client {
    String name;
    String telephone;
    int lacation;
    int destination;

    public Client(String name, int lacation, int destination) {
        this.name = name;
        this.telephone = telephone;
        this.lacation = lacation;
        this.destination = destination;
        callTaxi();
    }

    private void callTaxi() {
        //making an order for taxi
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public int getLacation() {
        return lacation;
    }

    public void setLacation(int lacation) {
        this.lacation = lacation;
    }

    public int getDestination() {
        return destination;
    }

    public void setDestination(int destination) {
        this.destination = destination;
    }

    public static class Taxi {
        private static final int MAX_CAR_PLACES=4;
        private String name;
        private String car;
        private String number;
        private int peopleInside;
        private ArrayList<Client> clients;
        private int route;
        private boolean freePlaces;
        private int position;






    }
}
