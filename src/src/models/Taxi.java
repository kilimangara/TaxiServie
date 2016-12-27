package models;


import controllers.Controller;
import views.CityGraph;

import javax.swing.*;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Taxi {
    private static final int MAX_CAR_PLACES=4;
    public static final int ID_OF_CAR_PLACE=20;

    private String name;

    private String car;

    private String number;

    private ArrayList<Client> clients;

    private boolean isStopped;

    private Route route;

    public Client activeClient;

    private boolean isRouteSet;

    private boolean isRouteToClient=false;

    private int direction;

    public boolean goHome ;

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

    public String getName() {
        return name;
    }

    public String getCar() {
        return car;
    }

    public String getNumber() {
        return number;
    }

    public boolean isRouteSet() {
        return isRouteSet;
    }

    public boolean isRouteToClient(){
        return isRouteToClient;
    }
    public void setRouteToClient(boolean flag){
        isRouteToClient = flag;
    }

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

    public void stopTaxi(){
        if(!isStopped) {
            isStopped = true;
            new Timer(2500,e -> isStopped=false).start();
        }
    }
    public boolean nextPoint(){

        if(!lastPoint()) {
            if ((x == City.getInstance().getXMasPoint(route.getNextPoint())) &&
                    (y == City.getInstance().getYMasPoint(route.getNextPoint()))) {
                route.switchPosition();
                position = route.getCurrentPoint();
                checkDirection();
            }
            return false;
        } else {
            if(activeClient!=null){
                stopTaxi();
                setRoute(activeClient.getLacation(), activeClient.getDestination());
                pickClient(activeClient);
                activeClient.isInCar =true;
                activeClient = null;
                return false;
            } else {
                if(getPosition()==ID_OF_CAR_PLACE){
                    this.isRouteSet= false;
                    route = null;
                    goHome = false;
                    return false;
                } else {
                    stopTaxi();
                    setRoute(getPosition(), ID_OF_CAR_PLACE);
                    goHome = true;
                    clients.get(0).isInCar=false;
                    clients.get(0).setLacation(clients.get(0).getDestination());
                    //Controller.deleteClientFromList(clients.get(0));
                    Timer timer = new Timer(3000, e->{if(clients.size()!=0){
                        Controller.deleteClientFromList(clients.get(0));
                        clients.remove(0);}});
                    timer.start();
                    if(clients.size()==0){
                        timer.stop();
                    }
                    return false;
                }
            }
        }
    }

    public int getPosition() {
        return position;
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
        this.clients = new ArrayList<>();
        this.isRouteSet=true;
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

    public Taxi(String name, String car, String number){
        this.name = name;
        this.car = car;
        this.number = number;
        clients = new ArrayList<>();
        this.isStopped = false;
        this.isRouteSet =false;
        goHome =false;
        this.position = ID_OF_CAR_PLACE;
    }
    public void pickClient(Client client){
        if(clients.size()<4){
            clients.add(client);
        }

    }
    public void setRoute(int start, int end){
        this.route= new Route(start, end);
        this.x =City.getInstance().getXMasPoint(route.getCurrentPoint());
        this.y = City.getInstance().getYMasPoint(route.getCurrentPoint());
        this.position = route.getCurrentPoint();
        checkDirection();
        this.isRouteSet=true;
    }



}
