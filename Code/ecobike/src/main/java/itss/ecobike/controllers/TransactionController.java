package itss.ecobike.controllers;

import itss.ecobike.dao.BikeDAO;
import itss.ecobike.dao.RentalDAO;
import itss.ecobike.entities.CreditCard;
import itss.ecobike.entities.EcoBank;
import itss.ecobike.entities.Transaction;
import itss.ecobike.interfaces.Interbank;

public class TransactionController {
    public static Transaction returnBike(CreditCard creditCard, double rentalAmount, double deposit, int rentalId, String barcode, int returnDockId) throws Exception {
        Interbank bank = new EcoBank();
        bank.validatePaymentInfo(creditCard, rentalAmount);
        bank.returnDeposit(creditCard, deposit, rentalId);
        RentalDAO.updateRentalEndtime(rentalId, returnDockId);
        BikeDAO.returnBike(barcode, returnDockId);
        if (rentalAmount > 0) {
            return bank.payRental(creditCard, rentalAmount, rentalId);
        } else {
            return null;
        }
    }

    public static Transaction processDeposit(CreditCard creditCard, String barcode, String depositFee) throws Exception{
        Interbank bank = new EcoBank();
        bank.validatePaymentInfo(creditCard, Double.parseDouble(depositFee));
        int rental_id;
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
