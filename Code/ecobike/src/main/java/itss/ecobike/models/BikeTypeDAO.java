package itss.ecobike.models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import itss.ecobike.utils.DBUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BikeTypeDAO {

    public static ObservableList<BikeType> getAllBikeTypes() throws SQLException, ClassNotFoundException {
        String selectStmt = "SELECT * FROM \"BikeType\"";

        try {
            ResultSet rsBikeTypes = DBUtil.dbExecuteQuery(selectStmt);

            return getBikeTypeList(rsBikeTypes);
        } catch (SQLException e) {
            System.out.println("SQL select operation has failed: " + e);
            // Return exception
            throw e;
        }
    }

    private static ObservableList<BikeType> getBikeTypeList(ResultSet rs) throws SQLException {
        ObservableList<BikeType> bikeTypeList = FXCollections.observableArrayList();

        while (rs.next()) {
            BikeType bikeType = new BikeType(
                    rs.getInt("type_id"),
                    rs.getString("type_name"),
                    rs.getInt("saddle_count"),
                    rs.getInt("pedal_count"),
                    rs.getInt("rear_seat_count"),
                    rs.getInt("bike_value"),
                    rs.getDouble("rental_price_multiplier")
            );
            bikeTypeList.add(bikeType);
        }
        // Return bikeTypeList (ObservableList of BikeTypes)
        return bikeTypeList;
    }
}
