package views.dialogs;

import controllers.Controller;
import models.City;
import models.Client;

import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


public class AddRandomClients  {
    public static void AddRandomClients(){
        //super(owner, "Добавление рандомных клиентов",true);
         int[] starts = {1, 33 ,25 , 19, 45, 10, 8, 69, 55};
        int[] finishs = {30, 26, 15, 11, 72, 59, 75, 29, 47 };
        Random random = new Random();
        int i=0;
        Executors.newSingleThreadScheduledExecutor().schedule(()->{
            Client client = new Client("client "+1,starts[0],finishs[0]);
            Controller.addClientToList(client);
            Client client1 = new Client("client "+2,starts[1],finishs[1]);
            Controller.addClientToList(client1);
            Client client2 = new Client("client "+3,starts[2],finishs[2]);
            Controller.addClientToList(client2);
        },1, TimeUnit.SECONDS);

        Executors.newSingleThreadScheduledExecutor().schedule(()->{
            Client client = new Client("client "+4,starts[3],finishs[3]);
            Controller.addClientToList(client);
            Client client1 = new Client("client "+5,starts[4],finishs[4]);
            Controller.addClientToList(client1);
            Client client2 = new Client("client "+6,starts[5],finishs[5]);
            Controller.addClientToList(client2);
        },14, TimeUnit.SECONDS);
        Executors.newSingleThreadScheduledExecutor().schedule(()->{
            Client client = new Client("client "+7,starts[6],finishs[6]);
            Controller.addClientToList(client);
            Client client1 = new Client("client "+8,starts[7],finishs[7]);
            Controller.addClientToList(client1);
            Client client2 = new Client("client "+9,starts[8],finishs[8]);
            Controller.addClientToList(client2);
        },20, TimeUnit.SECONDS);

    }
}
