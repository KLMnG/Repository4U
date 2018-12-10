package Views;

import Controllers.LoginController;
import Controllers.AController;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;

public class LoginView implements IView{

    private LoginController controller;
    public Button bn_Login;
    public TextField tf_UserName;
    public TextField tf_Password;

    @Override
    public void setController(AController controller) {
        this.controller = (LoginController)controller;
    }


    public void Login(ActionEvent actionEvent){
        this.controller.Login();
    }
    public String getUserName(){
        return tf_UserName.getText();
    }
    public String getPassword(){
        return tf_Password.getText();
    }
    public void ShowAlert(String str) {
        Alert alert = new Alert(Alert.AlertType.ERROR,str, ButtonType.OK);
        alert.show();

    }
    public void back(){
        this.controller.back();
    }
}
