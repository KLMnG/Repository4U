import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class MainView {

    private Scene mainScene;

    @FXML
    public Button b_create;

    public MainView(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("mainPageFXML.fxml"));
        stage.setTitle("Home Page !");
        mainScene = new Scene(root, 600, 500);
        stage.setScene(mainScene);
        stage.show();
    }
}
