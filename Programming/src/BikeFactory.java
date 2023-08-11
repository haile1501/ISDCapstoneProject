public class BikeFactory {
    public enum BikeType {
        STANDARD, TWIN, ELECTRIC
    }

    public static Bike createBike(BikeType type) {
        return switch (type) {
            case STANDARD -> new StandardBike();
            case TWIN -> new TwinBike();
            case ELECTRIC -> new EBike();
        };
    }
}
