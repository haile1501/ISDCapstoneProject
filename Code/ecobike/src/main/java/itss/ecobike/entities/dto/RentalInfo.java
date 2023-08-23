package itss.ecobike.entities.dto;

import itss.ecobike.entities.strategies.RentalStrategy;

public class RentalInfo {
    private int rentalId;
    private String barCode;
    private String type;
    private double deposit;
    private double amount;
    private int rentingTime;
    private String cardNumber;

    private RentalStrategy rentalStrategy;

    public RentalStrategy getRentalStrategy() {
        return rentalStrategy;
    }

    public void setRentalStrategy(RentalStrategy rentalStrategy) {
        this.rentalStrategy = rentalStrategy;
    }



    public String getBarCode() {
        return barCode;
    }

    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getDeposit() {
        return deposit;
    }

    public void setDeposit(double deposit) {
        this.deposit = deposit;
    }

    public double getAmount() {
        return this.amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getRentingTime() {
        return rentingTime;
    }

    public void setRentingTime(int rentingTime) {
        this.rentingTime = rentingTime;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public int getRentalId() {
        return rentalId;
    }

    public void setRentalId(int rentalId) {
        this.rentalId = rentalId;
    }
}
