package itss.ecobike.models.dto;

import itss.ecobike.models.Bike;
import itss.ecobike.models.BikeType;

public class RentedBike extends Bike {

    private final int rentingTime;

    public RentedBike(String barcode, BikeType bikeType, String licensePlate, int dockId, int batteryPercentage, Boolean isRented, int rentingTime) {
        super(barcode, bikeType, licensePlate, dockId, batteryPercentage, isRented);
        this.rentingTime = rentingTime;
    }

    public int getRentingTime() {
        return rentingTime;
    }
}
