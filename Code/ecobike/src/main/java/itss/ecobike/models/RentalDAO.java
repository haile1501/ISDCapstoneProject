package itss.ecobike.models;

import itss.ecobike.utils.DBUtil;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public class RentalDAO {
    // insert to database name Rental
    public static int insertRental(String barcode, CreditCard creditCard) throws SQLException, ClassNotFoundException {
        int rentDock = BikeDAO.getBikeByBarCode(barcode).get(0).getDockId();
        String insertStmt = "INSERT INTO public.\"Rental\"(bike_barcode, customer_name, card_number, rent_dock, start_time) " +
                "VALUES ('" + barcode + "', '" + creditCard.getCardHolderName() + "', '" + creditCard.getCardNumber() + "', " + rentDock + " , current_timestamp) " +
                "RETURNING rental_id";
        try {
            int rental_id = DBUtil.dbExecuteUpdateWithReturn(insertStmt);
            System.out.print(rental_id);
            return rental_id;
        } catch (SQLException e) {
            System.out.print("Error occurred while INSERT Operation: " + e);
            throw e;
        }
    }
}
