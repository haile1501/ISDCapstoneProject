package itss.ecobike.models;

import java.sql.Date;

public class Rental {
    private final int RentalId;
    private final String bikeBarCode;
    private final String customerName;
    private final int cardNumber;
    private final int rentDock;
    private final int returnDock;
    private final Date startTime;
    private final Date endTime;

    public Rental(int rentalId, String bikeBarCode, String customerName, int cardNumber, int rentDock, int returnDock, Date startTime, Date endTime) {
        RentalId = rentalId;
        this.bikeBarCode = bikeBarCode;
        this.customerName = customerName;
        this.cardNumber = cardNumber;
        this.rentDock = rentDock;
        this.returnDock = returnDock;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public int getRentalId() {
        return RentalId;
    }

    public String getBikeBarCode() {
        return bikeBarCode;
    }

    public String getCustomerName() {
        return customerName;
    }

    public int getCardNumber() {
        return cardNumber;
    }

    public int getRentDock() {
        return rentDock;
    }

    public int getReturnDock() {
        return returnDock;
    }

    public Date getStartTime() {
        return startTime;
    }

    public Date getEndTime() {
        return endTime;
    }
}
