package itss.ecobike.utils;

public class RentalPriceCalculator {
    public static double calculate(int minutes, double multiplier) {
        int basePrice = 10000;
        int additionalPrice = 3000;
        if (minutes <= 10) {
            return 0;
        } else if (minutes <= 30) {
            return basePrice;
        } else {
            int additionalMinutes = minutes - 30;
            int additionalBlocks = (int) Math.ceil((double) additionalMinutes / 15);

            return basePrice + (int) (additionalPrice * additionalBlocks * multiplier);
        }
    }
}
