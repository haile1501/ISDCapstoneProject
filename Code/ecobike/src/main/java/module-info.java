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
    exports itss.ecobike.entities;
    exports itss.ecobike.views;
    exports itss.ecobike.entities.dto;
    exports itss.ecobike.views.components;
    opens itss.ecobike.views.components to javafx.fxml;
    exports itss.ecobike.interfaces;
    exports itss.ecobike.dao;
    exports itss.ecobike.entities.strategies;
}