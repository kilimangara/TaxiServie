package models;

import com.sun.security.ntlm.Client;
import controllers.Controller;
import views.CityGraph;

import java.util.ArrayList;

public class Taxi {
    private static final int MAX_CAR_PLACES=4;

    private String name;

    private String car;

    private String number;

    private ArrayList<Client> clients;

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
            }
            return false;
        } else {
            return true;
        }
    }
    public void nextStep(){
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
        System.out.println("DIRECTION "+direction +" x "+x+" y "+y);


    }
    public Taxi(String name, String car, String number, Route route){
        this.name = name;
        this.car = car;
        this.number = number;
        //this.position = position;
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
