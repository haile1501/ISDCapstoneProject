package itss.ecobike.views;

import itss.ecobike.models.Bike;
import itss.ecobike.models.BikeDAO;
import itss.ecobike.models.Dock;
import itss.ecobike.models.DockDAO;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
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
        Dock dock = DockDAO.getDockById(dockId);
        dockName.setText(dock.getDockName());
        address.setText("Address: " + dock.getAddress());
        availableBikes.setText("Available bikes: " + dock.getAvailableBikes());
        emptyDockingPoints.setText("Empty docking points: " + (dock.getDockingPoints() - dock.getAvailableBikes()));
        distance.setText("Distance: 2km");
        walkingTime.setText("Walking time: 10 minutes");
        area.setText("Area: " + dock.getArea() + " m²");

        ObservableList<Bike> bikes = BikeDAO.getAvailableBikesInDock(dockId);
        for (Bike bike: bikes) {
            FXMLLoader loader = new FXMLLoader();
            String pathToFxml = "/home/haile/Study/HUST/ITSS/ISDCapstoneProject/Code/ecobike/src/main/resources/itss/ecobike/BikeItem.fxml";
            URL bikeItemURL = new File(pathToFxml).toURI().toURL();
            loader.setLocation(bikeItemURL);
            Pane pane = loader.load();

            BikeItem bikeItem = loader.getController();
            bikeItem.setData(bike.getBarcode(), bike.getBikeType().getTypeName(), 3, bike.getBatteryPercentage(), bike.getLicensePlate());
            bikesContainer.getChildren().add(pane);
        }
    }

    @FXML
    private void initialize() {
        back.setOnMouseClicked(mouseEvent -> {
            FXMLLoader loader2 = new FXMLLoader();
            String pathToFxml2 = "/home/haile/Study/HUST/ITSS/ISDCapstoneProject/Code/ecobike/src/main/resources/itss/ecobike/MainScreen.fxml";
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
            stage = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        });
    }
}
