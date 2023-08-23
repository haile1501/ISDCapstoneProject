package itss.ecobike.controllers;

import itss.ecobike.dao.BikeDAO;
import itss.ecobike.entities.Bike;
import itss.ecobike.entities.dto.RentedBike;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public class BikeController {
    public static ObservableList<Bike> getAvailableBikesInDock(int dockId) throws SQLException, ClassNotFoundException {
        return BikeDAO.getAvailableBikesInDock(dockId);
    }

    public static ObservableList<RentedBike> getRentedBikes() throws SQLException, ClassNotFoundException {
        return BikeDAO.getRentedBikes();
    }

    public static Bike validateBarCode (String BarCode) throws SQLException, ClassNotFoundException {
        if(BarCode.isEmpty()) {
            throw new SQLException("Barcode is empty");
        }
        ObservableList<Bike> bike = BikeDAO.getBikeByBarCode(BarCode);
        if (bike.isEmpty()) {
            throw new SQLException("Barcode not found");
        }
        if (bike.get(0).getRented()) {
            throw new SQLException("Bike is already rented");
        }
        return bike.get(0);
    }
}
