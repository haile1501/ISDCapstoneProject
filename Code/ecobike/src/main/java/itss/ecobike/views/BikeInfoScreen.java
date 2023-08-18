package itss.ecobike.views;

import itss.ecobike.models.Bike;
import itss.ecobike.models.Dock;
import itss.ecobike.models.DockDAO;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
        dockName.setText(DockDAO.getDockById(bike.getDockId()).getDockName());
        saddleCount.setText(String.valueOf(bike.getBikeType().getSaddleCount()));
        pedalCount.setText(String.valueOf(bike.getBikeType().getPedalCount()));
        rearSeatCount.setText(String.valueOf(bike.getBikeType().getRearSeatCount()));
        depositFee.setText(String.valueOf((int)(bike.getBikeType().getBikeValue() * 0.4)));
        batteryPercentage.setText("<unavailable>");
    }

    void setData(String barcode){
        this.barcode.setText(barcode);

    }

    @FXML
    private void returnToMainScreen() throws SQLException, IOException, ClassNotFoundException {
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
    private void proceedToDepositScreen() throws SQLException, IOException, ClassNotFoundException {
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
