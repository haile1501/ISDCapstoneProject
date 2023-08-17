package itss.ecobike.views.components;

import itss.ecobike.views.ReturnBike;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;

public class RentedBikeItem {
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
    @FXML
    private Label rentingTime;
    @FXML
    private Label amount;
    @FXML
    private Button returnBike;
    private Stage stage;
    private Scene scene;
    private Parent root;

    public void setData(String code, String type, int duration, int battery, String licensePlate, int rentingTime, int amount) {
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
        this.rentingTime.setText("Renting time: " + rentingTime + " minutes");
        this.amount.setText("Amount: " + amount + " VND");

        returnBike.setOnMouseClicked(mouseEvent -> {
            FXMLLoader loader2 = new FXMLLoader();
            String pathToFxml2 = "./src/main/resources/itss/ecobike/ReturnBikeScreen.fxml";
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
            ReturnBike controller = loader2.getController();
            try {
                controller.setData(code);
            } catch (SQLException | ClassNotFoundException | IOException e) {
                throw new RuntimeException(e);
            }
            scene = new Scene(root);
            stage = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        });
    }
}
