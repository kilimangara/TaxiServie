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

    public static final int LEFT=0;
    public static final int RIGHT=1;
    public static final int TOP=2;
    public static final int BOTTOM=3;

    private Icon image;

    private RotatedIcon rotatedIcon;

    private Map<Integer, Object> map;

    private Timer timer;

    private boolean flag;
    int beginx=MainPanel.DEFAULT_SIZE;
    int beginy = MainPanel.DEFAULT_SIZE;

    public CityGraph(mxGraph graph){
        super(graph);
        loadImage();
        timer = new Timer(100, e -> repaint());
        flag = false;
        rotatedIcon = new RotatedIcon(image,0);
        rotateImage(RIGHT);
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
     private void rotateImage(int direction){
         switch (direction){
             case LEFT:
                 rotatedIcon.setDegrees(270);
                 repaint();
                 break;
             case RIGHT:
                 rotatedIcon.setDegrees(90);
                 repaint();
                 break;
             case TOP:
                 rotatedIcon.setDegrees(0);
                 repaint();
                break;
             case BOTTOM:
                 rotatedIcon.setDegrees(180);
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
            rotatedIcon.paintIcon(this,g, beginx, beginy);
            if(!flag){
                beginx+=1;
                if(beginx==MainPanel.DEFAULT_SIZE*5){
                    rotateImage(BOTTOM);
                    flag = true;
                }

            } else {
                beginy+=1;
                if(beginy == MainPanel.DEFAULT_SIZE*5){
                    flag=false;
                    rotateImage(RIGHT);
                    beginx =MainPanel.DEFAULT_SIZE;
                    beginy =MainPanel.DEFAULT_SIZE;
                }

            }

        }

    }
    public void start(){
        timer.start();
    }
}
