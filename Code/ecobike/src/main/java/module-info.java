module itss.ecobike {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.postgresql.jdbc;
    requires de.jensd.fx.glyphs.fontawesome;
    requires java.sql.rowset;

    opens itss.ecobike to javafx.fxml;
    opens itss.ecobike.views to javafx.fxml;
    exports itss.ecobike;
    exports itss.ecobike.controllers;
    exports itss.ecobike.models;
    exports itss.ecobike.views;
    exports itss.ecobike.responses;
}