import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class PapaController {

    @FXML
            private Button b_b;

    List<AController> controllers;

    public PapaController() {
        controllers=new LinkedList<>();
        addControllers();
    }

    private void addControllers() {
        AController mainPage=new MainPageController();
        controllers.add(mainPage);
        AController login=new LoginController();
        controllers.add(login);

    }

    public void start()  throws IOException {
        Stage stage=new Stage();
        LoginView view=new LoginView();
        view.start(stage);

    }
}
