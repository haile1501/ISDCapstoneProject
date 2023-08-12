package itss.ecobike.models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import itss.ecobike.utils.DBUtil;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BikeDAO {

    public static ObservableList<Bike> searchBikes() throws SQLException, ClassNotFoundException {
        String selectStmt = "SELECT * FROM bike";

        try {
            ResultSet rsBikes = DBUtil.dbExecuteQuery(selectStmt);

            return getBikeList(rsBikes);
        } catch (SQLException e) {
            System.out.println("SQL select operation has been failed: " + e);
            // Return exception
            throw e;
        }
    }

    public static ObservableList<Bike> getBikeIsRentedInDock(int dock_id) throws SQLException, ClassNotFoundException {
        String selectStmt = "SELECT * FROM \"Bike\" WHERE is_rented = true AND dock_id = "+dock_id;
        try {
            ResultSet rsBikes = DBUtil.dbExecuteQuery(selectStmt);
            return getBikeList(rsBikes);
        } catch (SQLException e) {
            System.out.println("SQL select operation has been failed: " + e);
            // Return exception
            throw e;
        }
    }

    public static ObservableList<Bike> getBikeByBarCode(String Barcode) throws SQLException, ClassNotFoundException {
        String selectStmt = "SELECT * FROM \"Bike\" WHERE barcode = "+ Barcode;
        try {
            ResultSet rsBikes = DBUtil.dbExecuteQuery(selectStmt);

            return getBikeList(rsBikes);
        } catch (SQLException e) {
            System.out.println("SQL select operation has been failed: " + e);
            // Return exception
            throw e;
        }
    }
    private static ObservableList<Bike> getBikeList(ResultSet rs) throws SQLException, ClassNotFoundException {
        ObservableList<Bike> bikeList = FXCollections.observableArrayList();

        while (rs.next()) {
            Bike bike = new Bike(
                    rs.getString("barcode"),
                    rs.getInt("bike_type_id"),
                    rs.getString("license_plate"),
                    rs.getInt("dock_id"),
                    rs.getInt("battery_percentage"),
                    rs.getBoolean("is_rented")
            );
            bikeList.add(bike);
        }
        // Return bikeList (ObservableList of Bikes)
        return bikeList;
    }
}
