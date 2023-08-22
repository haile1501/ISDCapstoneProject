package itss.ecobike.models;

import itss.ecobike.models.dto.RentalInfo;
import itss.ecobike.utils.DBUtil;
import itss.ecobike.utils.RentalPriceCalculator;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
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

    public static void updateRentalEndtime(int rentalId, int returnDockId) throws SQLException, ClassNotFoundException {
        String stm = "update public.\"Rental\" set end_time = current_timestamp, return_dock = " + returnDockId + " where rental_id = " + rentalId;
        DBUtil.dbExecuteUpdate(stm);
    }

    public static RentalInfo getRentalInfo(String barCode) throws SQLException, ClassNotFoundException {
        String stm = "select b.barcode, bt.rental_price_multiplier, bt.type_name, tr.amount as deposit, r.card_number, r.rental_id, ceil(EXTRACT(EPOCH FROM AGE(localtimestamp, start_time)) / 60) AS renting_time" +
                " from public.\"Bike\" b, public.\"BikeType\" bt, public.\"Rental\" r, public.\"Transaction\" tr" +
                " where is_rented = true and b.bike_type_id = bt.type_id" +
                " and r.bike_barcode = b.barcode" +
                " and r.end_time is null" +
                " and tr.rental_id = r.rental_id" +
                " and tr.type = 'pay_deposit'" +
                " and b.barcode = '" + barCode + "'";

        try {
            ResultSet resultSet = DBUtil.dbExecuteQuery(stm);
            RentalInfo rentalInfo = new RentalInfo();

            if (resultSet.next()) {
                rentalInfo.setRentalId(resultSet.getInt("rental_id"));
                rentalInfo.setBarCode(resultSet.getString("barcode"));
                rentalInfo.setType(resultSet.getString("type_name"));
                rentalInfo.setDeposit(resultSet.getDouble("deposit"));
                rentalInfo.setRentingTime(resultSet.getInt("renting_time"));
                rentalInfo.setCardNumber(resultSet.getString("card_number"));
                rentalInfo.setRentalStrategy(new StandardRental());
                rentalInfo.setAmount(rentalInfo.getRentalStrategy().calculate(rentalInfo.getRentingTime()) * resultSet.getDouble("rental_price_multiplier"));
            }

            return rentalInfo;

        } catch (SQLException e) {
            System.out.println("Error");
            throw e;
        }
    }
}
