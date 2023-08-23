package itss.ecobike.entities.dto;

import itss.ecobike.entities.strategies.RentalStrategy;
import itss.ecobike.entities.*;
import itss.ecobike.entities.strategies.StandardRental;

public class RentedBike {

    private Bike bike;

    private int rentingTime;

    private double amount;

    private RentalStrategy rentalStrategy;

    public RentedBike(String barcode, BikeType bikeType, String licensePlate, int dockId, Boolean isRented, int rentingTime) {
        this.bike = BikeFactory.createBike(bikeType, barcode, licensePlate, dockId, isRented);
        this.rentingTime = rentingTime;
        this.rentalStrategy = new StandardRental();
        this.amount = rentalStrategy.calculate(rentingTime) * this.bike.getBikeType().getRentalPriceMultiplier();
    }

    public double getAmount() {
        return amount;
    }

    public int getRentingTime() {
        return rentingTime;
    }

    public Bike getBike() {
        return bike;
    }

}
