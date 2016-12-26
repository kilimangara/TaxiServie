package models;

import com.sun.security.ntlm.Client;
import controllers.Controller;
import views.CityGraph;

import javax.swing.*;
import java.util.ArrayList;

public class Taxi {
    private static final int MAX_CAR_PLACES=4;

    private String name;

    private String car;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    private String number;

    private ArrayList<Client> clients;

    private boolean isStopped;

    private Route route;

    private int direction;

    private boolean freePlaces;
    /**
     * id of Point
     */
    private int position;
    /**
     * current position x, y
     */
    private int x;
    private int y;

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getDirection(){
        return direction;
    }
    public void checkDirection(){
        direction = route.getDirection();
    }

    private boolean lastPoint(){
        return route.getNextPoint() == -1;

    }
    public boolean nextPoint(){

        if(!lastPoint()) {
            if ((x == City.getInstance().getXMasPoint(route.getNextPoint())) &&
                    (y == City.getInstance().getYMasPoint(route.getNextPoint()))) {
                route.switchPosition();
                checkDirection();
                if(!isStopped) {
                    isStopped = true;
                }
                new Timer(3000,e -> isStopped=false).start();
            }
            return false;
        } else {
            return true;
        }
    }
    public void nextStep(){
        if(isStopped){
           return;
        }
        switch (direction) {
            case CityGraph.BOTTOM:
                y++;
                break;
            case CityGraph.LEFT:
                x--;
                break;
            case CityGraph.RIGHT:
                x++;
                break;
            case CityGraph.TOP:
                y--;
                break;
        }
    }
    public Taxi(String name, String car, String number, Route route){
        this.name = name;
        this.car = car;
        this.number = number;
        //this.position = position;
        this.isStopped = false;
        this.route = route;
        this.x =City.getInstance().getXMasPoint(route.getCurrentPoint());
        this.y = City.getInstance().getYMasPoint(route.getCurrentPoint());
        this.position = route.getCurrentPoint();
        checkDirection();
    }
    public Taxi( Route route){
        this("A","B","C", route);
    }

    public void pickClient(Client client){
        if(clients.size()<4){
            clients.add(client);
        }

    }
    public void setRoute(int start, int end){
        //this.route= new Route(start, end);



    }



}
