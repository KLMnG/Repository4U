import CreatePackage.CreateController;
import CreatePackage.CreateModel;
import CreatePackage.CreateView;
import MainPackage.MainController;
import MainPackage.MainModel;
import MainPackage.MainView;
import javafx.stage.Stage;

import java.io.IOException;

public class PapaController {

    private Stage stage;

    private MainView mainView;


    public PapaController() throws IOException {
        CreateView c_view = new CreateView();
        CreateModel c_model = new CreateModel();
        CreateController c_create = new CreateController(c_model, c_view);

        MainView m_view = new MainView();
        MainModel m_model = new MainModel();
        MainController m_controller = new MainController(m_model, m_view);

    }


    public void start()  throws IOException {
        stage=new Stage();
        mainView = new MainView(stage);
    }



}
