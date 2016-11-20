package models;


public class Building implements Structure {
    private String street;
    private int number;
    private int x;
    private int y;


    public Building(String street, int number, int x, int y) {
        this.street = street;
        this.number = number;
        this.x=x;
        this.y=y;
    }


    public int getType() {
        return Types.BUILDING_TYPE;
    }
}
