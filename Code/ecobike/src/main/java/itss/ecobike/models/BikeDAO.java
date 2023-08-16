package itss.ecobike.models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import itss.ecobike.utils.DBUtil;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BikeDAO {

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

    public static ObservableList<Bike> getAvailableBikesInDock(int dockId) throws SQLException, ClassNotFoundException {
        String stm = "select * from public.\"Bike\" b, public.\"BikeType\" bt where dock_id = " + dockId + " and is_rented = false and b.bike_type_id = bt.type_id";
        try {
            ResultSet resultSet = DBUtil.dbExecuteQuery(stm);

            return getBikeList(resultSet);
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("SQL select operation has been failed: " + e);
            // Return exception
            throw e;
        }
    }

    private static ObservableList<Bike> getBikeList(ResultSet rs) throws SQLException, ClassNotFoundException {
        ObservableList<Bike> bikeList = FXCollections.observableArrayList();

        while (rs.next()) {
            bikeList.add(getBikeFromDBRow(rs));
        }
        // Return bikeList (ObservableList of Bikes)
        return bikeList;
    }

    private static Bike getBikeFromDBRow(ResultSet rs) throws SQLException, ClassNotFoundException {
        BikeType bikeType = new BikeType(
                rs.getInt("type_id"),
                rs.getString("type_name"),
                rs.getInt("saddle_count"),
                rs.getInt("pedal_count"),
                rs.getInt("rear_seat_count"),
                rs.getInt("bike_value"),
                rs.getDouble("rental_price_multiplier")
        );

        return new Bike(
                rs.getString("barcode"),
                bikeType,
                rs.getString("license_plate"),
                rs.getInt("dock_id"),
                rs.getInt("battery_percentage"),
                rs.getBoolean("is_rented")
        );
    }
}
