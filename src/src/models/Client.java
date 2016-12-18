package models;


public class Client {
    String name;
    String telephone;
    Point lacation;
    Point destination;

    public Client(String name, String telephone, Point lacation, Point destination) {
        this.name = name;
        this.telephone = telephone;
        this.lacation = lacation;
        this.destination = destination;
        callTaxi();
    }

    private void callTaxi() {
        //making an order for taxi
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Point getLacation() {
        return lacation;
    }

    public void setLacation(Point lacation) {
        this.lacation = lacation;
    }

    public Point getDestination() {
        return destination;
    }

    public void setDestination(Point destination) {
        this.destination = destination;
    }
}
