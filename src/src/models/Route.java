
package models;
import views.CityGraph;
import models.City;

import java.util.ArrayList;
import java.util.Arrays;


public class Route {

    private boolean isSet;

    public ArrayList<Integer> route;

    public Integer currentPoint;

    public int getCurrentPoint(){
        return this.route.get(currentPoint);

    }
    public int getNextPoint(){
        if(currentPoint+1 <route.size()){
            return this.route.get(currentPoint+1);
        } else {
          return -1;
        }
    }

    public void switchPosition(){
        currentPoint++;
    }

    public int getDirection(){
        if(getNextPoint()==-1){
            return -1;
        }

        if(City.getInstance().getXMasPoint(getCurrentPoint())-City.getInstance().getXMasPoint(getNextPoint())>0){
            return CityGraph.LEFT;
        }
        if(City.getInstance().getXMasPoint(getCurrentPoint())-City.getInstance().getXMasPoint(getNextPoint())<0){
            return CityGraph.RIGHT;
        }
        if(City.getInstance().getYMasPoint(getCurrentPoint())-City.getInstance().getYMasPoint(getNextPoint())>0){
            return CityGraph.TOP;
        }
        if(City.getInstance().getYMasPoint(getCurrentPoint())-City.getInstance().getYMasPoint(getNextPoint())<0){
            return CityGraph.BOTTOM;
        }
        return -1;

    }

    public Route(){
        route = new ArrayList<>();
        currentPoint=0;
    }
    public Route(int start, int finish){
        this();
        setRoute(start, finish);
    }

    private final static int INF = Integer.MAX_VALUE / 2;
    static int n=City.getInstance().vertexCount; 				//количество вершин в орграфе
    static ArrayList<Integer> adj[]; 			//список смежности
    static ArrayList<Integer> weight[]; 		//вес ребра в орграфе
    private boolean used[];						//массив для хранения информации о пройденных и не пройденных вершинах
    private int dist[]; 							//массив для хранения расстояния от стартовой вершины
    private int pred[];							//массив предков, необходимых для восстановления кратчайшего пути из стартовой вершины
    // start - стартовая вершина, от которой ищется расстояние до всех других




    //процедура запуска алгоритма Дейкстры из стартовой вершины
    private void dejkstra(int s) {
        dist[s] = 0;//кратчайшее расстояние до стартовой вершины равно 0
        for (int iter = 0; iter < n; ++iter) {
            int v = -1;
            int distV = INF;
            //выбираем вершину, кратчайшее расстояние до которого еще не найдено
            for (int i = 0; i < n; ++i) {
                if (used[i]) {
                    continue;
                }
                if (distV < dist[i]) {
                    continue;
                }
                v = i;
                distV = dist[i];
            }
            //рассматриваем все дуги, исходящие из найденной вершины
            for (int i = 0; i < adj[v].size(); ++i) {
                int u = adj[v].get(i);
                int weightU = weight[v].get(i);
                //релаксация вершины
                if (dist[v] + weightU < dist[u]) {
                    dist[u] = dist[v] + weightU;
                    pred[u] = v;
                }
            }
            //помечаем вершину v просмотренной, до нее найдено кратчайшее расстояние
            used[v] = true;
        }
    }

    //процедура восстановления кратчайшего пути до вершины v
    /*static void printWay(int v) {
        if (v == -1) {
            return;
        }
        printWay(pred[v]);
        System.out.println((v + 1) + " ");
    }*/
    private void makeWay(int v, Route way) {
        if (v == -1) {
            return;
        }
        makeWay(pred[v],way);
        way.route.add(v+1);

    }

    //процедура заполнения матриц смежности и весов
    private void readData() {

        //инициализируем списка смежности графа размерности n
        adj = new ArrayList[n];
        for (int i = 0; i < n; ++i) {
            adj[i] = new ArrayList<Integer>();
        }
        //инициализация списка, в котором хранятся веса ребер
        weight = new ArrayList[n];
        for (int i = 0; i < n; ++i) {
            weight[i] = new ArrayList<Integer>();
        }

        //считываем граф, заданный списком ребер
        for (int i=0;i<n;i++){
            int length = City.getInstance().connections.get(i).size();
            for (int j=0;j<length;j++){
                int connect=City.getInstance().connections.get(i).get(j);
                int u=i+1;
                int v=connect;
                int w=0;
                if (City.getInstance().masPoint[0][u]==City.getInstance().masPoint[0][v]) w = Math.abs(City.getInstance().masPoint[1][v]-City.getInstance().masPoint[1][u]);
                else w = Math.abs(City.getInstance().masPoint[0][v] - City.getInstance().masPoint[0][u]);
                u--;
                v--;
                adj[u].add(v);
                weight[u].add(w);
            }
        }

        used = new boolean[n];
        Arrays.fill(used, false);

        pred = new int[n];
        Arrays.fill(pred, -1);

        dist = new int[n];
        Arrays.fill(dist, INF);

    }

    public void setRoute(int start, int finish) {
        readData();
        dejkstra(--start);
        makeWay(--finish,this);
    }
}
