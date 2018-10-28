package DeletePackage;

import General.AController;
import interfaces.IView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;
import java.util.Optional;


public class DeleteView implements IView {

    @FXML
    public Button b_delete;
    public Button b_back;
    public Label l_error;
    public TextField userTxt;
    public TextField passTxt;

    private DeleteController m_controller;

    public DeleteView() {
    }



    @Override
    public void setController(AController controller) {
        this.m_controller = (DeleteController) controller;
    }

    public void deleteUser(ActionEvent actionEvent) {
        l_error.setVisible(false);
        //model.read(username AND password)
        //if false-> show view.show l_error
        //else (below)
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);

        alert.setTitle("confirmation");
        alert.setHeaderText("Delete User");
        alert.setContentText("Are you sure?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK)
            System.out.println("model.delete(username)");
    }

    public void showError(){
        l_error.setVisible(true);
    }
}
