package views;

import javafx.scene.effect.Light;
import models.City;
import models.Taxi;

import javax.swing.*;

/**
 * Created by Васили on 03.01.2017.
 */
public class TaxiList extends JDialog{
    public TaxiList(JFrame owner){
        super(owner, "Список такси",true);
        String[] columnName = {
                "Name","Car","Number","Client1","Client2","Client3","Client4" };
        JList<String> top = new JList<String>(columnName);
        top.setLayoutOrientation(JList.HORIZONTAL_WRAP);
        // top.setLayoutOrientation(JList.VERTICAL_WRAP);
        top.setVisibleRowCount(1);
        top.setLocation(10,0);
        top.setSize(150,35);
        add(top);
        for (int i=1;i<City.getInstance().getTaxis().size()+1;i++) {
            String[][] data = new String[City.getInstance().getTaxis().size()+1][6];
            data[i][0]=City.getInstance().getTaxis().get(i-1).getName();
            data[i][1]=City.getInstance().getTaxis().get(i-1).getCar();
            data[i][2]=City.getInstance().getTaxis().get(i-1).getNumber();
            if (City.getInstance().getTaxis().get(i-1).getClients().size()>0) {
                data[i][3]=City.getInstance().getTaxis().get(i-1).getClients().get(0).getName();}
            if (City.getInstance().getTaxis().get(i-1).getClients().size()>1) {
                data[i][4]=City.getInstance().getTaxis().get(i-1).getClients().get(1).getName();}
            if (City.getInstance().getTaxis().get(i-1).getClients().size()>2) {
                data[i][5]=City.getInstance().getTaxis().get(i-1).getClients().get(2).getName();}
            if (City.getInstance().getTaxis().get(i-1).getClients().size()>3) {
                data[i][6]=City.getInstance().getTaxis().get(i-1).getClients().get(3).getName();}

            JList<String> record = new JList<String>(data[i]);
            record.setLayoutOrientation(JList.HORIZONTAL_WRAP);
            record.setVisibleRowCount(1);
            record.setLocation(10,40);
            record.setSize(150,35);
            record.setVisible(true);
            add(record);
        }


        setSize(800,600);
        setLocationRelativeTo(owner);

    }
}
