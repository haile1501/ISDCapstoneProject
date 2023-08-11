public class BikeConfig {
    private int saddleCount;
    private int pedalCount;
    private int rearSeatCount;
    private double bikeValue; // deposit 40% of bike value
    private double rentalPriceMultiplier = 1.0; // each bike type has different multiplier

    public BikeConfig() {

    }

    public BikeConfig(int saddleCount, int pedalCount, int rearSeatCount, double bikeValue, double rentalPriceMultiplier) {
        this.saddleCount = saddleCount;
        this.pedalCount = pedalCount;
        this.rearSeatCount = rearSeatCount;
        this.bikeValue = bikeValue;
        this.rentalPriceMultiplier = rentalPriceMultiplier;
    }

    public int getSaddleCount() {
        return saddleCount;
    }

    public void setSaddleCount(int saddleCount) {
        this.saddleCount = saddleCount;
    }

    public int getPedalCount() {
        return pedalCount;
    }

    public void setPedalCount(int pedalCount) {
        this.pedalCount = pedalCount;
    }

    public int getRearSeatCount() {
        return rearSeatCount;
    }

    public void setRearSeatCount(int rearSeatCount) {
        this.rearSeatCount = rearSeatCount;
    }

    public double getBikeValue() {
        return bikeValue;
    }

    public void setBikeValue(double bikeValue) {
        this.bikeValue = bikeValue;
    }

    public double getRentalPriceMultiplier() {
        return rentalPriceMultiplier;
    }

    public void setRentalPriceMultiplier(double multiplier) {
        this.rentalPriceMultiplier = multiplier;
    }
}
