package itss.ecobike.models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import itss.ecobike.utils.DBUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DockDAO {
    public static ObservableList<Dock> searchDocks () throws SQLException, ClassNotFoundException {
        String selectStmt = "SELECT * FROM dock";

        try {
            ResultSet rsDocks = DBUtil.dbExecuteQuery(selectStmt);

            return getDockList(rsDocks);
        } catch (SQLException e) {
            System.out.println("SQL select operation has been failed: " + e);
            //Return exception
            throw e;
        }
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
