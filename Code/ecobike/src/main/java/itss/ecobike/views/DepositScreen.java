package itss.ecobike.views;

import itss.ecobike.controllers.DepositScreenController;
import itss.ecobike.controllers.RentBikeController;
import itss.ecobike.models.CreditCard;
import itss.ecobike.models.Transaction;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;

public class DepositScreen {
    @FXML
    private Text barcode;
    @FXML
    private Text bikeTypeName;
    @FXML
    private Text depositFee;
    @FXML
    private TextField cardNumber;
    @FXML
    private TextField cardHolderName;
    @FXML
    private TextField cardSecurityCode;
    @FXML
    private ComboBox<String> expMonthCombo;
    @FXML
    private ComboBox<Integer> expYearCombo;

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
    void initialize() {
        int currentYear = java.time.LocalDate.now().getYear();

        expMonthCombo.setItems(FXCollections.observableArrayList(
                "January", "February", "March", "April", "May", "June",
                "July", "August", "September", "October", "November", "December"
        ));

        for (int i = 0; i < 10; i++) {
            expYearCombo.getItems().add(currentYear + i);
        }

        expMonthCombo.getSelectionModel().select(0); // January
        expYearCombo.getSelectionModel().select(0);
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

    @FXML
    private void processDeposit() throws Exception {
        CreditCard creditCard = new CreditCard(
                cardHolderName.getText(),
                cardNumber.getText(),
                cardSecurityCode.getText(),
                expMonthCombo.getSelectionModel().getSelectedIndex() + 1,
                expYearCombo.getValue()
        );
        // use processDeposit from DepositScreenController
        Transaction transaction = DepositScreenController.processDeposit(
                creditCard,
                barcode.getText(),
                depositFee.getText()
        );
        // show success alert
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText("Deposit successfully");
        alert.setContentText("Transaction ID: " + transaction.getTransactionId() + "\n" +
                        "Bike barcode: " + barcode.getText() + "\n" +
                        "Amount: " + transaction.getAmount() + "\n" +
                        "Transaction time: " + transaction.getTransactionTime() + "\n\n\n" +
                        "Thank you! Please take your bike and enjoy your ride!"
                );
        alert.showAndWait();
        // return to main screen
        returnToMainScreen();
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
}
