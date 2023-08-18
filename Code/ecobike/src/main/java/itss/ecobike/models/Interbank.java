package itss.ecobike.models;

import itss.ecobike.exceptions.NotEnoughBalanceException;

import java.sql.SQLException;

/**
 * This interface provides functions that the interbank subsystem must be implemented for its communication with other subsystem
 * Any interbank must implement this interface as a loose connection between the subsystems
 * @author chauntm
 *
 */
public interface Interbank {

    public boolean isExpired(CreditCard creditCard);


    public boolean validatePaymentInfo(CreditCard creditCard, double amount);
    /**
     * Perform paying deposit process, with a given credit card and amount of money to deduct
     * @param creditCard the card being deducted
     * @param amount the amount of money to be deducted
     * @param content information about the transfer
     * @return A <b><i>PaymentTransaction</i></b> record about the transaction <br> <i>null</i> if the transaction fails
     */
    public Transaction payDeposit(CreditCard creditCard, double amount, int rental_id) throws Exception;

    /**
     * Perform returning deposit process, with a given credit card and and amount of money to return
     * @param creditCard the card getting the return
     * @param amount the amount of money to be returned
     * @param content information about the transfer
     * @return A <b><i>PaymentTransaction</i></b> record about the transaction <br> <i>null</i> if the transaction fails
     */
    public void returnDeposit(CreditCard creditCard, double amount, int rentalId);

    /**
     * Perform paying rental process, with a given credit card and and amount of money to deduct
     * @param creditCard the card being deducted
     * @param amount the amount of money to be deducted
     * @param content information about the transfer
     * @return A <b><i>PaymentTransaction</i></b> record about the transaction <br> <i>null</i> if the transaction fails
     */
    public Transaction payRental(CreditCard creditCard, double amount, int rentalId) throws SQLException, ClassNotFoundException, NotEnoughBalanceException;

    //    @Override
    //    public Transaction returnDeposit(CreditCard creditCard, double amount, String content) {
    //        return null;
    //    }
    //

    /**
     * Get current balance of a given credit card.
     * @param creditCard The card with the balance to be queried
     * @return the current balance of the card
     */
    public double getBalance(CreditCard creditCard);
}
