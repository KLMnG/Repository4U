package DeletePackage;

import interfaces.IView;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import java.io.IOException;


public class DeleteView implements IView {
    @FXML
    public Button b_delete;

    private Scene m_Scene;

    public DeleteView() {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("deleteFXML.fxml"));
            this.m_Scene = new Scene(root);
        } catch (IOException e) {
            e.printStackTrace();
        }


        b_delete = (Button) root.lookup("#b_delete");

    }

    public void setButtonCreateClickedHandler(EventHandler buttonCreateEventHandler){
        b_delete.addEventHandler(javafx.scene.input.MouseEvent.MOUSE_CLICKED,buttonCreateEventHandler);
    }
    @Override
    public Scene getScene() {
        return this.m_Scene;
    }

}
