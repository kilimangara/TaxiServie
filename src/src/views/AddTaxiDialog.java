package views;

import models.Route;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Васили on 22.12.2016.
 */
public class AddTaxiDialog extends JDialog {

    public interface Listener{
        void buttonPressed(Route route);
    }

    Listener listener;

    public AddTaxiDialog(JFrame owner){
        super(owner, "Добавление такси",true);
        listener = (Listener) owner;
        JTextField route =  new JTextField(18);
        JButton OkButton = new JButton("Ok");
        setLayout(new GridLayout(2,1));
        add(route);
        add(OkButton);
        setSize(250,150);

        setLocationRelativeTo(owner);
        OkButton.addActionListener(e->{
            String [] stringRoute = route.getText().split(",");
            Route route1 = new Route();
            for (String string : stringRoute) {
                route1.route.add(Integer.parseInt(string));
            }
            listener.buttonPressed(route1);
            setVisible(false);
        });
    }


}
