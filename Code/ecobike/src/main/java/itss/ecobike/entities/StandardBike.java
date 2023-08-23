package itss.ecobike.entities;

public class StandardBike extends Bike{
    public StandardBike(String barcode, BikeType bikeType, String licensePlate, int dockId, Boolean isRented) {
        super(barcode, bikeType, licensePlate, dockId, isRented);
    }
}
