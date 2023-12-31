package itss.ecobike.views;

import itss.ecobike.models.BikeDAO;
import itss.ecobike.models.dto.RentedBike;
import itss.ecobike.views.components.RentedBikeItem;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;

public class RentedBikes {
    @FXML
    private VBox rentedBikesContainer;

    @FXML
    private Pane back;

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private void initialize() throws SQLException, ClassNotFoundException, IOException {
        back.setOnMouseClicked(mouseEvent -> {
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
            stage = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        });
        ObservableList<RentedBike> rentedBikes = BikeDAO.getRentedBikes();

        for (RentedBike bike: rentedBikes) {
            FXMLLoader loader = new FXMLLoader();
            String pathToFxml = "./src/main/resources/itss/ecobike/components/RentedBikeItem.fxml";
            URL bikeItemURL = new File(pathToFxml).toURI().toURL();
            loader.setLocation(bikeItemURL);
            Pane pane = loader.load();

            RentedBikeItem rentedBikeItem = loader.getController();
            rentedBikeItem.setData(bike.getBarcode(), bike.getBikeType().getTypeName(), 3, bike.getBatteryPercentage(), bike.getLicensePlate(), bike.getRentingTime(), bike.getAmount());
            rentedBikesContainer.getChildren().add(pane);
        }
    }
}
