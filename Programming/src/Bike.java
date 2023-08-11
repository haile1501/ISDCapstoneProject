class Bike{
    enum Status {
        AVAILABLE, DOCKED, RENTED, MAINTENANCE
    }
    private Status status = Status.AVAILABLE;
    private String barCode;
    private Integer currentDockId;
    private BikeConfig bikeConfig;
    private RentalStrategy rentalStrategy;

    protected Bike() {
        this.bikeConfig = new BikeConfig();
        this.rentalStrategy = new StandardRentalStrategy();
    }

    protected Bike(BikeConfig bikeConfig, RentalStrategy rentalStrategy) {
        this.bikeConfig = bikeConfig;
        this.rentalStrategy = rentalStrategy;
    }

    protected Bike(int saddleCount, int pedalCount, int rearSeatCount, double bikeValue, double rentalPriceMultiplier) {
        this.bikeConfig = new BikeConfig(saddleCount, pedalCount, rearSeatCount, bikeValue, rentalPriceMultiplier);
    }

    void setSaddleCount(int saddleCount) {
        this.bikeConfig.setSaddleCount(saddleCount);
    }

    int getSaddleCount() {
        return this.bikeConfig.getSaddleCount();
    }

    void setPedalCount(int pedalCount) {
        this.bikeConfig.setPedalCount(pedalCount);
    }

    int getPedalCount() {
        return this.bikeConfig.getPedalCount();
    }

    void setRearSeatCount(int rearSeatCount) {
        this.bikeConfig.setRearSeatCount(rearSeatCount);
    }

    int getRearSeatCount() {
        return this.bikeConfig.getRearSeatCount();
    }

    void setBikeValue(double bikeValue) {
        this.bikeConfig.setBikeValue(bikeValue);
    }

    double getBikeValue() {
        return this.bikeConfig.getBikeValue();
    }

    void setRentalPriceMultiplier(double multiplier) {
        this.bikeConfig.setRentalPriceMultiplier(multiplier);
    }

    double getRentalPriceMultiplier() {
        return this.bikeConfig.getRentalPriceMultiplier();
    }

    void setBarCode(String barCode) {
        this.barCode = barCode;
    }

    String getBarCode() {
        return this.barCode;
    }

    void setStatus(Status status) {
        this.status = status;
    }

    Status getStatus() {
        return this.status;
    }

    void setRentalStrategy(RentalStrategy rentalStrategy) {
        this.rentalStrategy = rentalStrategy;
    }

    double getDepositCost() {
        return this.bikeConfig.getBikeValue() * 0.4;
    }

    double getRentalCost(int duration) {
        return this.rentalStrategy.getRentalCost(duration) * this.getRentalPriceMultiplier();
    }

    void setCurrentDockId(Integer dockId) {
        this.currentDockId = dockId;
    }

    Integer getCurrentDockId() {
        return this.currentDockId;
    }

}