package views.dialogs;

import controllers.Controller;
import models.City;
import models.Client;

import javax.swing.*;
import java.awt.*;


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
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3,2));
        setLayout(new BorderLayout());
        panel.add(from);
        panel.add(comboBox);
        panel.add(to);
        panel.add(comboBox2);
        panel.add(name);
        panel.add(edName);
        add(panel, BorderLayout.NORTH);
        add(OkButton,BorderLayout.AFTER_LAST_LINE);
        setSize(250,150);



        setLocationRelativeTo(owner);
        OkButton.addActionListener(e->{
            int start = Integer.parseInt(comboBox.getItemAt(comboBox.getSelectedIndex()));
            int finish = Integer.parseInt(comboBox2.getItemAt(comboBox2.getSelectedIndex()));
            String name1 = name.getText();

            if(start!=finish) {
                Client client = new Client(name1, start, finish);
                Controller.addClientToList(client);
            }
            else {
                JOptionPane.showMessageDialog(null,"Невозможно создать клиента с большой жопой","Eror",JOptionPane.ERROR_MESSAGE);
            }
                setVisible(false);
        });
    }
}
