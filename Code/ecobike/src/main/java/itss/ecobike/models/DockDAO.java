package itss.ecobike.models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import itss.ecobike.utils.DBUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DockDAO {
    public static ObservableList<Dock> searchDocks (String searchInput) throws SQLException, ClassNotFoundException {
        String selectStmt = "select d.*, b.available_bikes\n" +
                "from public.\"Dock\" d left join (select count(b.barcode) as available_bikes, dock_id \n" +
                " from public.\"Bike\" b \n" +
                " where is_rented = false\n" +
                " group by dock_id) b\n" +
                " on d.dock_id = b.dock_id" +
                " where dock_name like '%" + searchInput + "%' or address like '%" + searchInput + "%'";

        try {
            ResultSet rsDocks = DBUtil.dbExecuteQuery(selectStmt);

            return getDockList(rsDocks);
        } catch (SQLException e) {
            System.out.println("SQL select operation has been failed: " + e);
            throw e;
        }
    }

    public static Dock getDockById(int dockId) throws SQLException, ClassNotFoundException {
        String stm = "select d.*, b.available_bikes\n" +
                "from public.\"Dock\" d left join (select count(b.barcode) as available_bikes, dock_id \n" +
                " from public.\"Bike\" b \n" +
                " where is_rented = false\n" +
                " group by dock_id) b\n" +
                " on d.dock_id = b.dock_id" +
                " where d.dock_id = " + dockId;
        try {
            ResultSet rsDocks = DBUtil.dbExecuteQuery(stm);

            return getDockList(rsDocks).get(0);
        } catch (SQLException e) {
            System.out.println("SQL select operation has been failed: " + e);
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
            dock.setAvailableBikes(rs.getInt("available_bikes"));
            dockList.add(dock);
        }

        return dockList;
    }
}
