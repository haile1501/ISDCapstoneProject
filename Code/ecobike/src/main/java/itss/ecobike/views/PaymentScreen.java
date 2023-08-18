package itss.ecobike.views;

import itss.ecobike.controllers.ReturnBikeController;
import itss.ecobike.exceptions.NotEnoughBalanceException;
import itss.ecobike.models.CreditCard;
import itss.ecobike.models.RentalDAO;
import itss.ecobike.models.Transaction;
import itss.ecobike.models.dto.RentalInfo;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;

public class PaymentScreen {
    @FXML
    private Text cardNumber;
    @FXML
    private Text bikeType;
    @FXML
    private Text barCode;
    @FXML
    private Text depositFee;
    @FXML
    private Text rentalAmount;
    @FXML
    private Text rentingTime;

    @FXML
    private Pane back;

    @FXML
    private Button confirm;

    private int returnDockId;

    private Stage stage;
    private Scene scene;
    private Parent root;

    private String bikeCode;
    public void setData(String bikeCode, int returnDockId) throws SQLException, ClassNotFoundException {
        this.bikeCode = bikeCode;
        this.returnDockId = returnDockId;
        RentalInfo rentalInfo = RentalDAO.getRentalInfo(bikeCode);
        cardNumber.setText(rentalInfo.getCardNumber());
        bikeType.setText(rentalInfo.getType());
        barCode.setText(rentalInfo.getBarCode());
        depositFee.setText(String.valueOf(rentalInfo.getDeposit()));
        rentalAmount.setText(String.valueOf(rentalInfo.getAmount()));
        rentingTime.setText(rentalInfo.getRentingTime() + " minutes");

        confirm.setOnMouseClicked(mouseEvent -> {
            Transaction transaction;
            CreditCard creditCard = new CreditCard(
                    "139396_group5_2023",
                    cardNumber.getText(),
                    "123",
                    10,
                    10
            ); // card expired
            try {
                transaction = ReturnBikeController.returnBike(creditCard, rentalInfo.getAmount(), rentalInfo.getDeposit(), rentalInfo.getRentalId(), rentalInfo.getBarCode(), returnDockId);
            } catch (SQLException | ClassNotFoundException | NotEnoughBalanceException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Not enough balance");
                return;
            }

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText("Return bike successfully");
            alert.setContentText("Transaction ID: " + transaction.getTransactionId() + "\n" +
                    "Amount: " + transaction.getAmount() + "\n" +
                    "Transaction time: " + transaction.getTransactionTime() + "\n\n\n" +
                    "Thank you!"
            );
            alert.showAndWait();

            FXMLLoader loader2 = new FXMLLoader();
            String pathToFxml2 = "./src/main/resources/itss/ecobike/MainScreen.fxml";
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
            scene = new Scene(root);
            stage = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        });

        back.setOnMouseClicked(mouseEvent -> {
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
                ReturnBike controller = loader2.getController();
                controller.setData(bikeCode);
            } catch (IOException | ClassNotFoundException | SQLException e) {
                throw new RuntimeException(e);
            }
            scene = new Scene(root);
            stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        });
    }

    @FXML
    private void exit() {
        System.exit(0);
    }
}
