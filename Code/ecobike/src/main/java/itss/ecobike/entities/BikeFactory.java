package itss.ecobike.entities;

public class BikeFactory {
    public static Bike createBike(BikeType bikeType, String barcode, String licensePlate, int dockId, Boolean isRented) {
        return switch (bikeType.getTypeName()) {
            case "Standard" -> new StandardBike(
                    barcode,
                    bikeType,
                    licensePlate,
                    dockId,
                    isRented
            );
            case "Standard E-bike" -> new StandardEBike(
                    barcode,
                    bikeType,
                    licensePlate,
                    dockId,
                    isRented
            );
            case "Twin" -> new TwinBike(
                    barcode,
                    bikeType,
                    licensePlate,
                    dockId,
                    isRented
            );
            default -> null;
        };
    }
}
