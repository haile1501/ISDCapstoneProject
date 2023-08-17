package itss.ecobike.models;

import itss.ecobike.models.dto.RentedBike;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import itss.ecobike.utils.DBUtil;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BikeDAO {

    public static ObservableList<Bike> getRentedBikesInDock(int dock_id) throws SQLException, ClassNotFoundException {
        String selectStmt = "SELECT * FROM \"Bike\" WHERE is_rented = true AND dock_id = "+dock_id;
        try {
            ResultSet resultSet = DBUtil.dbExecuteQuery(selectStmt);
            ObservableList<Bike> bikeList = FXCollections.observableArrayList();

            while (resultSet.next()) {
                bikeList.add(getBikeFromDBRow(resultSet));
            }
            // Return bikeList (ObservableList of Bikes)
            return bikeList;
        } catch (SQLException e) {
            System.out.println("SQL select operation has been failed: " + e);
            // Return exception
            throw e;
        }
    }

    public static ObservableList<Bike> getBikeByBarCode(String Barcode) throws SQLException, ClassNotFoundException {
        String selectStmt = "SELECT * FROM public.\"Bike\" b INNER JOIN public.\"BikeType\" bt ON b.bike_type_id = bt.type_id WHERE barcode = '" + Barcode + "';";
        try {
            ResultSet rsBikes = DBUtil.dbExecuteQuery(selectStmt);

            ObservableList<Bike> bikeList = FXCollections.observableArrayList();

            while (rsBikes.next()) {
                bikeList.add(getBikeFromDBRow(rsBikes));
            }
            // Return bikeList (ObservableList of Bikes)
            return bikeList;
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

            ObservableList<Bike> bikeList = FXCollections.observableArrayList();

            while (resultSet.next()) {
                bikeList.add(getBikeFromDBRow(resultSet));
            }
            // Return bikeList (ObservableList of Bikes)
            return bikeList;

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("SQL select operation has been failed: " + e);
            // Return exception
            throw e;
        }
    }

    public static ObservableList<RentedBike> getRentedBikes() throws SQLException, ClassNotFoundException {
        String stm = "select b.*, bt.*, ceil(EXTRACT(EPOCH FROM AGE(NOW(), start_time)) / 60) AS renting_time\n" +
                " from public.\"Bike\" b, public.\"BikeType\" bt, public.\"Rental\" r\n" +
                " where is_rented = true and b.bike_type_id = bt.type_id\n" +
                " and r.bike_barcode = b.barcode;\n";
        try {
            ResultSet resultSet = DBUtil.dbExecuteQuery(stm);

            ObservableList<RentedBike> bikeList = FXCollections.observableArrayList();

            while (resultSet.next()) {
                bikeList.add(getRentedBikeFromDBRow(resultSet));
            }
            // Return bikeList (ObservableList of Bikes)
            return bikeList;
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("SQL select operation has been failed: " + e);
            // Return exception
            throw e;
        }
    }

    private static RentedBike getRentedBikeFromDBRow(ResultSet rs) throws  SQLException, ClassNotFoundException {
        BikeType bikeType = new BikeType(
                rs.getInt("type_id"),
                rs.getString("type_name"),
                rs.getInt("saddle_count"),
                rs.getInt("pedal_count"),
                rs.getInt("rear_seat_count"),
                rs.getInt("bike_value"),
                rs.getDouble("rental_price_multiplier")
        );

        return new RentedBike(
                rs.getString("barcode"),
                bikeType,
                rs.getString("license_plate"),
                rs.getInt("dock_id"),
                rs.getInt("battery_percentage"),
                rs.getBoolean("is_rented"),
                rs.getInt("renting_time")
        );
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
