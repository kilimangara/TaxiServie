package models;

import java.util.ArrayList;

public class Client {
    private String name;

    private String telephone;

    private int lacation;

    private int destination;

    private boolean tooLongWaiting;

    public Client(String name, int lacation, int destination) {
        this.name = name;
        this.lacation = lacation;
        this.destination = destination;
        this.tooLongWaiting =false;
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

}
