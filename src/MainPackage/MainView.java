package MainPackage;

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
    public Button b_update;
    public Button b_delete;
    public Button b_read;

    public MainView(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("mainPageFXML.fxml"));
        stage.setTitle("Home Page !");

        b_create = (Button) root.lookup("#b_create");
        b_update = (Button) root.lookup("#b_update");
        b_read = (Button) root.lookup("#b_read");
        b_delete = (Button) root.lookup("#b_delete");


        b_create.setOnAction(e->{
            dos();
        });


        mainScene = new Scene(root, 600, 500);
        stage.setScene(mainScene);
        stage.show();
    }

    public MainView() {

    }

    public void dos(){
        System.out.println("hello it's me !");

    }
}
