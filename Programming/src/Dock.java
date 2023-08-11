import java.util.ArrayList;
import java.util.List;

public class Dock {
    private int dockId;
    private int dockCapacity;
    private double area;
    private List<Bike> bikeList;
    private String address;

    public Dock() {

    }

    public Dock(int dockId, int dockCapacity, String address) {
        this.dockId = dockId;
        this.dockCapacity = dockCapacity;
        this.bikeList = new ArrayList<Bike>();
        this.address = address;
    }

    public boolean addBike(Bike bike) {
        if (bikeList.size() < dockCapacity) {
            bikeList.add(bike);
            bike.setCurrentDockId(dockId);
            bike.setStatus(Bike.Status.DOCKED);
            return true;
        } else {
            return false;
        }
    }

    public boolean removeBike(Bike bike) {
        if (bikeList.contains(bike)) {
            bikeList.remove(bike);
            bike.setCurrentDockId(null);
            bike.setStatus(Bike.Status.AVAILABLE);
            return true;
        } else {
            return false;
        }
    }
    // ... other methods
}
