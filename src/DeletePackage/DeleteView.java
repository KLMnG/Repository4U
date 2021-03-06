package DeletePackage;

import Controllers.AController;
import Views.IView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.Optional;


public class DeleteView implements IView {

    @FXML
    public Button b_delete;
    public Button b_back;
    public Label l_error;
    public TextField userTxt;
    public PasswordField passTxt;

    private DeleteController m_controller;

    public DeleteView() {
    }



    @Override
    public void setController(AController controller) {
        this.m_controller = (DeleteController) controller;
    }

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

    public void back(ActionEvent actionEvent) {
        this.m_controller.back();
    }

    public void ShowAlert(String alertMessage) {
        Alert alert = new Alert(Alert.AlertType.ERROR,alertMessage,ButtonType.OK);
        alert.show();
    }
}
