package views.dialogs;

import controllers.Controller;
import models.City;
import models.Taxi;
import views.customRenderers.TaxiList;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;


public class TaxiManagingDialog extends JDialog {
    private int[] selection;
    private JList<Taxi> list;
    public TaxiManagingDialog(JFrame owner){
        super(owner,"Информация о такси");
        init(owner);
        setLocationRelativeTo(owner);
    }
    private void init(JFrame owner){
        setSize(350,250);
        setLayout(new GridBagLayout());
        DefaultListModel<Taxi> listModel = new DefaultListModel<>();
        City.getInstance().getTaxis().forEach(listModel::addElement);
        list = new JList<>(listModel);
        list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        list.setCellRenderer(new TaxiList());
        JButton delete =new JButton("Удалить");
        delete.setEnabled(false);
        //delete.setBorderPainted(false);
        delete.setFocusPainted(false);
        delete.addActionListener(e -> {Controller.deleteTaxiFromList(selection);
            for(int index:selection){
                listModel.removeElementAt(index);
            }
            delete.setEnabled(false);
            delete.setFocusPainted(false);});
       JButton edit = new JButton("Редактировать");
        edit.setEnabled(false);
        //edit.setBorderPainted(false);
        edit.setFocusPainted(false);
        edit.addActionListener(e -> {
           JDialog dialog= new AddTaxiDialog(owner,City.getInstance().getTaxis().get(selection[0]));
            dialog.setVisible(true);
        });
        list.addListSelectionListener(e -> {
            System.out.println(Arrays.toString(list.getSelectedIndices()));

            selection = list.getSelectedIndices();
            if(list.getSelectedIndices().length==1){
                edit.setEnabled(true);
                edit.setFocusPainted(true);
            } else {
                edit.setEnabled(false);
                edit.setFocusPainted(false);
            }
            delete.setEnabled(true);
            delete.setFocusPainted(true);
        });
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(new JScrollPane(list), BorderLayout.CENTER);
        add(panel, new GridBagConstraints(0,0,10,10,4,4,GridBagConstraints.NORTH, GridBagConstraints.BOTH,new Insets(0,0,0,0),0,0));
        add(delete,new GridBagConstraints(2,10,1,1,1,1,GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0,0, 0), 0,0));
        add(edit,new GridBagConstraints(4,10,1,1,1,1,GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0,0, 0), 0,0));
        setVisible(true);
    }

    private void reinit(){
        DefaultListModel<Taxi> listModel = new DefaultListModel<>();
        City.getInstance().getTaxis().forEach(listModel::addElement);
        list.setModel(listModel);

    }
}
