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
        setLayout(new GridLayout(3,1));
        add(car);
        add(number);
        add(name);
        setSize(350, 100);
        setLocationRelativeTo(owner);
       // setVisible(true);

    }
}
