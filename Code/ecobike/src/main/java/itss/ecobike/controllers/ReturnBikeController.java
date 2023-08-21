package itss.ecobike.controllers;

import itss.ecobike.models.*;

public class ReturnBikeController {
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
}
