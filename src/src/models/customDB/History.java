package models.customDB;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import models.Client;
import models.Taxi;


public class History {
    @SerializedName("nameTaxi")
    @Expose
    private String nameTaxi;
    @SerializedName("car")
    @Expose
    private String car;
    @SerializedName("number")
    @Expose
    private String number;
    @SerializedName("nameClient")
    @Expose
    private String nameClient;
    @SerializedName("cost")
    @Expose
    private int cost;


    public History(Taxi taxi, Client client){
        this.car = taxi.getCar();
        this.nameTaxi = taxi.getName();
        this.number = taxi.getNumber();
        this.nameClient =client.getName();
        this.cost = client.getCostOfRide();
    }

    public String getNameTaxi() {
        return nameTaxi;
    }

    public void setNameTaxi(String nameTaxi) {
        this.nameTaxi = nameTaxi;
    }

    public String getCar() {
        return car;
    }

    public void setCar(String car) {
        this.car = car;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getNameClient() {
        return nameClient;
    }

    public void setNameClient(String nameClient) {
        this.nameClient = nameClient;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }
}
