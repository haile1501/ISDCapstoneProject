package itss.ecobike.models;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * A record of a transaction, including ID of the transaction, credit card number of the card performing the transaction,
 * amount of money transfered, and details about the transaction.
 * This transaction might be for deposit, rental or refund
 */
public class Transaction {

    public int getTransactionId() {
        return transactionId;
    }

    public int getRentID() {
        return rentID;
    }

    public double getAmount() {
        return amount;
    }

    public int getStatus() {
        return status;
    }

    public String getType() {
        return type;
    }

    public LocalDateTime getTransactionTime() {
        return transactionTime;
    }

    private final int transactionId;

    private final int rentID;

    private final double amount;

    private final int status;

    private final String type;

    private final LocalDateTime transactionTime;


    public Transaction(int transactionId, int rentID, double amount, int status, String type, LocalDateTime transactionTime) {
        this.transactionId = transactionId;
        this.rentID = rentID;
        this.amount = amount;
        this.status = status;
        this.type = type;
        this.transactionTime = transactionTime;
    }

}
