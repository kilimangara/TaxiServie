package views;

import models.Route;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Васили on 22.12.2016.
 */
public class AddClientDialog extends JDialog  {

    public interface Listener2{
        void buttonPressed2(int startid, int finishid, String name, String number);


    }

    Listener2 listener;
//String name, String telephone, int lacation, int destination
    public AddClientDialog(JFrame owner){
        super(owner, "Добавление клиента",true);
        listener = (Listener2) owner;
        JTextField location =  new JTextField(2);
        JTextField destination =  new JTextField(2);
        JTextField name =  new JTextField(10);
        JTextField telephone =  new JTextField(10);
        JButton OkButton = new JButton("Ok");
        setLayout(new GridLayout(2,1));
        add(location);
        add(destination);
        add(name);
        add(telephone);
        add(OkButton);
        setSize(250,150);

        setLocationRelativeTo(owner);
        OkButton.addActionListener(e->{
            int start = Integer.parseInt(location.getText());
            int finish = Integer.parseInt(destination.getText());
            String name1 = name.getText();
            String number1 = telephone.getText();
            listener.buttonPressed2(start,finish,name1,number1);
            setVisible(false);
        });
    }
}
