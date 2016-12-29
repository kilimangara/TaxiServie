package models;


import controllers.Controller;
import views.CityGraph;

import javax.sound.midi.Soundbank;
import javax.swing.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Taxi implements Serializable{
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
    public List<Client> getClients(){
        return clients;
    }
    public String getClientsString(){

        String clients=" ";
        for(Client client:this.clients){
            if(client.isInCar){
                clients= clients+client.getName()+" ";
            }
        }
        return clients;
    }
    public void checkDirection(){
        direction = route.getDirection();
    }

    private boolean lastPoint(){
        if(route != null) {
            return route.getNextPoint() == -1;
        } else {
            return true;
        }

    }

    public void stopTaxi(){
        if(!isStopped) {
            isStopped = true;
            new Timer(2000,e -> isStopped=false).start();
        }
    }
    public boolean nextPoint(){  // true - нет следующе  точки

        if(!lastPoint()) {
            if ((x == City.getInstance().getXMasPoint(route.getNextPoint())) &&
                    (y == City.getInstance().getYMasPoint(route.getNextPoint()))) {
                route.switchPosition();
                position = route.getCurrentPoint();
                if(activeClient!=null){
                    reSetRoute(activeClient, getPosition());
                    return false;
                }
                if(clients.size()>1) {
                    for(int i=1;i< clients.size();++i) {
                        Client client =clients.get(i);
                        if (getPosition() == client.getLacation()) {
                            stopTaxi();
                            client.isInCar = true;
                            isRouteToClient = false;
                            continue;
                        }
                        if (getPosition() == client.getDestination()) {
                            stopTaxi();
                            client.isInCar = false;
                            client.setLacation(client.getDestination());
                            clients.remove(client);
                            Executors.newSingleThreadScheduledExecutor().schedule(() -> {
                                Controller.deleteClientFromList(client);
                                System.out.println("deleting client "+client);
                            }, 3, TimeUnit.SECONDS);
                        }
                    }
                }
                checkDirection();
            }
            return false;
        } else {
            if(clients.size()==1){
                    Client client = clients.get(0);
                    if (activeClient != null) {
                     //   System.out.println("in activeClient");
                        int pos = ID_OF_CAR_PLACE;
                        if (getPosition() != ID_OF_CAR_PLACE) {
                            pos = route.getNextPoint();
                        }
                        reSetRoute(activeClient, pos);
                        return false;
                    }
                    // System.out.println("Taxi position "+getPosition()+" client position "+client.getLacation()+" client dest "+client.getDestination());
                    if (getPosition() == client.getLacation()) {
                      //  System.out.println("in clientLocation");
                        stopTaxi();
                        setRoute(client.getLacation(), client.getDestination());
                        client.isInCar = true;
                        isRouteToClient = false;
                        return false;
                    }
                    if (getPosition() == client.getDestination()) {
                        //System.out.println("in destination");
                        stopTaxi();
                        client.isInCar = false;
                        client.setLacation(client.getDestination());
                        clients.remove(client);
                        Executors.newSingleThreadScheduledExecutor().schedule(() -> {
                            Controller.deleteClientFromList(client);
                            System.out.println("deleting client "+client);
                        }, 3,  TimeUnit.SECONDS);
                        goHome = true;
                        System.out.println("setting route home "+getName()+" at time wgen it was on "+getPosition()+"and on route "+route.route);
                        setRoute(getPosition(), ID_OF_CAR_PLACE);
                        return false;
                    }
                    if (getPosition() == ID_OF_CAR_PLACE) {
                        //System.out.println("in at home");
                        this.isRouteSet = false;
                        route = null;
                        goHome = false;
                        return false;
                    }
                return false;
            }
            return true;
        }
    }

    public boolean checkRoute(Client client){
        if(route == null){
            return false;
        }
       int begin= route.route.indexOf(client.getLacation());
        int end = route.route.indexOf(client.getDestination());
        return end != -1 && begin != -1 && end > begin;
    }

    public void reSetRoute(Client client, int pos){

        /*int pos = ID_OF_CAR_PLACE;
        if(getPosition()!=ID_OF_CAR_PLACE){
            pos = route.getNextPoint();
        }*/
        setRoute(pos,client.getLacation());
        activeClient = null;
        goHome=false;
        isRouteToClient = false;

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
    public boolean pickClient(Client client){
        if(clients.size()==0){
            clients.add(client);
            activeClient = client;
            setRouteToClient(true);
            goHome=false;
            System.out.println("Client picked "+client.getLacation()+" "+client.getDestination()+" by "+this.getName());
            return true;
        }
        if(clients.size()<4){
            clients.add(client);
            System.out.println("Client onroad "+client.getLacation()+" "+client.getDestination()+" by "+this.getName()+" route "+this.route.route);
            return true;
        }
        return false;

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
