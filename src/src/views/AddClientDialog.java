package views;

import models.City;
import models.Route;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Васили on 22.12.2016.
 */
public class AddClientDialog extends JDialog  {

    public interface Listener2{
        void buttonPressed2(int startid, int finishid, String name);
    }

    Listener2 listener;
//String name, String telephone, int lacation, int destination
    public AddClientDialog(JFrame owner){
        super(owner, "Добавление клиента",true);
        listener = (Listener2) owner;


       int  length = City.getInstance().connections.size();   // размер структуры
        String [] items = new String[length];
        for (int i=0; i < length; i++) {
            items[i]= new String(String.valueOf(i+1));
        }

        JComboBox comboBox = new JComboBox(items);
        comboBox.setVisible(true);

        JComboBox comboBox2 = new JComboBox(items);
        comboBox2.setVisible(true);
        JTextField name =  new JTextField(10);
        JButton OkButton = new JButton("Ok");
        setLayout(new GridLayout(2,1));
        add(comboBox);
        add(comboBox2);
        add(name);
        add(OkButton);
        setSize(250,150);

        setLocationRelativeTo(owner);
        OkButton.addActionListener(e->{
            int start = (comboBox.getSelectedIndex());
            ///System.out.println(start);
           // int start = Integer.parseInt(location.getText());
            int finish = comboBox2.getSelectedIndex();
            String name1 = name.getText();
            listener.buttonPressed2(start,finish,name1);
            setVisible(false);
        });
    }
}
