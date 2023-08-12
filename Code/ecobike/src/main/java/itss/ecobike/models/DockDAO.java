package itss.ecobike.models;

import itss.ecobike.responses.ResponseSearchAvailableBikeInDock;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import itss.ecobike.utils.DBUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DockDAO {
//    public static Dock searchDock (String empId) throws SQLException, ClassNotFoundException {
//        //Declare a SELECT statement
//        String selectStmt = "SELECT * FROM employees WHERE employee_id="+empId;
//
//        //Execute SELECT statement
//        try {
//            //Get ResultSet from dbExecuteQuery method
//            ResultSet rsEmp = DBUtil.dbExecuteQuery(selectStmt);
//
//            //Send ResultSet to the getDockFromResultSet method and get employee object
//            Dock employee = getDockFromResultSet(rsEmp);
//
//            //Return employee object
//            return employee;
//        } catch (SQLException e) {
//            System.out.println("While searching an employee with " + empId + " id, an error occurred: " + e);
//            //Return exception
//            throw e;
//        }
//    }

//    //Use ResultSet from DB as parameter and set Dock Object's attributes and return employee object.
//    private static Dock getDockFromResultSet(ResultSet rs) throws SQLException
//    {
//        Dock emp = null;
//        if (rs.next()) {
//            emp = new Dock();
//            emp.setDockId(rs.getInt("EMPLOYEE_ID"));
//            emp.setFirstName(rs.getString("FIRST_NAME"));
//            emp.setLastName(rs.getString("LAST_NAME"));
//            emp.setEmail(rs.getString("EMAIL"));
//            emp.setPhoneNumber(rs.getString("PHONE_NUMBER"));
//            emp.setHireDate(rs.getDate("HIRE_DATE"));
//            emp.setJobId(rs.getString("JOB_ID"));
//            emp.setSalary(rs.getInt("SALARY"));
//            emp.setCommissionPct(rs.getDouble("COMMISSION_PCT"));
//            emp.setManagerId(rs.getInt("MANAGER_ID"));
//            emp.setDepartmantId(rs.getInt("DEPARTMENT_ID"));
//        }
//        return emp;
//    }

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
            dock.setDockingPoints(rs.getInt("docking_points"));
            //dock.setAvailableBikes(rs.getInt("available_bikes"));
            dockList.add(dock);
        }
        //return dockList (ObservableList of Docks)
        return dockList;
    }
}
