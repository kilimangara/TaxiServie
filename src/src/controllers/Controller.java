package controllers;

import models.City;
import models.Taxi;

/**
 * Created by nikitazlain on 22.12.16.
 */
public class Controller {

    public static void deleteTaxiFromList(Taxi taxi){
        City.getInstance().getTaxis().remove(taxi);
    }
}
