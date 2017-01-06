package views;


import com.mxgraph.util.mxConstants;
import com.mxgraph.view.mxGraph;
import com.mxgraph.view.mxStylesheet;
import models.City;
import models.Taxi;
import models.customDB.DBHelper;
import views.dialogs.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.*;
import java.util.*;
import java.util.List;

public class MainPanel extends JFrame implements ComponentListener{
    public static final int DEFAULT_SIZE=30;
    private mxGraph graph;
    private CityGraph component;
    private HashMap<Integer, Object> map;

    public MainPanel(String name){
        super(name);
        initGUI();
        this.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {

            }

            @Override
            public void windowClosing(WindowEvent e) {
                Object[] options = { "Да", "Нет!" };
                int n = JOptionPane
                        .showOptionDialog(e.getWindow(), "Закрыть окно?",
                                "Подтверждение", JOptionPane.YES_NO_OPTION,
                                JOptionPane.QUESTION_MESSAGE, null, options,
                                options[0]);
                if (n == 0) {
                    e.getWindow().setVisible(false);
                    saveFile();
                    DBHelper.getInstance().writeHistory();
                    System.exit(0);
                }
            }

            @Override
            public void windowClosed(WindowEvent e) {

            }

            @Override
            public void windowIconified(WindowEvent e) {

            }

            @Override
            public void windowDeiconified(WindowEvent e) {

            }

            @Override
            public void windowActivated(WindowEvent e) {

            }

            @Override
            public void windowDeactivated(WindowEvent e) {

            }
        });
    }



    private void loadFile(){
        File file = new File("saveFile.txt");
        try {
            ObjectInputStream stream = new ObjectInputStream(new FileInputStream(file));
            City.getInstance().setTaxis((List<Taxi>)stream.readObject());
            stream.close();

        } catch (IOException | ClassNotFoundException ignored) {
            System.out.println(ignored.getLocalizedMessage());
        }
    }

    private void saveFile(){
        File file = new File("saveFile.txt");
        try {
            ObjectOutputStream stream = new ObjectOutputStream(new FileOutputStream(file));
            stream.writeObject(City.getInstance().getTaxis());
            stream.close();
        } catch (IOException e) {
            System.out.println(e.getLocalizedMessage());
        }
    }
    public void initGUI(){
        setSize(900, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        City.getInstance().init("structura.xml");
        DBHelper.getInstance().init();

        loadFile();
        addComponentListener(this);
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

        JMenuItem newmenu = new JMenuItem("Статистика поездок");
        newmenu.addActionListener(e -> {
            new StatisticsDialog(this);
        });
        newmenu.setFont(font);
        filemenu.add(newmenu);

        JMenuItem newmenu2 = new JMenuItem("Информация о такси");
        newmenu2.addActionListener(e -> new TaxiManagingDialog(this));
        newmenu2.setFont(font);
        filemenu.add(newmenu2);

        menuBar.add(filemenu);

        JMenu help = new JMenu("Help");
        help.setFont(font);

        JMenuItem hmenu = new JMenuItem("About software");
        hmenu.setFont(font);
        help.add(hmenu);

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

        JButton button3 = new JButton("Добавить рандомных клиентов");
        button3.addActionListener(e -> {
             AddRandomClients.AddRandomClients();
        });


        stylesheet.setDefaultEdgeStyle(edge);
        component = new CityGraph(graph, this);
        component.setPreferredSize(new Dimension(450, 650));
        getContentPane().add(component, new GridBagConstraints(1,0,1,1,1,1,GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(0,0,0,0),0,0));
        getContentPane().add(button1, new GridBagConstraints(2,0,1,1,1,1,GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(50,0,0,0),0,0));
        getContentPane().add(button2, new GridBagConstraints(2,0,1,1,1,1,GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(90,0,0,0),0,0));
        getContentPane().add(button3, new GridBagConstraints(2,0,1,1,1,1,GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(140,0,0,0),0,0));
        Object parent = graph.getDefaultParent();
        for(int i=1; i<City.getInstance().vertexCount;++i){

            graph.getModel().beginUpdate();
            Object v1= graph.insertVertex(parent, null, String.valueOf(i),City.getInstance().getXMasPoint(i),
                    City.getInstance().getYMasPoint(i),DEFAULT_SIZE, DEFAULT_SIZE, "ROUNDED" );
            map.put(i, v1);
            graph.getModel().endUpdate();

            }
        for(int i=0;i<City.getInstance().vertexCount;++i) {
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
    public void componentResized(ComponentEvent e) {
        int width =400;
        if(e.getComponent().getWidth()/2>=400){
            width = e.getComponent().getWidth()/2;
        }
        component.setPreferredSize(new Dimension(e.getComponent().getWidth()/2,e.getComponent().getHeight()-50));
    }

    @Override
    public void componentMoved(ComponentEvent e) {

    }

    @Override
    public void componentShown(ComponentEvent e) {

    }

    @Override
    public void componentHidden(ComponentEvent e) {

    }
}

