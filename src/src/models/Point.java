package models;

public class Point implements Comparable<Point> {
    private int x;
    private int y;
    private int id;

    public Point(int id, int x, int y){
        this.x = x;
        this.y =y;
        this.id = id;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object obj) {
        return ((Integer)this.id).equals(((Point)obj).getId());
    }

    @Override
    public int compareTo(Point o) {
        return ((Integer)this.id).compareTo(o.getId());
    }
}
