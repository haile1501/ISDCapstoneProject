package itss.ecobike.views;

import itss.ecobike.controllers.BikeController;
import itss.ecobike.controllers.DockController;
import itss.ecobike.controllers.NotifyScreenController;
import itss.ecobike.entities.Bike;
import itss.ecobike.entities.Dock;
import itss.ecobike.views.components.DockItem;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
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
    private TextField searchInput;
    @FXML
    private TextField barcode;
    @FXML
    private Button viewButton;
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    protected void initialize() throws SQLException, ClassNotFoundException, IOException {
        ObservableList<Dock> docks = DockController.getAllDocks();
        renderDocks(docks);
    }


    @FXML
    private void handleSearchDock() {
        String searchText = searchInput.getText();
        ObservableList<Dock> docks = null;
        try{
            docks = DockController.searchDocks(searchText);
            renderDocks(docks);
        } catch (SQLException | ClassNotFoundException | IOException e) {
            NotifyScreenController.showErrorAlert(e);
        }
    }

    @FXML
    private void viewRentedBikes(MouseEvent mouseEvent) {
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
        stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    private void renderDocks(ObservableList<Dock> docks) throws IOException {
        docksContainer.getChildren().clear();
        for (Dock dock : docks) {
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

                    stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
                    stage.setScene(scene);
                    stage.setUserData(dock.getId());
                    stage.show();
                } catch (IOException | SQLException | ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            });
            DockItem dockItem = loader.getController();
            dockItem.setData(dock);
            docksContainer.getChildren().add(pane);
        }
    }

    @FXML
    private void processScanBarcode() throws SQLException, ClassNotFoundException, IOException {
        String barcodeText = barcode.getText();
        Bike bike = null;
        try{
            bike = BikeController.validateBarCode(barcodeText);
        } catch (SQLException | ClassNotFoundException e) {
            NotifyScreenController.showErrorAlert(e);
            return ;
        }
        FXMLLoader loader = new FXMLLoader();
        String pathToFxml = "./src/main/resources/itss/ecobike/BikeInfoScreen.fxml";
        URL bikeInfoItemURL = new File(pathToFxml).toURI().toURL();
        loader.setLocation(bikeInfoItemURL);
        root = loader.load();
        BikeInfoScreen bikeInfoScreen = loader.getController();
        bikeInfoScreen.setData(bike);
        scene = new Scene(root);
        stage = (Stage) barcode.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void exit() {
        System.exit(0);
    }
}
