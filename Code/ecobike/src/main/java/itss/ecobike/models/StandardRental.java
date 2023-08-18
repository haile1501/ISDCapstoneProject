package itss.ecobike.models;

public class StandardRental implements RentalStrategy{
    @Override
    public double calculate(int rentingTimeMinutes) {
        double basePrice = 10000;
        double additionalPrice = 3000;
        if (rentingTimeMinutes <= 10) {
            return 0;
        } else if (rentingTimeMinutes <= 30) {
            return basePrice;
        } else {
            double additionalMinutes = rentingTimeMinutes - 30;
            double additionalBlocks = Math.ceil(additionalMinutes / 15);
            return basePrice + additionalPrice * additionalBlocks;
        }
    }
}
