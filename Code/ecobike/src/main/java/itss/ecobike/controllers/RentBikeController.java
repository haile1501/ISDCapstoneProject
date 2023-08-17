package itss.ecobike.controllers;

import itss.ecobike.models.Bike;
import javafx.collections.ObservableList;
import itss.ecobike.models.BikeDAO;

import java.sql.SQLException;

public class RentBikeController {
    public static ObservableList<Bike> validateBarCode (String BarCode) throws SQLException, ClassNotFoundException {
        ObservableList<Bike> bikes = BikeDAO.getBikeByBarCode(BarCode);
        if (bikes.isEmpty()) {
            throw new SQLException("Barcode not found");
        }
        return bikes;
    }
}
