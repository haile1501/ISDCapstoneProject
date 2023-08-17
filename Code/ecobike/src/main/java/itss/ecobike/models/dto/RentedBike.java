package itss.ecobike.models.dto;

import itss.ecobike.models.Bike;
import itss.ecobike.models.BikeType;
import itss.ecobike.utils.RentalPriceCalculator;

public class RentedBike extends Bike {

    private final int rentingTime;

    private double amount;

    public RentedBike(String barcode, BikeType bikeType, String licensePlate, int dockId, int batteryPercentage, Boolean isRented, int rentingTime) {
        super(barcode, bikeType, licensePlate, dockId, batteryPercentage, isRented);
        this.rentingTime = rentingTime;
        this.amount = RentalPriceCalculator.calculate(rentingTime, bikeType.getRentalPriceMultiplier());
    }

    public double getAmount() {
        return amount;
    }

    public int getRentingTime() {
        return rentingTime;
    }

}
