package itss.ecobike.views;

import itss.ecobike.models.Dock;
import itss.ecobike.models.DockDAO;
import itss.ecobike.views.components.ReturnDockItem;
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

public class ReturnBike {

    @FXML
    private VBox docksContainer;

    @FXML
    private Pane back;

    @FXML
    private Label title;

    private Stage stage;
    private Scene scene;
    private Parent root;

    private String bikeCode;
    public void setData(String bikeCode) throws SQLException, ClassNotFoundException, IOException {
        this.title.setText("Select a dock to return bike " + bikeCode);
        this.bikeCode = bikeCode;
        back.setOnMouseClicked(mouseEvent -> {
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

        ObservableList<Dock> docks = DockDAO.searchDocks("");
        docksContainer.getChildren().clear();
        for (Dock dock: docks) {
            FXMLLoader loader = new FXMLLoader();
            String pathToFxml = "./src/main/resources/itss/ecobike/components/ReturnDockItem.fxml";
            URL dockItemURL = new File(pathToFxml).toURI().toURL();
            loader.setLocation(dockItemURL);
            HBox pane = loader.load();
            ReturnDockItem dockItem = loader.getController();
            dockItem.setData(dock.getDockName(), dock.getAddress(), dock.getDockingPoints() - dock.getAvailableBikes(), bikeCode);
            docksContainer.getChildren().add(pane);
        }
    }
}
