package itss.ecobike.views;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;

public class PaymentScreen {

    @FXML
    private Pane back;

    private Stage stage;
    private Scene scene;
    private Parent root;

    private String bikeCode;
    public void setData(String bikeCode) {
        System.out.println(bikeCode);
        this.bikeCode = bikeCode;
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
}
