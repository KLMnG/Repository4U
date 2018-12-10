package Views;

import Controllers.RegisterController;
import Controls.DateTimePicker;
import Controllers.AController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import Views.IView;
import javafx.scene.control.*;

import javax.swing.text.html.ImageView;

public class RegisterView implements IView {

    private RegisterController controller;

    @FXML
    public Button b_create;
    public TextField UserName;
    public PasswordField Password;
    public DateTimePicker BirthDate;
    public TextField FirstName;
    public TextField LastName;
    public TextField City;
    public ImageView iv;




    public String getUserName(){
        return UserName.getText();
    }
    public String getPassword(){
        return Password.getText();
    }
    public String getBirthDate(){
        return BirthDate.getEditor().getText();
    }
    public String getFirstName(){
        return FirstName.getText();
    }
    public String getLastName(){
        return LastName.getText();
    }
    public String getCity(){
        return City.getText();
    }


    public RegisterView(){ }


    @Override
    public void setController(AController controller) {
        this.controller = (RegisterController) controller;
    }

    public void b_create(ActionEvent actionEvent) {
     ;
        Alert a = new Alert(Alert.AlertType.ERROR);
        a.setTitle("Something is wrong");

        if(getUserName().equals("") || getUserName().length()<4 ) {
            a.setContentText("User Name" + " is not valid");
            a.show();
        }
        else if(getPassword().equals("") || getPassword().length()<6 ) {
            a.setContentText("Password" + " is not valid");
            a.show();
        }

        else if( getBirthDate().equals("") || Integer.parseInt(getBirthDate().substring(6,getBirthDate().length()))>2000) {
            a.setContentText("Birth Date" + " is not valid");
            a.show();
        }
        else if(  getFirstName().equals("") ) {
            a.setContentText("First name" + " is not valid");
            a.show();
        }
        else if(  getLastName().equals("") ) {
            a.setContentText("Last name" + " is not valid");
            a.show();
        }
        else if(  getCity().equals("") ) {
            a.setContentText("City" + " is not valid");
            a.show();
        }

        else
            this.controller.b_create();

    }

    public void back(ActionEvent actionEvent) {
        this.controller.back();
    }

    public void ShowAlert(String alertMessage) {
        Alert alert = new Alert(Alert.AlertType.ERROR,alertMessage,ButtonType.OK);
        alert.show();
    }
}
