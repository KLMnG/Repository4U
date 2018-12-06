package General;

import CreatePackage.CreateController;
import MainPackage.MainController;
import Models.ModelUserDB;
import Update.UpdateController;
import DeletePackage.DeleteController;
import ReadPackage.ReadController;
import interfaces.IView;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;

public class PapaController {

    private Stage stage;
    private Scene scene;
    private ModelUserDB model;
    public enum Views {MainWindow,CreateWindow,DeleteWIndow,ReadWindow,UpdateWindow}

    private HashMap<Views,String> views;
    private HashMap<Views,AController> controllers;

    public PapaController(Stage primaryStage) {

        this.stage = primaryStage;
        this.views = new HashMap<>();
        this.controllers = new HashMap<>();
        this.model = new ModelUserDB();

        this.views.put(Views.MainWindow,"/MainPackage/mainPageFXML.fxml");
        this.views.put(Views.CreateWindow,"/CreatePackage/createFXML.fxml");
        this.views.put(Views.DeleteWIndow,"/DeletePackage/deleteFXML.fxml");
        this.views.put(Views.ReadWindow,"/ReadPackage/readFXML.fxml");
        this.views.put(Views.UpdateWindow,"/Update/updateFXML.fxml");

        this.controllers.put(Views.MainWindow,new MainController(this));
        this.controllers.put(Views.CreateWindow,new CreateController(this));
        this.controllers.put(Views.DeleteWIndow,new DeleteController(this));
        this.controllers.put(Views.ReadWindow,new ReadController(this));
        this.controllers.put(Views.UpdateWindow,new UpdateController(this));
    }

    public void SwapScene(Views ViewID) {
        loadView(ViewID);
        stage.setScene(this.scene);
        stage.show();
    }

    private void loadView(Views viewID){
        try {
            FXMLLoader loader = new FXMLLoader();
            Parent root = loader.load(getClass().getResource(this.views.get(viewID)).openStream());

            controllers.get(viewID).setModel(this.model);
            IView view = loader.getController();
            controllers.get(viewID).setView(view);
            view.setController(controllers.get(viewID));


            this.scene = new Scene(root);

        } catch (IOException e) {
        }
    }

}
