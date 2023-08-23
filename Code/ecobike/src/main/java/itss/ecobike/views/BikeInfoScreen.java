package itss.ecobike.views;

import itss.ecobike.controllers.DockController;
import itss.ecobike.entities.Bike;
import itss.ecobike.interfaces.Electric;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;

public class BikeInfoScreen {
    @FXML
    private Text barcode;

    @FXML
    private Text bikeType;

    @FXML
    private Text licensePlate;

    @FXML
    private Text batteryPercentage;

    @FXML
    private Text dockName;

    @FXML
    private Text saddleCount;

    @FXML
    private Text pedalCount;

    @FXML
    private Text rearSeatCount;

    @FXML
    private Text depositFee;

    @FXML
    private Label batteryLabel;

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private void initialize() {


    }

    void setData(Bike bike) throws SQLException, ClassNotFoundException {
        barcode.setText(bike.getBarcode());
        bikeType.setText(bike.getBikeType().getTypeName());
        licensePlate.setText(bike.getLicensePlate());
        dockName.setText(DockController.getDockInfo(bike.getDockId()).getDockName());
        saddleCount.setText(String.valueOf(bike.getBikeType().getSaddleCount()));
        pedalCount.setText(String.valueOf(bike.getBikeType().getPedalCount()));
        rearSeatCount.setText(String.valueOf(bike.getBikeType().getRearSeatCount()));
        depositFee.setText(String.valueOf((int)(bike.getBikeType().getBikeValue() * 0.4)));
        if(bike instanceof Electric) {
            batteryPercentage.setText(((Electric) bike).getBatteryPercentage() + "%");
        } else {
            batteryLabel.setVisible(false);
            batteryPercentage.setVisible(false);
        }
    }
    @FXML
    private void returnToMainScreen() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        String pathToFxml = "./src/main/resources/itss/ecobike/MainScreen.fxml";
        URL mainScreenURL = new File(pathToFxml).toURI().toURL();
        loader.setLocation(mainScreenURL);
        root = loader.load();
        scene = new Scene(root);
        stage = (Stage)barcode.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void proceedToDepositScreen() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        String pathToFxml = "./src/main/resources/itss/ecobike/DepositScreen.fxml";
        URL depositScreenURL = new File(pathToFxml).toURI().toURL();
        loader.setLocation(depositScreenURL);
        root = loader.load();
        DepositScreen depositScreen = loader.getController();
        depositScreen.setData(barcode.getText(), bikeType.getText(), depositFee.getText());
        scene = new Scene(root);
        stage = (Stage)barcode.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void exit() {
        System.exit(0);
    }
}
