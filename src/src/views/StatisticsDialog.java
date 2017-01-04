package views;

import models.customDB.DBHelper;
import models.customDB.History;

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
        DefaultListModel<History> listModel = new DefaultListModel<>();
        DBHelper.getInstance().getActualHistories().forEach(listModel::addElement);
        JList<History> list = new JList<>(listModel);
        add(list);
        setVisible(true);
    }
}