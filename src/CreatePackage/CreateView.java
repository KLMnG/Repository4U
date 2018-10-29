package CreatePackage;

import Controls.DateTimePicker;
import General.AController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import interfaces.IView;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class CreateView implements IView {

    private CreateController controller;

    @FXML
    public Button b_create;
    public TextField UserName;
    public TextField Password;
    public DateTimePicker BirthDate;
    public TextField FirstName;
    public TextField LastName;
    public TextField City;
    public GridPane gp_info;
    public Label l_not;
    public TextField tf_line;




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


    public CreateView(){ }


    @Override
    public void setController(AController controller) {
        this.controller = (CreateController) controller;
    }

    public void b_create(ActionEvent actionEvent) {
     ;
        Alert a = new Alert(Alert.AlertType.ERROR);
        a.setTitle("Something is wrong");

        //b_create.setOnAction(e -> a.getAlertType());

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

        System.out.println("dfgdfg");
    }
}
