package CreatePackage;

import General.AController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import interfaces.IView;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class CreateView implements IView {

    private CreateController controller;

    @FXML
    public Button b_create;
    public TextField UserName;
    public TextField Password;
    public TextField BirthDate;
    public TextField FirstName;
    public TextField LastName;
    public TextField City;



    public String getUserName(){
        return UserName.getText();
    }
    public String getPassword(){
        return Password.getText();
    }
    public String getBirthDate(){
        return BirthDate.getText();
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
        this.controller.b_create();
    }
}
