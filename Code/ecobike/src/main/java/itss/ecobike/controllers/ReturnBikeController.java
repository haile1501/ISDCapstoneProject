package itss.ecobike.controllers;

import itss.ecobike.exceptions.NotEnoughBalanceException;
import itss.ecobike.models.*;

import java.sql.SQLException;

public class ReturnBikeController {
    public static Transaction returnBike(CreditCard creditCard, double rentalAmount, double deposit, int rentalId, String barcode) throws SQLException, ClassNotFoundException, NotEnoughBalanceException {
        Interbank interbank = new EcoBank();
        interbank.returnDeposit(creditCard, deposit, rentalId);
        Transaction transaction = interbank.payRental(creditCard, rentalAmount, rentalId);
        RentalDAO.updateRentalEndtime(rentalId);
        BikeDAO.returnBike(barcode);

        return transaction;
    }
}
