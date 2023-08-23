package itss.ecobike.entities;

public class BikeType {
    private int typeId;
    private String typeName;
    private int saddleCount;
    private int pedalCount;
    private int rearSeatCount;
    private int bikeValue;
    private double rentalPriceMultiplier;
    public BikeType(int typeId, String typeName, int saddleCount, int pedalCount, int rearSeatCount, int bikeValue, double rentalPriceMultiplier) {
        this.typeId = typeId;
        this.typeName = typeName;
        this.saddleCount = saddleCount;
        this.pedalCount = pedalCount;
        this.rearSeatCount = rearSeatCount;
        this.bikeValue = bikeValue;
        this.rentalPriceMultiplier = rentalPriceMultiplier;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
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

    public int getBikeValue() {
        return bikeValue;
    }

    public void setBikeValue(int bikeValue) {
        this.bikeValue = bikeValue;
    }

    public double getRentalPriceMultiplier() {
        return rentalPriceMultiplier;
    }

    public void setRentalPriceMultiplier(double rentalPriceMultiplier) {
        this.rentalPriceMultiplier = rentalPriceMultiplier;
    }
}