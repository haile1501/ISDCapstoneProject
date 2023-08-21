package itss.ecobike.models;

import itss.ecobike.exceptions.ExpiredCreditCard;
import itss.ecobike.exceptions.InvalidCardInfo;
import itss.ecobike.exceptions.NotEnoughBalanceException;
import itss.ecobike.exceptions.NullCreditCard;

import java.sql.SQLException;
import java.time.LocalDate;

public class EcoBank implements Interbank{
    @Override
    public boolean isExpired(CreditCard creditCard){
        LocalDate currentLocalDate = LocalDate.now();
        LocalDate expireDate = creditCard.getExpirationDate().atDay(1);
        return expireDate.isBefore(currentLocalDate);
    }
    @Override
    public boolean validatePaymentInfo(CreditCard creditCard, double amount) throws Exception{
        if (creditCard == null){
            throw new NullCreditCard();
        }
        if (!"139396_group5_2023".equals(creditCard.getCardHolderName())){
            throw new InvalidCardInfo();
        }
        if (isExpired(creditCard)){
            throw new ExpiredCreditCard();
        }
        if (creditCard.getBalance() < amount){
            throw new NotEnoughBalanceException();
        }
        return true;
    }
    @Override
    public Transaction payDeposit(CreditCard creditCard, double amount, int rentalId) throws Exception {
        creditCard.setBalance(creditCard.getBalance() + amount);
        int transaction_id = TransactionDAO.insertTransaction(rentalId, amount, "pay_deposit");
        return TransactionDAO.getTransactionByTransactionId(transaction_id).get(0);
    }

    @Override
    public Transaction returnDeposit(CreditCard creditCard, double amount, int rentalId) throws Exception {
        creditCard.setBalance(creditCard.getBalance() - amount);
        int transaction_id = TransactionDAO.insertTransaction(rentalId, amount, "refund_deposit");
        return TransactionDAO.getTransactionByTransactionId(transaction_id).get(0);
    }

    @Override
    public Transaction payRental(CreditCard creditCard, double amount, int rentalId) throws SQLException, ClassNotFoundException, NotEnoughBalanceException {
        creditCard.setBalance(creditCard.getBalance() - amount);
        int transaction_id = TransactionDAO.insertTransaction(rentalId, amount, "pay_rental");
        return TransactionDAO.getTransactionByTransactionId(transaction_id).get(0);
    }

    @Override
    public double getBalance(CreditCard creditCard) {
        return creditCard.getBalance();
    }
}
