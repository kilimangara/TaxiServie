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
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
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


    public CityGraph(mxGraph graph, JFrame context){
        super(graph);
        loadImage();
        map = new HashMap<>();
        mapClient= new HashMap<>();
        Route route = new Route(1,10);
        /*route.route.add(1);
        route.route.add(2);
        route.route.add(5);
        route.route.add(6);
        route.route.add(9);*/
        Route route1 = new Route(8,1);
       /* route1.route.add(8);
        //route.route.add();
        route1.route.add(5);
        route1.route.add(2);
        route1.route.add(1);*/
        City.getInstance().getTaxis().add(new Taxi("AHMED","BMW","228",route1));
        City.getInstance().getTaxis().add(new Taxi(route));
       // City.getInstance().getClients().add(new Client("Petrov",1,3));
        horizontal = getHorizontalScrollBar();
        vertical = getVerticalScrollBar();
        repaint();
        timer = new Timer(0, e -> repaint());
        this.getGraphControl().addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();
                System.out.println("x "+x+" y "+y);
                for(Taxi taxi:City.getInstance().getTaxis()){
                    boolean flag=false;
                    System.out.println("TAXIX: "+taxi.getX()+" TAXIY: "+taxi.getY());
                    if((Math.abs(taxi.getX()-x)<50)&&(Math.abs(taxi.getY()-y)<50)&&(!flag)){
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
        //rotatedIcon = new RotatedIcon(image,0);
        // rotateImage(RIGHT);
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
                if(!taxi.nextPoint()){
                    rotateImage(rotatedIcon1, taxi.getDirection());
                    rotatedIcon1.paintIcon(this, g, taxi.getX() - offsetX, taxi.getY() - offsetY);
                    taxi.nextStep();

                } else {
                    map.remove(taxi);
                    Controller.deleteTaxiFromList(taxi);
                }
            }
            for(Client client:City.getInstance().getClients()){
                ImageIcon imageIcon= mapClient.get(client);
                if(imageIcon == null){
                    imageIcon = new ImageIcon(imageCl1);
                    mapClient.put(client, imageIcon);
                }

                imageIcon.paintIcon(this,g,City.getInstance().getXMasPoint(client.getLacation()-offsetX),
                        City.getInstance().getYMasPoint(client.getLacation())-offsetY);
            }


        }

    }
    public void start(){
        timer.start();
    }


}
