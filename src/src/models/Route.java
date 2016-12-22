
package models;
import views.CityGraph;

import java.util.ArrayList;
import java.util.Arrays;


public class Route {

    private boolean isSet;

    public ArrayList<Integer> route;

    public Integer currentPoint;

    final int inf = Integer.MAX_VALUE/2;

    int n=City.getInstance().vertexCount;
    //количество вершин
    ArrayList<Integer> adj[];
    //список смежности
    ArrayList<Integer> weight[];
    //вес ребра в орграфе
    boolean used[];
    //массив для хранения информации о пройденных и не пройденных вершинах
    int dist[];
    //массив для хранения расстояния от стартовой вершины
    int pred[];
    //массив предков, необходимых для восстановления кратчайшего пути из стартовой вершины
    public int getCurrentPoint(){
        System.out.println("CURR POS: "+City.getInstance().getXMasPoint(route.get(currentPoint))+"" +
                " "+City.getInstance().getYMasPoint(route.get(currentPoint)));
        return this.route.get(currentPoint);

    }
    public int getNextPoint(){
        if(currentPoint+1 <route.size()){
            System.out.println("NEXT POS: "+City.getInstance().getXMasPoint(route.get(currentPoint+1))+"" +
                    " "+City.getInstance().getYMasPoint(route.get(currentPoint+1)));
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

    private void setRoute(int begin, int end) {
     /*   //инициализируем списка смежности графа размерности n
        adj = new ArrayList[n];
        for (int i = 0; i < n; ++i) {
            adj[i] = new ArrayList<Integer>();
        }
        //инициализация списка, в котором хранятся веса ребер
        weight = new ArrayList[n];
        for (int i = 0; i < n; ++i) {
            weight[i] = new ArrayList<Integer>();
        }
        //считываем граф, заданный матрицей смежности
        for (int i=0; i<n; i++)
        	for (int j=0; i<n; j++)
        		if (matr[i][j]!=0){
                    adj[i].add(j);
                    weight[i].add(matr[i][j]);
        		}
        used = new boolean[n];
        Arrays.fill(used, false);

        pred = new int[n];
        Arrays.fill(pred, -1);

        dist = new int[n];
        Arrays.fill(dist, inf);



    	int s=structures.indexOf(begin);	//Начало пути
    	int e=structures.indexOf(end);		//Конец пути
        dist[s] = 0; 							//Кратчайшее расстояние до стартовой вершины равно 0
        for (int j = 0; j < n; ++j) {
            int v = -1;
            int distV = inf;
            //выбираем вершину, кратчайшее расстояние до которой еще не найдено
            for (int i=0; i<n; ++i) {
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
    //Заполняем путь
    prev(e);
    }

    //процедура восстановления кратчайшего пути по массиву предком
    void prev(int v) {
        if (v == -1) {
            return;
        }
        prev(pred[v]);
        route.add(structures.get(v));
        */
    }
}
