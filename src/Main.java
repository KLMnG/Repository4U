import General.PapaController;
import javafx.application.Application;
import javafx.stage.Stage;


public class Main extends Application {

    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        PapaController papa = new PapaController(primaryStage);
        papa.SwapScene(PapaController.Views.HomePage);
    }
}