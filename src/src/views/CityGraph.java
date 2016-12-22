package views;

import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.view.mxGraph;
import controllers.Controller;
import models.City;
import models.Client;
import models.Route;
import models.Taxi;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;



public class CityGraph extends mxGraphComponent {

    public static final int LEFT=0;
    public static final int RIGHT=1;
    public static final int TOP=2;
    public static final int BOTTOM=3;

    private Icon image;

    private RotatedIcon rotatedIcon;

    private Map<Taxi, RotatedIcon> map;

    private Timer timer;

    private boolean flag;
    int beginx=MainPanel.DEFAULT_SIZE;
    int beginy = MainPanel.DEFAULT_SIZE;

    public CityGraph(mxGraph graph){
        super(graph);
        loadImage();
        timer = new Timer(100, e -> repaint());
        flag = false;
        //rotatedIcon = new RotatedIcon(image,0);
       // rotateImage(RIGHT);
        map = new HashMap<>();
        Route route = new Route();
        route.route.add(1);
        route.route.add(2);
        route.route.add(5);
        route.route.add(6);
        route.route.add(9);
        City.getInstance().getTaxis().add(new Taxi(1, route));
        repaint();
    }
    private void loadImage(){
        try {
          Image  image1 = ImageIO.read(new File("ic_marker_driver.png"));
            image = new ImageIcon(image1);
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
        super.paintComponent(g);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.CYAN);

        if(timer!=null){
            for(Taxi taxi:City.getInstance().getTaxis()){
                RotatedIcon rotatedIcon1= map.get(taxi);
                if(rotatedIcon1 == null){
                    rotatedIcon1 = new RotatedIcon(image,0);
                    map.put(taxi, rotatedIcon1);
                }
                if(!taxi.nextPoint()){
                    rotateImage(rotatedIcon1, taxi.getDirection());
                    rotatedIcon1.paintIcon(this,g, taxi.getX(), taxi.getY());
                    taxi.nextStep();

                } else {
                    map.remove(taxi);
                    Controller.deleteTaxiFromList(taxi);
                }

            }

        }

    }
    public void start(){
        timer.start();
    }
}