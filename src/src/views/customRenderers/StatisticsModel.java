package views.customRenderers;

import models.customDB.History;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;


public class StatisticsModel extends JPanel implements ListCellRenderer<History> {
    private JLabel client;
    private JLabel taxi;
    private JLabel cost;
    private JLabel image;
    private JSeparator separator ;

    public StatisticsModel(){
        setLayout(new BorderLayout(5,5));
        client = new JLabel();
        taxi = new JLabel();
        cost = new JLabel();
        image = new JLabel();
        separator = new JSeparator(SwingConstants.HORIZONTAL);
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3,0));
        panel.add(client);
        panel.add(cost);
        panel.add(taxi);
        add(panel, BorderLayout.CENTER);
        add(separator, BorderLayout.SOUTH);
        add(image, BorderLayout.WEST);

    }

    @Override
    public Component getListCellRendererComponent(JList<? extends History> list, History value, int index, boolean isSelected, boolean cellHasFocus) {
        client.setText("Client:"+value.getNameClient());
        taxi.setText("Taxi:"+value.getNameTaxi());
        cost.setText("Cost:"+String.valueOf(value.getCost()));
        try {
            if (!value.isRate()) {
                image.setIcon(new ImageIcon(ImageIO.read(new File("Client2.png"))));
            } else {
                image.setIcon(new ImageIcon(ImageIO.read(new File("Client1.png"))));
            }
        }
        catch (IOException e){

        }
        setOpaque(true);
        client.setOpaque(true);
        taxi.setOpaque(true);
        cost.setOpaque(true);
        image.setOpaque(true);
        separator.setOpaque(true);
        client.setBackground(list.getBackground());
        taxi.setBackground(list.getBackground());
        cost.setBackground(list.getBackground());
        image.setBackground(list.getBackground());
        separator.setBackground(Color.BLACK);
        setBackground(list.getBackground());
        return this;
    }
}
