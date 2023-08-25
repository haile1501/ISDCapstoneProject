package itss.ecobike.entities;

public class Bike {
    private String barcode;
    private BikeType bikeType;
    private String licensePlate;
    private int dockId;
    private Boolean isRented;

    public Bike(String barcode, BikeType bikeType, String licensePlate, int dockId, Boolean isRented) {
        this.barcode = barcode;
        this.bikeType = bikeType;
        this.licensePlate = licensePlate;
        this.dockId = dockId;
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

    public BikeType getBikeType() {
        return bikeType;
    }

    public void setBikeType(BikeType bikeType) {
        this.bikeType = bikeType;
    }
}