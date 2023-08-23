package itss.ecobike.views.components;

import itss.ecobike.controllers.NotifyScreenController;
import itss.ecobike.views.PaymentScreen;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;

public class ReturnDockItem {
    @FXML
    private Label dockName;

    @FXML
    private Label address;

    @FXML
    private Label emptyDockingPoints;

    @FXML
    private Button select;

    private int dockId;

    private Stage stage;
    private Scene scene;
    private Parent root;

    public void setData(int dockId, String dockNameData, String addressData, int emptyDockingPointsData, String bikeCode) {
        this.dockId = dockId;
        dockName.setText(dockNameData);
        address.setText(addressData);
        emptyDockingPoints.setText("Empty docking points: " + emptyDockingPointsData);

        select.setOnMouseClicked(mouseEvent -> {
            if (emptyDockingPointsData < 0) {
                NotifyScreenController.showErrorAlert("Dock is full", "Please select another dock");
                return;
            }
            FXMLLoader loader2 = new FXMLLoader();
            String pathToFxml2 = "./src/main/resources/itss/ecobike/PaymentScreen.fxml";
            URL dockItemURL2 = null;
            try {
                dockItemURL2 = new File(pathToFxml2).toURI().toURL();
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            }
            loader2.setLocation(dockItemURL2);
            try {
                root = loader2.load();
                PaymentScreen controller = loader2.getController();
                controller.setData(bikeCode, dockId);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            scene = new Scene(root);
            stage = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        });
    }
}
