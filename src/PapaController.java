import CreatePacage.CreateController;
import CreatePacage.CreateModel;
import CreatePacage.CreateView;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class PapaController {

    private Stage stage;
    private Scene mainScene;

    private MainView mainView;


    public PapaController() {
        CreateView c_view = new CreateView();
        CreateModel c_model = new CreateModel();
        CreateController c_create = new CreateController(c_model, c_view);
    }


    public void start()  throws IOException {
        stage=new Stage();
        mainView = new MainView(stage);
    }


}
