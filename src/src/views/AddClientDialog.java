package views;

import controllers.Controller;
import models.City;
import models.Client;
import models.Route;

import javax.swing.*;
import java.awt.*;
import java.util.Random;


public class AddClientDialog extends JDialog  {

    public AddClientDialog(JFrame owner){
        super(owner, "Добавление клиента",true);

       int  length = City.getInstance().connections.size();
        String [] items = new String[length];
        for (int i=0; i < length; i++) {
            items[i]= String.valueOf(i + 1);
        }
        JLabel from = new JLabel("From: ");
        JComboBox<String> comboBox = new JComboBox<>(items);
        comboBox.setVisible(true);
        JLabel to = new JLabel("Destination");
        JComboBox<String> comboBox2 = new JComboBox<>(items);
        comboBox2.setVisible(true);
        JLabel name = new JLabel("Name");
        JTextField edName =  new JTextField(10);
        JButton OkButton = new JButton("Ok");
        setLayout(new GridLayout(4,2));
        add(from);
        add(comboBox);
        add(to);
        add(comboBox2);
        add(name);
        add(edName);
        add(OkButton);
        setSize(250,150);



        setLocationRelativeTo(owner);
        OkButton.addActionListener(e->{
            int start = Integer.parseInt(comboBox.getItemAt(comboBox.getSelectedIndex()));
            int finish = Integer.parseInt(comboBox2.getItemAt(comboBox2.getSelectedIndex()));
            String name1 = name.getText();

            Random random = new Random();


            if(City.getInstance().getTaxis().size()!=0) {
                for(int i = 0; i < 5; i++) {

                    System.out.println(City.getInstance().vertexCount-1);
                            int start1,finish1;
                            start1 = random.nextInt(City.getInstance().vertexCount-1)+1;
                            finish1= random.nextInt(City.getInstance().vertexCount-1)+1;
                            Client client = new Client("name",start1,finish1);
                    System.out.println(start1);
                    System.out.println(finish1);
                            Controller.addClientToList(client);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    }
                }

                setVisible(false);
                /*Client client = new Client(name1, start, finish);
                Controller.addClientToList(client);
                setVisible(false);*/
            }

        });
    }
}
