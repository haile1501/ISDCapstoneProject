package itss.ecobike.controllers;

import itss.ecobike.models.*;

public class DepositScreenController {
    public static Transaction processDeposit(CreditCard creditCard, String barcode, String depositFee) throws Exception{
        EcoBank bank = new EcoBank();
        try {
            bank.validatePaymentInfo(creditCard, Double.parseDouble(depositFee));
        } catch (Exception e){
            throw new Exception("Error occurred while validating credit card: " + e);
        }
        System.out.println("Valid payment information");
        int rental_id = -1;
        try{
            rental_id = RentalDAO.insertRental(barcode, creditCard);
        } catch (Exception e){
            throw new Exception("Error occurred while INSERT Operation: " + e);
        }
        System.out.println("Rental id: " + rental_id);
        Transaction transaction = bank.payDeposit(creditCard, Double.parseDouble(depositFee), rental_id);
        System.out.println(transaction.getTransactionId());
        System.out.println(transaction.getAmount());
        System.out.println(transaction.getTransactionTime());
        // update bike status
        BikeDAO.updateRentStatus(barcode, true);
        return transaction;
    }
}
