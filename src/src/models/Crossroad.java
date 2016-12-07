package models;


public class Crossroad implements Structure {
    public int getType() {
        return Types.CROSSROAD_TYPE;
    }
    private String firstStreet;
    private String secondStreet;
    private int x;
    private int y;


    public String getFirstStreet() {
        return firstStreet;
    }

    public void setFirstStreet(String firstStreet) {
        this.firstStreet = firstStreet;
    }

    public String getSecondStreet() {
        return secondStreet;
    }

    public void setSecondStreet(String secondStreet) {
        this.secondStreet = secondStreet;
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

    public Crossroad(String firstStreet, String secondStreet, int x, int y) {
        this.firstStreet = firstStreet;
        this.secondStreet = secondStreet;
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return firstStreet+"\n"+secondStreet;
    }
}
