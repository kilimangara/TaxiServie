package views;

import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.view.mxGraph;
import models.City;
import models.Client;
import models.Taxi;
import views.dialogs.TaxiInfoDialog;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class CityGraph extends mxGraphComponent  {

    public static final int LEFT=0;
    public static final int RIGHT=1;
    public static final int TOP=2;
    public static final int BOTTOM=3;

    private int offsetY=0;
    private int offsetX=0;

    private Icon image;
    private Image imageCl1;
    private Image imageCl2;

    private Map<Taxi, RotatedIcon> map;
    private Map<Client, ImageIcon> mapClient;
    private Timer timer;
    //private JFrame context;

    private JScrollBar horizontal;

    private   JScrollBar vertical;

    private boolean flag;


    public CityGraph(mxGraph graph, JFrame context){
        super(graph);
        loadImage();
        map = new HashMap<>();
        mapClient= new HashMap<>();
        horizontal = getHorizontalScrollBar();
        vertical = getVerticalScrollBar();
        repaint();
        timer = new Timer(0, e -> repaint());
        this.getGraphControl().addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();
              //  System.out.println("x "+x+" y "+y);
                for(Taxi taxi:City.getInstance().getTaxis()){
                   flag=false;
                    if((Math.abs(taxi.getX()-x)<50)&&(Math.abs(taxi.getY()-y)<70)&&(!flag)){
                        JDialog taxiDialog = new TaxiInfoDialog(context,taxi);
                        taxiDialog.setVisible(true);
                        flag=true;
                    }
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
    }
    private void loadImage(){
        try {
            Image  image1 = ImageIO.read(new File("ic_marker_driver.png"));
            image = new ImageIcon(image1);

            imageCl1 = ImageIO.read(new File("Client1.png"));

            imageCl2 = ImageIO.read(new File("Client2.png"));

        } catch (IOException e) {
            System.out.println(e.getLocalizedMessage());
        }
    }
    private void rotateImage(RotatedIcon icon,int direction){
        switch (direction){
            case LEFT:
                icon.setDegrees(270);
                repaint();
                break;
            case RIGHT:
                icon.setDegrees(90);
                repaint();
                break;
            case TOP:
                icon.setDegrees(0);
                repaint();
                break;
            case BOTTOM:
                icon.setDegrees(180);
                repaint();
                break;
        }

    }

    @Override
    protected void paintComponent(Graphics g) {
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.CYAN);

        if(timer!=null){
            offsetX = horizontal.getValue();
            offsetY = vertical.getValue();
            for(Taxi taxi:City.getInstance().getTaxis()){
                RotatedIcon rotatedIcon1= map.get(taxi);
                if(rotatedIcon1 == null){
                    rotatedIcon1 = new RotatedIcon(image,0);
                    map.put(taxi, rotatedIcon1);
                }
                if(taxi.isRouteSet()||taxi.isRouteToClient()) {
                    if (!taxi.nextPoint()) {
                        rotateImage(rotatedIcon1, taxi.getDirection());
                        rotatedIcon1.paintIcon(this, g, taxi.getX()-offsetX , taxi.getY()-offsetY );
                        taxi.nextStep();
                    }
                }
            }

            for(Client client:City.getInstance().getClients()){
                if(!client.isInCar) {
                    ImageIcon imageIcon = mapClient.get(client);
                    if (imageIcon == null) {
                        imageIcon = new ImageIcon(imageCl2);
                        mapClient.put(client, imageIcon);
                    }
                    if (client.isTooLongWaiting()) {
                        imageIcon = new ImageIcon(imageCl1);
                        mapClient.put(client, imageIcon);
                    } else {
                        imageIcon = new ImageIcon(imageCl2);
                        mapClient.put(client, imageIcon);
                    }

                    imageIcon.paintIcon(this, g, City.getInstance().getXMasPoint(client.getLacation()) - offsetX,
                            City.getInstance().getYMasPoint(client.getLacation()) - offsetY);
                }
            }


        }

    }
    public void start(){
        timer.start();
    }



}
