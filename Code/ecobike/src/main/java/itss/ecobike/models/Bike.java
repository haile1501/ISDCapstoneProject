package itss.ecobike.models;

public class Bike {
    private String barcode;
    private int bikeTypeId;
    private String licensePlate;
    private int dockId;
    private int batteryPercentage;
    private Boolean isRented;

    public Bike(String barcode, int bikeTypeId, String licensePlate, int dockId, int batteryPercentage, Boolean isRented) {
        this.barcode = barcode;
        this.bikeTypeId = bikeTypeId;
        this.licensePlate = licensePlate;
        this.dockId = dockId;
        this.batteryPercentage = batteryPercentage;
        this.isRented = isRented;
    }

    public Boolean getRented() {
        return isRented;
    }


    public void setRented(Boolean rented) {
        isRented = rented;
    }
    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public int getBatteryPercentage() {
        return batteryPercentage;
    }

    public void setBatteryPercentage(int batteryPercentage) {
        this.batteryPercentage = batteryPercentage;
    }

    public int getDockId() {
        return dockId;
    }

    public void setDockId(int dockId) {
        this.dockId = dockId;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public int getBikeTypeId() {
        return bikeTypeId;
    }

    public void setBikeTypeId(int bikeTypeId) {
        this.bikeTypeId = bikeTypeId;
    }
}
