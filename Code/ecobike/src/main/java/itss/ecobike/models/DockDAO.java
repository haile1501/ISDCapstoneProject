package itss.ecobike.models;

import itss.ecobike.responses.ResponseSearchAvailableBikeInDock;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import itss.ecobike.utils.DBUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DockDAO {
    public static ObservableList<Dock> searchDocks () throws SQLException, ClassNotFoundException {
        String selectStmt = "SELECT * FROM \"Dock\"";

        try {
            ResultSet rsDocks = DBUtil.dbExecuteQuery(selectStmt);

            return getDockList(rsDocks);
        } catch (SQLException e) {
            System.out.println("SQL select operation has been failed: " + e);
            //Return exception
            throw e;
        }
    }
    public static ObservableList<ResponseSearchAvailableBikeInDock> searchBikeInDock (int dock_id) throws SQLException, ClassNotFoundException {
        String selectStmt = "SELECT d.dock_id, d.dock_name, d.address, d.area, d.capacity, COUNT(b.barcode) AS available_bikes\n" +
                "FROM public.\"Dock\" d\n" +
                "LEFT JOIN public.\"Bike\" b ON d.dock_id = b.dock_id\n" +
                "GROUP BY d.dock_id, d.dock_name, d.address, d.area, d.capacity\n" +
                "ORDER BY d.dock_id;\n ";

        try {
            ResultSet rs = DBUtil.dbExecuteQuery(selectStmt);
            return responseSearchAvailableBikeInDocksBikeInDock(rs);
        } catch (SQLException e) {
            System.out.println("SQL select operation has been failed: " + e);
            //Return exception
            throw e;
        }
    }

    private static ObservableList<ResponseSearchAvailableBikeInDock> responseSearchAvailableBikeInDocksBikeInDock(ResultSet rs) throws SQLException, ClassNotFoundException {
        ObservableList<ResponseSearchAvailableBikeInDock> responses = FXCollections.observableArrayList();

        while (rs.next()) {
            int dockId = rs.getInt("dock_id");
            String dockName = rs.getString("dock_name");
            String address = rs.getString("address");
            int area = rs.getInt("area");
            int capacity = rs.getInt("capacity");
            int availableBikes = rs.getInt("available_bikes"); // This should be the alias from your query

            ResponseSearchAvailableBikeInDock response = new ResponseSearchAvailableBikeInDock(dockId, dockName, address, area, capacity, availableBikes);
            responses.add(response);
        }
        return responses;
    }
    private static ObservableList<Dock> getDockList(ResultSet rs) throws SQLException, ClassNotFoundException {
        ObservableList<Dock> dockList = FXCollections.observableArrayList();

        while (rs.next()) {
            Dock dock = new Dock();
            dock.setDockName(rs.getString("dock_name"));
            dock.setId(rs.getInt("dock_id"));
            dock.setAddress(rs.getString("address"));
            dock.setArea(rs.getDouble("area"));
            dock.setDockingPoints(rs.getInt("capacity"));
            //dock.setAvailableBikes(rs.getInt("available_bikes"));
            dockList.add(dock);
        }
        //return dockList (ObservableList of Docks)
        return dockList;
    }
}
