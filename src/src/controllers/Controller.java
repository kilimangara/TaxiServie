package controllers;

import models.City;
import models.Client;
import models.Taxi;

/**
 * Created by nikitazlain on 22.12.16.
 */
public class Controller {
    private final static int MAXCLIENTS=10;
    private final static int MAXTAXI=5;
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
        }
    }

    public static void deleteClientFromList(Client client){
        City.getInstance().getClients().remove(client);

    }

}
