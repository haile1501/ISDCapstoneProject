package itss.ecobike.views.components;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import org.w3c.dom.events.MouseEvent;

public class DockItem {
    @FXML
    private Label dockName;

    @FXML
    private Label address;

    @FXML
    private Label availableBikes;

    public void setData(String dockNameData, String addressData, int availableBikesData) {
        dockName.setText(dockNameData);
        address.setText(addressData);
        availableBikes.setText("Available bikes: " + availableBikesData);
    }
}
