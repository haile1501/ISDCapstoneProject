package itss.ecobike.entities;

public class Dock {
    private int id;
    private String dockName;
    private String address;
    private double area;
    private int dockingPoints;
    private int availableBikes;

    public int getId() {
        return id;
    }

    public String getAddress() {
        return address;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public String getDockName() {
        return dockName;
    }

    public void setDockName(String dockName) {
        this.dockName = dockName;
    }

    public void setDockingPoints(int dockingPoints) {
        this.dockingPoints = dockingPoints;
    }

    public void setAvailableBikes(int availableBikes) {
        this.availableBikes = availableBikes;
    }

    public double getArea() {
        return area;
    }

    public int getDockingPoints() {
        return dockingPoints;
    }

    public int getAvailableBikes() {
        return availableBikes;
    }

    public int getDistance() {
        return (int)(Math.random() * 50);
    }

    public int getWalkingTime() {
        return (int)(Math.random() * 10);
    }
}
