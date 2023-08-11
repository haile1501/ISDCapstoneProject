public class App {
    public static void main(String[] args) {
        Bike standardBike = BikeFactory.createBike(BikeFactory.BikeType.STANDARD);
        Bike twinBike = BikeFactory.createBike(BikeFactory.BikeType.TWIN);
        Bike eBike = BikeFactory.createBike(BikeFactory.BikeType.ELECTRIC);

        System.out.println("Standard bike value: " + standardBike.getBikeValue());
        System.out.println("Twin bike value: " + twinBike.getBikeValue());
        System.out.println("E-bike value: " + eBike.getBikeValue());
        System.out.println("E-bike battery percentage: " + ((ElectricVehicle) eBike).getBatteryPercentage());

        // calculate some sample rental prices
        System.out.println("Standard bike rental price: " + twinBike.getRentalCost(70));

    }
}
