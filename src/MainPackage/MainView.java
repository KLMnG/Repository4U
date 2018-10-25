package MainPackage;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import java.io.IOException;
import javafx.event.EventHandler;
import interfaces.IView;

public class MainView implements IView{

    @FXML
    public Button b_create;
    public Button b_update;
    public Button b_delete;
    public Button b_read;

    private Scene m_Scene;

    public MainView() {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("mainPageFXML.fxml"));
            this.m_Scene = new Scene(root);
        } catch (IOException e) {
            e.printStackTrace();
        }

        b_create = (Button) root.lookup("#b_create");
        b_update = (Button) root.lookup("#b_update");
        b_read = (Button) root.lookup("#b_read");
        b_delete = (Button) root.lookup("#b_delete");

    }

    public void setButtonCreateClickedHandler(EventHandler buttonCreateEventHandler){
        b_create.addEventHandler(javafx.scene.input.MouseEvent.MOUSE_CLICKED,buttonCreateEventHandler);
    }


    @Override
    public Scene getScene() {
        return this.m_Scene;
    }
}
