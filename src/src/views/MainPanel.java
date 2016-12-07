package views;


import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.util.mxConstants;
import com.mxgraph.view.mxGraph;
import com.mxgraph.view.mxStylesheet;
import models.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

public class MainPanel extends JFrame {
    private static final int DEFAULT_SIZE=50;
    private mxGraph graph;
    private mxGraphComponent component;
    private HashMap<String, Object> map;

    public MainPanel(String name){
        super(name);
        initGUI();
    }



    public void initGUI(){
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        City.getInstance().init("structura.xml","matr.xml");
        System.out.println(City.getInstance().getStructures().toString());

        map = new HashMap<>();

        graph = new mxGraph();
        graph.setAutoSizeCells(true);
        //graph.setCellsLocked(true);
        mxStylesheet stylesheet = graph.getStylesheet();
        Hashtable<String, Object> style = new Hashtable<>();
        style.put(mxConstants.STYLE_SHAPE, mxConstants.SHAPE_RECTANGLE);
        style.put(mxConstants.STYLE_OPACITY, 50);
        style.put(mxConstants.STYLE_FONTSIZE,"6" );
        style.put(mxConstants.STYLE_FONTCOLOR, "#774400");
        stylesheet.putCellStyle("ROUNDED", style);
        Hashtable<String, Object> style11 = new Hashtable<>();
        style.put(mxConstants.STYLE_SHAPE, mxConstants.SHAPE_ELLIPSE);
        style.put(mxConstants.STYLE_OPACITY, 50);
        style11.put(mxConstants.STYLE_FONTSIZE,"6" );
        style.put(mxConstants.STYLE_FONTCOLOR, "#774400");
        stylesheet.putCellStyle("RECT", style11);

        Map<String, Object> edge = new HashMap<>();
        edge.put(mxConstants.STYLE_SHAPE, mxConstants.SHAPE_CONNECTOR);
        edge.put(mxConstants.STYLE_FONTSIZE,"10" );
        edge.put(mxConstants.STYLE_ENDARROW, "none");
        edge.put(mxConstants.STYLE_VERTICAL_ALIGN, mxConstants.ALIGN_MIDDLE);
        edge.put(mxConstants.STYLE_ALIGN, mxConstants.ALIGN_CENTER);
        edge.put(mxConstants.STYLE_STROKECOLOR, "#000000"); // default is #6482B9

        stylesheet.setDefaultEdgeStyle(edge);
        component = new mxGraphComponent(graph);
        component.setPreferredSize(new Dimension(400, 400));
        getContentPane().add(component);
        //graph.getModel().beginUpdate();
        Object parent = graph.getDefaultParent();
        for(Structure s:City.getInstance().getStructures()){
            int x=50;
            int y=50;

            switch(s.getType()){
                case Types.BUILDING_TYPE:
                    Building building = (Building) s;
                    graph.getModel().beginUpdate();
                        Object v1= graph.insertVertex(parent, null, building.toString(),building.getX()*DEFAULT_SIZE+DEFAULT_SIZE,
                                building.getY()*DEFAULT_SIZE+DEFAULT_SIZE,DEFAULT_SIZE, DEFAULT_SIZE, "ROUNDED" );
                        map.put(building.toString(), v1);

                    graph.getModel().endUpdate();
                    break;
                case Types.CROSSROAD_TYPE:
                    Crossroad crossroad = (Crossroad) s;
                    graph.getModel().beginUpdate();
                        Object v2=graph.insertVertex(parent, null, crossroad.toString(),crossroad.getX()*DEFAULT_SIZE+DEFAULT_SIZE,
                                crossroad.getY()*DEFAULT_SIZE+DEFAULT_SIZE,DEFAULT_SIZE, DEFAULT_SIZE, "RECT" );
                        map.put(crossroad.toString(), v2);
                    graph.getModel().endUpdate();
                    break;

            }



        }
        /*Object v1= graph.insertVertex(parent,null, "Шкурова 2", 30, 80, 60, 60,"ROUNDED" );
        Object v2= graph.insertVertex(parent, null,"Шкурова 3", 30, 240, 60, 60,"ROUNDED" );
        map.put("Шкурова 2", v1);
        map.put("Шкурова 3", v2);
        graph.insertEdge(parent,null, "Шкурова", map.get("Шкурова 2"), map.get("Шкурова 3"));
        graph.insertVertex(parent, null, "Шкурова-\nДолбоебики",150, 240, 60, 60,"RECT");*/
       // graph.getModel().endUpdate();


    }

}
