package itss.ecobike.views;

import itss.ecobike.models.Dock;
import itss.ecobike.models.DockDAO;
import itss.ecobike.views.components.DockItem;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.sql.SQLException;
import java.util.List;

public class MainScreen {
    @FXML
    private VBox docksContainer;

    @FXML
    private void initialize() throws SQLException, ClassNotFoundException {
        ObservableList<Dock> docks = DockDAO.searchDocks();

        for (Dock dock : docks) {
            Label dockName = new Label(dock.getDockName());
            Label dockAddress = new Label(dock.getAddress());
            Label availableBikes = new Label("10");
            Pane dockPane = new Pane();
//            dockPane.getChildren().add(dockName);
//            dockPane.getChildren().add(dockAddress);
            dockPane.getChildren().add(availableBikes);
            DockItem dockItem = new DockItem();

            docksContainer.getChildren().add(dockPane);
        }

    }
}
