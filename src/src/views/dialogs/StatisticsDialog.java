package views.dialogs;

import models.customDB.DBHelper;
import models.customDB.History;
import models.customDB.Utils;
import views.customRenderers.StatisticsModel;

import javax.swing.*;
import java.awt.*;


public class StatisticsDialog extends JDialog {


    public StatisticsDialog(Frame owner) {
        super(owner,"Статистика поездок");
        init();
        setLocationRelativeTo(owner);
    }


    public void init(){
        setSize(350, 250);
        JPanel panel = new JPanel(new GridBagLayout());
        JTabbedPane tabbedPane1 = new JTabbedPane();
        JPanel panel1 = new JPanel(new BorderLayout(1,1));
        panel1.setSize(150,50);
        JPanel panel2 = new JPanel(new GridLayout(2,0));
        DefaultListModel<History> listModel = new DefaultListModel<>();
        DBHelper.getInstance().getActualHistories().forEach(listModel::addElement);
        JList<History> list = new JList<>(listModel);
        list.setCellRenderer(new StatisticsModel());
        panel1.add(new JScrollPane(list), BorderLayout.CENTER);

        JLabel first = new JLabel("За время работы программной системы: " + Utils.formatDate(DBHelper.getInstance().getInfo().getTime()));
        JLabel second = new JLabel("Было обслужено: "+ DBHelper.getInstance().getActualHistories().size()+" клиентов");
        JButton button = new JButton("Очистить статистику");
        button.addActionListener(e -> {
            DBHelper.getInstance().getActualHistories().clear();
            listModel.clear();
            DBHelper.getInstance().getInfo().setTime(0);
            first.setText("За время работы программной системы: " + Utils.formatDate(DBHelper.getInstance().getInfo().getTime()));
            second.setText("Было обслужено: "+ DBHelper.getInstance().getActualHistories().size()+" клиентов");
        });
        panel2.add(first);
        panel2.add(second);
        tabbedPane1.add(panel1, "Список клиентов");
        tabbedPane1.add(panel2, "Общая информация");
        panel.add(tabbedPane1, new GridBagConstraints(0,0,10,10,5,5,GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(0,0,0,0),0,0));
        panel.add(button, new GridBagConstraints(5,10,1,1,1,1,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0,0,0,0),0,0));
        add(panel);
        setVisible(true);
    }
}
