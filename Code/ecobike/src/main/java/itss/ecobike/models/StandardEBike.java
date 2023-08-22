package itss.ecobike.models;

public class StandardEBike extends Bike implements Electric{
    private int batteryPercentage;

    @Override
    public int getBatteryPercentage() {
        return batteryPercentage;
    }

    @Override
    public void setBatteryPercentage(int batteryPercentage) {
        this.batteryPercentage = batteryPercentage;
    }

    public StandardEBike(String barcode, BikeType bikeType, String licensePlate, int dockId, Boolean isRented) {
        super(barcode, bikeType, licensePlate, dockId, isRented);
    }

    public StandardEBike(String barcode, BikeType bikeType, String licensePlate, int dockId, Boolean isRented, int batteryPercentage) {
        super(barcode, bikeType, licensePlate, dockId, isRented);
        this.batteryPercentage = batteryPercentage;
    }

    @Override
    public int getDuration() {
        // get a random number based on battery percentage
        return 2;
    }
}
