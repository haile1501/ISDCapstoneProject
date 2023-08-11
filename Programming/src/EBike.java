public class EBike extends Bike implements ElectricVehicle {
    private int batteryPercentage;

    public EBike() {
        super();
        setBikeValue(700000);
        setSaddleCount(1);
        setPedalCount(1);
        setRearSeatCount(1);
        setRentalPriceMultiplier(1.5);
        setBatteryPercentage(100);
    }

    @Override
    public int getBatteryPercentage() {
        return batteryPercentage;
    }

    @Override
    public void setBatteryPercentage(int percentage) {
        batteryPercentage = percentage;
    }
}
