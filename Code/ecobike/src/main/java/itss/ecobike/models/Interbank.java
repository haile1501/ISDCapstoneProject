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


    public boolean validatePaymentInfo(CreditCard creditCard, double amount) throws Exception;

    public Transaction payDeposit(CreditCard creditCard, double amount, int rental_id) throws Exception;


    public void returnDeposit(CreditCard creditCard, double amount, int rentalId);


    public Transaction payRental(CreditCard creditCard, double amount, int rentalId) throws SQLException, ClassNotFoundException, NotEnoughBalanceException;

    //    @Override
    //    public Transaction returnDeposit(CreditCard creditCard, double amount, String content) {
    //        return null;
    //    }
    //

    public double getBalance(CreditCard creditCard);
}
