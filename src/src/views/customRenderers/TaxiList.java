package views.customRenderers;

import models.Taxi;

import javax.swing.*;
import java.awt.*;

/**
 * Created by nikitazlain on 05.01.17.
 */
public class TaxiList extends JPanel implements ListCellRenderer<Taxi> {
    private JLabel name;
    private JLabel car;
    private JLabel number;
    private JSeparator separator;
    private JCheckBox checkBox;

    public TaxiList(){
        checkBox = new JCheckBox();
        name = new JLabel();
        car = new JLabel();
        number = new JLabel();
        separator = new JSeparator(SwingConstants.HORIZONTAL);
        setLayout(new BorderLayout());
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3,0));
        panel.add(name);
        panel.add(car);
        panel.add(number);
        add(panel, BorderLayout.CENTER);
        add(checkBox, BorderLayout.EAST);
        add(separator, BorderLayout.SOUTH);
    }

    @Override
    public Component getListCellRendererComponent(JList<? extends Taxi> list, Taxi value, int index, boolean isSelected, boolean cellHasFocus) {
        name.setText("Name:"+value.getName());
        car.setText("Car:"+value.getCar());
        number.setText("Number:"+value.getNumber());
        checkBox.setOpaque(true);
        name.setOpaque(true);
        car.setOpaque(true);
        number.setOpaque(true);
        separator.setOpaque(true);
        name.setBackground(list.getBackground());
        car.setBackground(list.getBackground());
        number.setBackground(list.getBackground());
        separator.setBackground(list.getBackground());
        checkBox.setBackground(list.getBackground());
        setBackground(list.getBackground());
        if(isSelected){
            checkBox.setSelected(true);
        } else {
            checkBox.setSelected(false);
        }
        if(cellHasFocus){
            System.out.println("cell "+index+" has focus");
        }
        return  this;
    }
}
