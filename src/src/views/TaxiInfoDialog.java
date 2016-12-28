package views;

import models.Taxi;

import javax.swing.*;
import java.awt.*;


public class TaxiInfoDialog extends JDialog {

    public TaxiInfoDialog(JFrame owner, Taxi taxi){

        super(owner,"Taxi Info", true);
        JLabel car = new JLabel("Car: "+taxi.getCar());
        JLabel number = new JLabel("number: "+taxi.getNumber());
        JLabel name = new JLabel("driver:"+taxi.getName());
        JLabel clients = new JLabel("clients:"+taxi.getClients());
        setLayout(new GridLayout(4,1));
        add(car);
        add(number);
        add(name);
        add(clients);
        setSize(350, 100);
        setLocationRelativeTo(owner);
       // setVisible(true);

    }
}
