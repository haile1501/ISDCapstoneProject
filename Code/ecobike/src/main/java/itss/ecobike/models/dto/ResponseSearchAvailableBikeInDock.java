package itss.ecobike.models.dto;

public class ResponseSearchAvailableBikeInDock {
    private int dockId;
    private String dockName;
    private String address;
    private int area;
    private int capacity;
    private int availableBikes;

    public ResponseSearchAvailableBikeInDock(int dockId, String dockName, String address, int area, int capacity, int availableBikes) {
        this.dockId = dockId;
        this.dockName = dockName;
        this.address = address;
        this.area = area;
        this.capacity = capacity;
        this.availableBikes = availableBikes;
    }

    public int getDockId() {
        return dockId;
    }

    public void setDockId(int dockId) {
        this.dockId = dockId;
    }

    public String getDockName() {
        return dockName;
    }

    public void setDockName(String dockName) {
        this.dockName = dockName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getArea() {
        return area;
    }

    public void setArea(int area) {
        this.area = area;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getAvailableBikes() {
        return availableBikes;
    }

    public void setAvailableBikes(int availableBikes) {
        this.availableBikes = availableBikes;
    }
}
