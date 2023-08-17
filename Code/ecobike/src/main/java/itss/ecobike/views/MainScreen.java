package itss.ecobike.views;

import itss.ecobike.models.Dock;
import itss.ecobike.models.DockDAO;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
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

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private void initialize() throws SQLException, ClassNotFoundException, IOException {
        ObservableList<Dock> docks = DockDAO.searchDocks("");

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
        for (Dock dock: docks) {
            FXMLLoader loader = new FXMLLoader();
            String pathToFxml = "./src/main/resources/itss/ecobike/DockItem.fxml";
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
}
