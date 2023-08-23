package itss.ecobike.controllers;

import itss.ecobike.dao.DockDAO;
import itss.ecobike.entities.Dock;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public class DockController {
    public static ObservableList<Dock> getAllDocks() throws SQLException, ClassNotFoundException {
        return DockDAO.searchDocks("");
    }

    public static ObservableList<Dock> searchDocks(String searchInput) throws SQLException, ClassNotFoundException{
        try {
            return DockDAO.searchDocks(searchInput);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static Dock getDockInfo(int dockId) throws SQLException, ClassNotFoundException {
        return DockDAO.getDockById(dockId);
    }
}
