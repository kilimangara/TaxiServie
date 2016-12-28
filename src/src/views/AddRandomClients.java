package views;

import controllers.Controller;
import models.City;
import models.Client;

import javax.swing.*;
import java.util.Random;

/**
 * Created by Васили on 29.12.2016.
 */
public class AddRandomClients  {
    public static void AddRandomClients(){
        //super(owner, "Добавление рандомных клиентов",true);

        Random random = new Random();
        int start1,finish1;
        start1 = random.nextInt(City.getInstance().vertexCount-1)+1;
        if(start1==20){
            start1 = random.nextInt(City.getInstance().vertexCount-1)+1;
        }
        finish1= random.nextInt(City.getInstance().vertexCount-1)+1;
        if(finish1 == start1){
            finish1= random.nextInt(City.getInstance().vertexCount-1)+1;
        }
        Client client = new Client("name",start1,finish1);
        Controller.addClientToList(client);

       /* start1 = random.nextInt(City.getInstance().vertexCount-1)+1;
        finish1= random.nextInt(City.getInstance().vertexCount-1)+1;
        client = new Client("name",start1,finish1);
        Controller.addClientToList(client);

        start1 = random.nextInt(City.getInstance().vertexCount-1)+1;
        finish1= random.nextInt(City.getInstance().vertexCount-1)+1;
        client = new Client("name",start1,finish1);
        Controller.addClientToList(client);

        start1 = random.nextInt(City.getInstance().vertexCount-1)+1;
        finish1= random.nextInt(City.getInstance().vertexCount-1)+1;
        client = new Client("name",start1,finish1);
        Controller.addClientToList(client);

        start1 = random.nextInt(City.getInstance().vertexCount-1)+1;
        finish1= random.nextInt(City.getInstance().vertexCount-1)+1;
        client = new Client("name",start1,finish1);
        Controller.addClientToList(client);
*/
    }


}
