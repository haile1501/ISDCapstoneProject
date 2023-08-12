package itss.ecobike.views;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

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

    private int dockId;

    public void setDockId(int dockId) {
        this.dockId = dockId;
    }

    @FXML
    private void initialize() {

    }
}
