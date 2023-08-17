package itss.ecobike.views;

import itss.ecobike.controllers.RentBikeController;
import itss.ecobike.models.Bike;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;

public class DepositScreen {
    @FXML
    private Text barcode;

    @FXML
    private Text bikeTypeName;

    @FXML
    private Text depositFee;

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    void setData(String barcode, String bikeTypeName, String depositFee) {
        this.barcode.setText(barcode);
        this.bikeTypeName.setText(bikeTypeName);
        this.depositFee.setText(depositFee);
    }

    @FXML
    private void returnToBikeInfoScreen() throws IOException, ClassNotFoundException, SQLException {
        FXMLLoader loader = new FXMLLoader();
        String pathToFxml = "./src/main/resources/itss/ecobike/BikeInfoScreen.fxml";
        URL bikeInfoScreenURL = new File(pathToFxml).toURI().toURL();
        loader.setLocation(bikeInfoScreenURL);
        root = loader.load();
        BikeInfoScreen bikeInfoScreen = loader.getController();
        bikeInfoScreen.setData(RentBikeController.validateBarCode(barcode.getText()).get(0));
        scene = new Scene(root);
        stage = (Stage)barcode.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}
