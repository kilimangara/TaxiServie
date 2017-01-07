package views.dialogs;

import models.City;
import models.customDB.DBHelper;
import models.customDB.History;
import views.MainPanel;
import views.customRenderers.StatisticsModel;

import javax.swing.*;
import java.awt.*;
import java.util.Date;


public class StatisticsDialog extends JDialog {


    public StatisticsDialog(Frame owner) {
        super(owner,"Статистика поездок");
        init();
        setLocationRelativeTo(owner);
    }


    public void init(){
        setSize(550, 250);
        JTabbedPane tabbedPane1 = new JTabbedPane();
        JPanel panel1 = new JPanel();
        panel1.setSize(350,250);
        JPanel panel2 = new JPanel();
        JLabel volume;
        DefaultListModel<History> listModel = new DefaultListModel<>();
        DBHelper.getInstance().getActualHistories().forEach(listModel::addElement);
        JList<History> list = new JList<>(listModel);
        list.setCellRenderer(new StatisticsModel());
        panel1.add(new JScrollPane(list));
        Date current = new Date();
        String time = new String("За время работы программной системы: "+(current.getTime()- MainPanel.date.getTime())/(60*1000)+ " минут " + (current.getTime()-
                MainPanel.date.getTime())/(1000) + " \r" +

                "Было обслужено "+ City.getInstance().getCount() + " клиентов");
        volume = new JLabel(time);

        panel2.add(volume);
        tabbedPane1.add(panel1, "Список клиентов");
        tabbedPane1.add(panel2, "Общая информация");
        add(tabbedPane1);
        setVisible(true);
    }
}
