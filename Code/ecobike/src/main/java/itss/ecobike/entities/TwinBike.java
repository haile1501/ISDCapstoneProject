package itss.ecobike.entities;

public class TwinBike extends Bike{
    public TwinBike(String barcode, BikeType bikeType, String licensePlate, int dockId, Boolean isRented) {
        super(barcode, bikeType, licensePlate, dockId, isRented);
    }
}
