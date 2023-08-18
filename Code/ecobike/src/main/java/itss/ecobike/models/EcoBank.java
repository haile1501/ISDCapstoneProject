package itss.ecobike.models;

import java.time.LocalDate;

public class EcoBank implements Interbank{

    @Override
    public boolean isExpired(CreditCard creditCard){
        LocalDate currentLocalDate = LocalDate.now();
        LocalDate expireDate = creditCard.getExpirationDate().atDay(1);
        return expireDate.isBefore(currentLocalDate);
    }
    @Override
    public boolean validatePaymentInfo(CreditCard creditCard, double amount){
        if (creditCard == null){
            return false;
        }
        if (isExpired(creditCard)){
            return false;
        }
        return !(creditCard.getBalance() < amount);
    }
    @Override
    public Transaction payDeposit(CreditCard creditCard, double amount, int rental_id) throws Exception {
        // deduct money from credit card
//        creditCard.setBalance(creditCard.getBalance() - amount);
        int transaction_id = TransactionDAO.insertTransaction(rental_id, amount, "deposit");

        return TransactionDAO.getTransactionByTransactionId(transaction_id).get(0);
    }

//    @Override
//    public Transaction returnDeposit(CreditCard creditCard, double amount, String content) {
//        return null;
//    }
//
//    @Override
//    public Transaction payRental(CreditCard creditCard, double amount, String content) {
//        return null;
//    }

    @Override
    public double getBalance(CreditCard creditCard) {
        return 0;
    }
}
