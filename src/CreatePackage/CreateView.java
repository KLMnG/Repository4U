package CreatePackage;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import interfaces.IView;

import java.io.IOException;

public class CreateView implements IView {

    private Scene m_Scene;

    public CreateView(){

        try {
            Parent root = FXMLLoader.load(getClass().getResource("createFXML.fxml"));
            this.m_Scene = new Scene(root);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @Override
    public Scene getScene() {
        return this.m_Scene;
    }
}
