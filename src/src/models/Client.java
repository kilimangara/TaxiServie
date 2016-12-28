package models;

import javax.swing.*;
import java.util.ArrayList;

public class Client {
    private String name;

    public boolean isInCar;

    public boolean hasDriver;

    private int lacation;

    private int destination;


    private boolean tooLongWaiting;

    public boolean isTooLongWaiting() {
        return tooLongWaiting;
    }

    public Client(String name, int lacation, int destination) {
        this.name = name;
        this.lacation = lacation;
        this.destination = destination;
        this.tooLongWaiting =false;
        new Timer(17000,e-> {
            if (!hasDriver) {
                tooLongWaiting = true;
            }
        }).start();
        this.isInCar = false;
        this.hasDriver =false;
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
