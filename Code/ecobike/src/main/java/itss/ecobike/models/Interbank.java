package itss.ecobike.models;

import itss.ecobike.exceptions.NotEnoughBalanceException;

import java.sql.SQLException;

public interface Interbank {

    boolean isExpired(CreditCard creditCard);


    boolean validatePaymentInfo(CreditCard creditCard, double amount) throws Exception;

    Transaction payDeposit(CreditCard creditCard, double amount, int rental_id) throws Exception;


    Transaction returnDeposit(CreditCard creditCard, double amount, int rentalId) throws Exception;


    Transaction payRental(CreditCard creditCard, double amount, int rentalId) throws SQLException, ClassNotFoundException, NotEnoughBalanceException;


    double getBalance(CreditCard creditCard);
}
