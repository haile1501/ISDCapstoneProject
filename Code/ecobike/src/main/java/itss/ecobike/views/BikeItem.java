package itss.ecobike.views;

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

    public void setData(String code, String type, int duration, int battery, String licensePlate) {
        this.code.setText("Barcode: " + code);
        this.type.setText(type);
        if (type.equals("Standard E-bike")) {
            this.duration.setText("Duration: " + duration + " hrs");
            this.battery.setText("Battery: " + battery + " %");
            this.licensePlate.setText("License plate: " + licensePlate);
        } else {
            this.duration.setText("");
            this.battery.setText("");
            this.licensePlate.setText("");
        }
    }
}
