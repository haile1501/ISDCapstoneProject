package itss.ecobike.views;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import itss.ecobike.controllers.RentBikeController;
import itss.ecobike.models.Bike;
import itss.ecobike.models.Dock;
import itss.ecobike.models.DockDAO;
import itss.ecobike.views.components.DockItem;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;

public class MainScreen {
    @FXML
    private VBox docksContainer;

    @FXML
    private Button searchButton;

    @FXML
    private TextField searchInput;

    @FXML
    private FontAwesomeIconView viewRentedBikes;

    @FXML
    private TextField barcode;

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    protected void initialize() throws SQLException, ClassNotFoundException, IOException {
        ObservableList<Dock> docks = DockDAO.searchDocks("");

        viewRentedBikes.setOnMouseClicked(mouseEvent -> {
            FXMLLoader loader2 = new FXMLLoader();
            String pathToFxml2 = "./src/main/resources/itss/ecobike/RentedBikes.fxml";
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

        searchButton.setOnMouseClicked(mouseEvent -> {
            String searchText = searchInput.getText();
            try {
                ObservableList<Dock> docks2 = DockDAO.searchDocks(searchText);
                renderDocks(docks2);
            } catch (SQLException | ClassNotFoundException | IOException e) {
                throw new RuntimeException(e);
            }
        });

        renderDocks(docks);
    }

    private void renderDocks(ObservableList<Dock> docks) throws IOException {
        docksContainer.getChildren().clear();
        for (Dock dock: docks) {
            FXMLLoader loader = new FXMLLoader();
            String pathToFxml = "./src/main/resources/itss/ecobike/components/DockItem.fxml";
            URL dockItemURL = new File(pathToFxml).toURI().toURL();
            loader.setLocation(dockItemURL);
            HBox pane = loader.load();
            pane.setOnMouseClicked(mouseEvent -> {
                try {
                    FXMLLoader loader2 = new FXMLLoader();
                    String pathToFxml2 = "./src/main/resources/itss/ecobike/DockScreen.fxml";
                    URL dockItemURL2 = new File(pathToFxml2).toURI().toURL();
                    loader2.setLocation(dockItemURL2);
                    root = loader2.load();
                    DockScreen dockScreen = loader2.getController();
                    dockScreen.setData(dock.getId());
                    scene = new Scene(root);
                    
                    stage = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
                    stage.setScene(scene);
                    stage.setUserData(dock.getId());
                    stage.show();
                } catch (IOException | SQLException | ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            });
            DockItem dockItem = loader.getController();
            dockItem.setData(dock.getDockName(), dock.getAddress(), dock.getAvailableBikes());
            docksContainer.getChildren().add(pane);
        }
    }

    @FXML
    private void showErrorAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Scan barcode failed");
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    private void processScanBarcode() throws SQLException, ClassNotFoundException, IOException {
        String barcodeText = barcode.getText();
        if (barcodeText.isEmpty()) {
            showErrorAlert("Barcode is blank");
            return;
        }
        ObservableList<Bike> bike = null;
        try {
            bike = RentBikeController.validateBarCode(barcodeText);
        } catch (SQLException e) {
            showErrorAlert(e.getMessage());
            return;
        }
        if (bike.get(0).getRented()) {
            showErrorAlert("Bike is already rented");
            return;
        }
//        System.out.println(bike.get(0).getClass().getName());
        // move to BikeInfoScreen.fxml
        FXMLLoader loader = new FXMLLoader();
        String pathToFxml = "./src/main/resources/itss/ecobike/BikeInfoScreen.fxml";
        URL bikeInfoItemURL = new File(pathToFxml).toURI().toURL();
        loader.setLocation(bikeInfoItemURL);
        root = loader.load();
        BikeInfoScreen bikeInfoScreen = loader.getController();
        bikeInfoScreen.setData(bike.get(0));
        scene = new Scene(root);
        stage = (Stage)barcode.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}
