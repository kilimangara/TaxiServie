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
import java.util.*;

public class MainPanel extends JFrame {
    private static final int DEFAULT_SIZE=30;
    private mxGraph graph;
    private mxGraphComponent component;
    private HashMap<Integer, Object> map;

    public MainPanel(String name){
        super(name);
        initGUI();
    }



    public void initGUI(){
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        City.getInstance().init("structura.xml","matr.xml");
        map = new HashMap<>();

        graph = new mxGraph();
        graph.setAutoSizeCells(true);
        //graph.setCellsLocked(true);
        mxStylesheet stylesheet = graph.getStylesheet();
        Hashtable<String, Object> style = new Hashtable<>();
        style.put(mxConstants.STYLE_SHAPE, mxConstants.SHAPE_ELLIPSE);
        style.put(mxConstants.STYLE_OPACITY, 50);
        style.put(mxConstants.STYLE_FONTSIZE,"6" );
        style.put(mxConstants.STYLE_FONTCOLOR, "#774400");
        stylesheet.putCellStyle("ROUNDED", style);
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
        for(int i=0; i<City.getInstance().vertexCount;++i){

            graph.getModel().beginUpdate();
            Object v1= graph.insertVertex(parent, null, String.valueOf(i),City.getInstance().getXMasPoint(i)*DEFAULT_SIZE+DEFAULT_SIZE,
                    City.getInstance().getYMasPoint(i)*DEFAULT_SIZE+DEFAULT_SIZE,DEFAULT_SIZE, DEFAULT_SIZE, "ROUNDED" );
            map.put(i, v1);
            graph.getModel().endUpdate();

            }
        for(int i=0;i<10;++i) {
            graph.getModel().beginUpdate();
            LinkedList<Integer> list =City.getInstance().connections.get(i);
            for(Integer id: list) {
                graph.insertEdge(parent, null, null, map.get(i + 1), map.get(id));
            }
            graph.getModel().endUpdate();
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

