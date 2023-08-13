package itss.ecobike.views;

import itss.ecobike.models.Dock;
import itss.ecobike.models.DockDAO;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;

public class MainScreen {
    @FXML
    private VBox docksContainer;

    @FXML
    private void initialize() throws SQLException, ClassNotFoundException, IOException {
        ObservableList<Dock> docks = DockDAO.searchDocks();

        for (Dock dock: docks) {
            FXMLLoader loader = new FXMLLoader();
            String pathToFxml = ".\\src\\main\\resources\\itss\\ecobike\\DockItem.fxml";
            URL dockItemURL = new File(pathToFxml).toURI().toURL();
            loader.setLocation(dockItemURL);
            HBox pane = loader.load();
            pane.setOnMouseClicked(mouseEvent -> {
                Parent newScreenParent = null;
                try {
                    FXMLLoader loader2 = new FXMLLoader();
                    String pathToFxml2 = ".\\src\\main\\resources\\itss\\ecobike\\DockScreen.fxml";
                    URL dockItemURL2 = new File(pathToFxml2).toURI().toURL();
                    loader2.setLocation(dockItemURL2);
                    newScreenParent = loader2.load();
                    Scene newScreenScene = new Scene(newScreenParent);
                    Stage currentStage = new Stage();

                    currentStage.setScene(newScreenScene);
                    currentStage.show();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
            DockItem dockItem = loader.getController();
            dockItem.setData(dock.getDockName(), dock.getAddress(), dock.getAvailableBikes());
            docksContainer.getChildren().add(pane);
        }

    }
}
