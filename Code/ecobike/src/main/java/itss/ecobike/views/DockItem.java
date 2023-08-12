package itss.ecobike.views;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import org.w3c.dom.events.MouseEvent;

public class DockItem {
    @FXML
    private Label dockName = new Label();

    @FXML
    private Label address = new Label();

    @FXML
    private Label availableBikes = new Label();

    public void setData(String dockNameData, String addressData, int availableBikesData) {
        dockName.setText(dockNameData);
        address.setText(addressData);
        availableBikes.setText("Available bikes: " + availableBikesData);
    }
}
