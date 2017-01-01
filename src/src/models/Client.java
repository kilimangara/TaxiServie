package models;

import javax.swing.*;
import java.io.Serializable;

public class Client implements Serializable {
    private String name;

    public boolean isInCar;

    public boolean hasDriver;

    private int lacation;

    private int destination;

    private int costOfRide;

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
        this.costOfRide = 0;
    }

    private void callTaxi() {
        //making an order for taxi

    }

    public int getCostOfRide() {
        return costOfRide;
    }

    public void setCostOfRide(int costOfRide) {
        this.costOfRide = costOfRide;
    }

    public void incrementCost(){
        this.costOfRide++;
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

    @Override
    public String toString() {
        if(name==null){
            name = "Client";
        }
        return this.name;
    }
}
