package views.dialogs;

import controllers.Controller;
import models.Route;
import models.Taxi;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Васили on 22.12.2016.
 */
public class AddTaxiDialog extends JDialog {


    public AddTaxiDialog(JFrame owner){
        super(owner, "Добавление такси",true);
        JLabel name = new JLabel("Name");
        JTextField edName =  new JTextField(18);
        JLabel number = new JLabel("number");
        JTextField edNumber =  new JTextField(18);
        JLabel car = new JLabel("car");
        JTextField edcar =  new JTextField(18);
        JButton OkButton = new JButton("Ok");
        setLayout(new GridLayout(4,2));
        add(name);
        add(edName);
        add(number);
        add(edNumber);
        add(car);
        add(edcar);
        add(OkButton);
        setSize(250,150);

        setLocationRelativeTo(owner);
        OkButton.addActionListener(e->{
            Taxi taxi = new Taxi(edName.getText(), edcar.getText(), edNumber.getText());
            Controller.addTaxiToList(taxi);
            setVisible(false);
        });
    }
    public AddTaxiDialog(JFrame owner, Taxi taxi){

        super(owner, "Редактирование такси",true);
        JLabel name = new JLabel("Name");
        JTextField edName =  new JTextField(18);
        edName.setText(taxi.getName());
        JLabel number = new JLabel("number");
        JTextField edNumber =  new JTextField(18);
        JLabel car = new JLabel("car");
        edNumber.setText(taxi.getNumber());
        JTextField edcar =  new JTextField(18);
        edcar.setText(taxi.getCar());
        JButton OkButton = new JButton("Ok");
        setLayout(new GridLayout(4,2));
        add(name);
        add(edName);
        add(number);
        add(edNumber);
        add(car);
        add(edcar);
        add(OkButton);
        setSize(250,150);

        setLocationRelativeTo(owner);
        OkButton.addActionListener(e->{
            taxi.setName(edName.getText());
            taxi.setCar(edcar.getText());
            taxi.setNumber(edNumber.getText());
            setVisible(false);
        });
    }


}
