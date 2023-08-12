package itss.ecobike.controllers;

import itss.ecobike.models.Bike;
import javafx.collections.ObservableList;
import itss.ecobike.models.BikeDAO;

import java.sql.SQLException;

public class RentBikeController {
    public ObservableList<Bike> validateBarCode (String BarCode) throws SQLException, ClassNotFoundException {
        ObservableList<Bike> bikes = BikeDAO.getBikeByBarCode(BarCode);
        if(bikes.size() > 1){
            throw new SQLException("dupicate barcode");
        }
        if(bikes.size() < 1){
            throw new Error("No bike has this barcode");
        }
        else {
            return bikes;
        }
    }
}
