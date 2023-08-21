package itss.ecobike.controllers;

import itss.ecobike.models.*;

public class DepositScreenController {
    public static Transaction processDeposit(CreditCard creditCard, String barcode, String depositFee) throws Exception{
        Interbank bank = new EcoBank();
        bank.validatePaymentInfo(creditCard, Double.parseDouble(depositFee));
        int rental_id = -1;
        try{
            rental_id = RentalDAO.insertRental(barcode, creditCard);
        } catch (Exception e){
            throw new Exception("Error occurred while INSERT Operation: " + e);
        }
        Transaction transaction = bank.payDeposit(creditCard, Double.parseDouble(depositFee), rental_id);
        BikeDAO.updateRentStatus(barcode, true);
        return transaction;
    }
}
