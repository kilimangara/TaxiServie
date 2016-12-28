package controllers;

import models.City;
import models.Client;
import models.Taxi;

import javax.swing.*;

/**
 * Created by nikitazlain on 22.12.16.
 */
public class Controller {
    private final static int MAXCLIENTS=10;
    private final static int MAXTAXI=5;
    private  static Timer timer ;
    public static void deleteTaxiFromList(Taxi taxi){
        City.getInstance().getTaxis().remove(taxi);
    }

    public static void addTaxiToList(Taxi taxi){
        if(City.getInstance().getTaxis().size()<MAXTAXI) {
            City.getInstance().getTaxis().add(taxi);
        }
    }

    public static void addClientToList(Client client){
        if(City.getInstance().getClients().size()<MAXCLIENTS) {
            City.getInstance().getClients().add(client);
            setTaxiRouteToClient(client);
        }
    }

    public static void deleteClientFromList(Client client){
        City.getInstance().getClients().remove(client);

    }

    private static void setTaxiRouteToClient(Client client){
        timer = new Timer(1000, e -> {
            for (Taxi taxi : City.getInstance().getTaxis()) {
                System.out.println("in route "+taxi.isRouteSet()+" going home "+taxi.goHome+" route relates "+taxi.checkRoute(client));
                if(!client.hasDriver) {
                    if ((!taxi.isRouteSet()) || (taxi.goHome)) {
                        if(taxi.pickClient(client)) {
                            client.hasDriver = true;
                            checkIfFound(client);
                            break;
                        }
                    } else {
                        if (taxi.checkRoute(client)){
                            if(taxi.pickClient(client)) {
                                client.hasDriver = true;
                                checkIfFound(client);
                                break;
                            }
                        }
                    }
                }
            }
        });

        if((!client.hasDriver)) {
            timer.start();
            System.out.println("timer "+timer+" started");
        }
    }
    private static void checkIfFound(Client client){
        if (client.hasDriver){
            timer.stop();
            System.out.println("timer "+timer+" stopped");
        }
    }

}
