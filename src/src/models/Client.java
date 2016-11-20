package models;


public class Client {
    String name;
    String telephone;
    Structure lacation;
    Structure destination;

    public Client(String name, String telephone, Structure lacation, Structure destination) {
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

    public Structure getLacation() {
        return lacation;
    }

    public void setLacation(Structure lacation) {
        this.lacation = lacation;
    }

    public Structure getDestination() {
        return destination;
    }

    public void setDestination(Structure destination) {
        this.destination = destination;
    }
}
