package itss.ecobike.views;

import itss.ecobike.controllers.BikeController;
import itss.ecobike.controllers.DockController;
import itss.ecobike.entities.Bike;
import itss.ecobike.entities.Dock;
import itss.ecobike.views.components.BikeItem;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;

public class DockScreen {
    @FXML
    private Label dockName;
    @FXML
    private Label address;
    @FXML
    private Label availableBikes;
    @FXML
    private Label emptyDockingPoints;
    @FXML
    private Label distance;
    @FXML
    private Label walkingTime;
    @FXML
    private Pane back;
    @FXML
    private Label area;
    @FXML
    private VBox bikesContainer;

    private Stage stage;
    private Scene scene;
    private Parent root;

    public void setData(int dockId) throws SQLException, ClassNotFoundException, IOException {
        Dock dock = DockController.getDockInfo(dockId);
        dockName.setText(dock.getDockName());
        address.setText("Address: " + dock.getAddress());
        availableBikes.setText("Available bikes: " + dock.getAvailableBikes());
        emptyDockingPoints.setText("Empty docking points: " + (dock.getDockingPoints() - dock.getAvailableBikes()));
        distance.setText("Distance: " + dock.getDistance() + " m");
        walkingTime.setText("Walking time: " + dock.getWalkingTime() + " minutes");
        area.setText("Area: " + dock.getArea() + " m²");

        ObservableList<Bike> bikes = BikeController.getAvailableBikesInDock(dockId);
        for (Bike bike: bikes) {
            FXMLLoader loader = new FXMLLoader();
            String pathToFxml = "./src/main/resources/itss/ecobike/components/BikeItem.fxml";
            URL bikeItemURL = new File(pathToFxml).toURI().toURL();
            loader.setLocation(bikeItemURL);
            Pane pane = loader.load();

            BikeItem bikeItem = loader.getController();
            bikeItem.setData(bike);
            bikesContainer.getChildren().add(pane);
        }
    }

    @FXML
    private void initialize() {
        back.setOnMouseClicked(this::returnToMainScreen);
    }

    private void returnToMainScreen(MouseEvent mouseEvent) {
        FXMLLoader loader2 = new FXMLLoader();
        String pathToFxml2 = "./src/main/resources/itss/ecobike/MainScreen.fxml";
        URL dockItemURL2 = null;
        try {
            dockItemURL2 = new File(pathToFxml2).toURI().toURL();
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        loader2.setLocation(dockItemURL2);
        try {
            root = loader2.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        scene = new Scene(root);
        stage = (Stage)((Node) mouseEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}
