package itss.ecobike.views.components;

import itss.ecobike.models.Bike;
import itss.ecobike.models.Electric;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class BikeItem {
    @FXML
    private Label code;
    @FXML
    private Label type;
    @FXML
    private Label duration;
    @FXML
    private Label battery;
    @FXML
    private Label licensePlate;

    public void setData(Bike bike) {
        this.code.setText("Barcode: " + bike.getBarcode());
        this.type.setText(bike.getBikeType().getTypeName());
        if (bike instanceof Electric) {
            this.duration.setText("Duration: " + ((Electric) bike).getDuration() + " hrs");
            this.battery.setText("Battery: " + ((Electric) bike).getBatteryPercentage() + " %");
            this.licensePlate.setText("License plate: " + bike.getLicensePlate());
        } else {
            this.duration.setText("");
            this.battery.setText("");
            this.licensePlate.setText("");
        }
    }
}
