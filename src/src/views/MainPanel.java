package views;


import com.mxgraph.util.mxConstants;
import com.mxgraph.view.mxGraph;
import com.mxgraph.view.mxStylesheet;
import models.*;
import javax.swing.*;
import java.awt.*;
import java.util.*;

public class MainPanel extends JFrame implements AddTaxiDialog.Listener,AddClientDialog.Listener2 {
    public static final int DEFAULT_SIZE=30;
    private mxGraph graph;
    private CityGraph component;
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
        setLayout(new GridBagLayout());

        graph = new mxGraph();
        graph.setAutoSizeCells(true);
        graph.setCellsLocked(true);
        graph.setEdgeLabelsMovable(false);
        mxStylesheet stylesheet = graph.getStylesheet();
        Hashtable<String, Object> style = new Hashtable<>();
        style.put(mxConstants.STYLE_SHAPE, mxConstants.SHAPE_ELLIPSE);
        style.put(mxConstants.STYLE_OPACITY, 50);
        style.put(mxConstants.STYLE_FONTSIZE,"6" );
        style.put(mxConstants.STYLE_FONTCOLOR, "#774400");
        stylesheet.putCellStyle("ROUNDED", style);
        Map<String, Object> edge = new HashMap<>();
        edge.put(mxConstants.STYLE_SHAPE, mxConstants.SHAPE_CONNECTOR);
        edge.put(mxConstants.STYLE_ENDARROW, "none");
        edge.put(mxConstants.STYLE_STROKECOLOR, "#000000"); // default is #6482B9


Font font = new Font("Verdana",Font.PLAIN, 11 );

        JMenuBar menuBar = new JMenuBar();

        JMenu filemenu = new JMenu("File");
        filemenu.setFont(font);

        JMenuItem newmenu = new JMenuItem("menu1");
        newmenu.setFont(font);
        filemenu.add(newmenu);

        JMenuItem newmenu2 = new JMenuItem("menu2");
        newmenu2.setFont(font);
        filemenu.add(newmenu2);

        menuBar.add(filemenu);

        JMenu setMen = new JMenu("Settings");
        setMen.setFont(font);

        JMenuItem snewmenu = new JMenuItem("menu1");
        snewmenu.setFont(font);
        setMen.add(snewmenu);

        JMenuItem snewmenu2 = new JMenuItem("menu2");
        snewmenu2.setFont(font);
        setMen.add(snewmenu2);

        menuBar.add(setMen);

        JMenu help = new JMenu("Help");
        help.setFont(font);

        JMenuItem hmenu = new JMenuItem("menu1");
        hmenu.setFont(font);
        help.add(hmenu);

        JMenuItem hmenu2 = new JMenuItem("menu2");
        hmenu2.setFont(font);
        help.add(hmenu2);

        menuBar.add(help);

        filemenu.addSeparator();


        this.setJMenuBar(menuBar);


        JButton button1 = new JButton("Добавить такси");
        button1.addActionListener(e -> {
                JDialog dialog = new AddTaxiDialog(this);
            dialog.setVisible(true);
        });
        JButton button2 = new JButton("Добавить клиента");
        button2.addActionListener(e -> {
            JDialog dialog = new AddClientDialog(this);
            dialog.setVisible(true);
        });
        stylesheet.setDefaultEdgeStyle(edge);
        component = new CityGraph(graph, this);
        component.setPreferredSize(new Dimension(400, 400));
        getContentPane().add(component, new GridBagConstraints(1,0,1,1,1,1,GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(0,0,0,0),0,0));
        getContentPane().add(button1, new GridBagConstraints(2,0,1,1,1,1,GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(50,0,0,0),0,0));
        getContentPane().add(button2, new GridBagConstraints(2,0,1,1,1,1,GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(90,0,0,0),0,0));


        //graph.getModel().beginUpdate();
        Object parent = graph.getDefaultParent();


        for(int i=1; i<City.getInstance().vertexCount;++i){

            graph.getModel().beginUpdate();
            Object v1= graph.insertVertex(parent, null, String.valueOf(i),City.getInstance().getXMasPoint(i),
                    City.getInstance().getYMasPoint(i),DEFAULT_SIZE, DEFAULT_SIZE, "ROUNDED" );
            map.put(i, v1);
            graph.getModel().endUpdate();

            }
        for(int i=0;i<City.getInstance().arcCount;++i) {
            graph.getModel().beginUpdate();
            LinkedList<Integer> list =City.getInstance().connections.get(i);
            for(Object id: list) {
                graph.insertEdge(parent, null, null, map.get(i + 1), map.get(id));
            }
            graph.getModel().endUpdate();
        }
        component.start();

    }

    @Override
    public void buttonPressed(Route route) {
        City.getInstance().getTaxis().add(new Taxi(route));
    }

    @Override
    public void buttonPressed2(int startid, int finishid, String name) {
      //  City.getInstance().getClients().add(new Client(name, startid, finishid));
    }

        /*Object v1= graph.insertVertex(parent,null, "Шкурова 2", 30, 80, 60, 60,"ROUNDED" );
        Object v2= graph.insertVertex(parent, null,"Шкурова 3", 30, 240, 60, 60,"ROUNDED" );
        map.put("Шкурова 2", v1);
        map.put("Шкурова 3", v2);
        graph.insertEdge(parent,null, "Шкурова", map.get("Шкурова 2"), map.get("Шкурова 3"));
        graph.insertVertex(parent, null, "Шкурова-\nДолбоебики",150, 240, 60, 60,"RECT");*/
       // graph.getModel().endUpdate();


}

