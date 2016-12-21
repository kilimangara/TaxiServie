package views;

import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.view.mxGraph;
import models.City;

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
import java.util.Map;



public class CityGraph extends mxGraphComponent {

    private BufferedImage image;
    private Map<Integer, Object> map;
    private Timer timer;
    int beginx=MainPanel.DEFAULT_SIZE;
    int beginy = MainPanel.DEFAULT_SIZE;

    public CityGraph(mxGraph graph){
        super(graph);
        loadImage();
        timer = new Timer(100, e -> repaint());
    }
    private void loadImage(){
        try {
            image = ImageIO.read(new File("ic_marker_driver.png"));
        } catch (IOException e) {
            System.out.println(e.getLocalizedMessage());
        }
    }
     private void rotateImage(){

     }
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.CYAN);
        Graphics2D g2 = (Graphics2D) g;
        double rotationRequired = Math.toRadians (45);
        double locationX = image.getWidth() / 2;
        double locationY = image.getHeight() / 2;
        AffineTransform tx = AffineTransform.getRotateInstance(rotationRequired, locationX, locationY);
        AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
        if(timer!=null){
            g2.drawImage(op.filter(image,null), beginx, beginy,null);
            beginx+=1;
            if(beginx==MainPanel.DEFAULT_SIZE*5){
                beginx = MainPanel.DEFAULT_SIZE;
            }
        }

    }
    public void start(){
        timer.start();
    }
}
