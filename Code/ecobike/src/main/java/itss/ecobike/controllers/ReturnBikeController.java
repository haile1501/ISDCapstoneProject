package itss.ecobike.controllers;

import itss.ecobike.exceptions.NotEnoughBalanceException;
import itss.ecobike.models.*;

import java.sql.SQLException;

public class ReturnBikeController {
    public static Transaction returnBike(CreditCard creditCard, double rentalAmount, double deposit, int rentalId, String barcode, int returnDockId) throws Exception {
        Interbank interbank = new EcoBank();
        if (interbank.validatePaymentInfo(creditCard, rentalAmount)) {
            interbank.returnDeposit(creditCard, deposit, rentalId);
            if (rentalAmount > 0) {
                Transaction transaction = interbank.payRental(creditCard, rentalAmount, rentalId);
                RentalDAO.updateRentalEndtime(rentalId, returnDockId);
                BikeDAO.returnBike(barcode, returnDockId);
                return transaction;
            } else {
                RentalDAO.updateRentalEndtime(rentalId, returnDockId);
                BikeDAO.returnBike(barcode, returnDockId);
                return null;
            }
        } else {
            return null;
        }
    }
}
