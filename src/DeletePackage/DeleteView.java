package DeletePackage;

import interfaces.IView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;

import java.io.IOException;
import java.util.Optional;


public class DeleteView implements IView {
    @FXML
    public Button b_delete;
    public Button b_back;
    public Label l_error;
    private Scene m_Scene;
    public TextField userTxt;
    public TextField passTxt;


    private DeleteController m_controller;

    public DeleteView() {

        Parent root = null;
        try {


            FXMLLoader l=new FXMLLoader();
            root = l.load(getClass().getResource("deleteFXML.fxml").openStream());
            l.setController(this);

            this.m_Scene = new Scene(root);
        } catch (IOException e) {
            e.printStackTrace();
        }



//
//
//        b_delete = (Button) root.lookup("#b_delete");
//        b_back=(Button) root.lookup("#b_back");
//        l_error=(Label)root.lookup("#l_error");
    }



    //
//    public void setButtonCreateClickedHandler(EventHandler buttonCreateEventHandler){
//        b_back.addEventHandler(javafx.scene.input.MouseEvent.MOUSE_CLICKED,buttonCreateEventHandler);
//    }
    @Override
    public Scene getScene() {
        return this.m_Scene;
    }

//    public void handle(EventHandler e) {
//
//        //model.read(username AND password)
//        //if false-> show view.show l_error
//        //else (below)
//        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
//
//        alert.setTitle("confirmation");
//        alert.setHeaderText("Delete User");
//        alert.setContentText("Are you sure?");
//        Optional<ButtonType> result = alert.showAndWait();
//        if (result.get() == ButtonType.OK)
//            System.out.println("model.delete(username)");
//    }

    public void deleteUser(ActionEvent actionEvent) {
        l_error.setVisible(false);
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);

        alert.setTitle("confirmation");
        alert.setHeaderText("Delete User");
        alert.setContentText("Are you sure?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK)
            m_controller.deleteUser();
    }

    public void showError(){
        l_error.setVisible(true);
    }


}
