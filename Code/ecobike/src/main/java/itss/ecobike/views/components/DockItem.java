package itss.ecobike.views.components;

import itss.ecobike.entities.Dock;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class DockItem {
    @FXML
    private Label dockName;

    @FXML
    private Label address;

    @FXML
    private Label availableBikes;

    @FXML
    private Button viewButton;

    public void setData(Dock dock) {
        dockName.setText(dock.getDockName());
        address.setText(dock.getAddress());
        availableBikes.setText("Available bikes: " + dock.getAvailableBikes());
    }
}
