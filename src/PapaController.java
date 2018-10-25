import CreatePackage.CreateController;
import CreatePackage.CreateModel;
import CreatePackage.CreateView;
import MainPackage.MainController;
import MainPackage.MainModel;
import MainPackage.MainView;
import javafx.scene.Scene;
import javafx.stage.Stage;
import interfaces.IPapaListener;

public class PapaController implements IPapaListener {

    private Stage stage;
    private Scene scene;



    public PapaController() {

        MainView mv = new MainView();

        new MainController(new MainModel(), mv).addPapaListener(this);
        new CreateController(new CreateModel(), new CreateView()).addPapaListener(this);

        stage = new Stage();
        scene = mv.getScene();
    }


    public void start(){
        stage.setScene(this.scene);
        stage.show();
    }


    @Override
    public void SwapScene(Scene scene) {
        this.scene = scene;
    }
}
