package CreatePackage;

import General.ModelUserDB;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import interfaces.IView;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;


public class CreateView implements IView {

    private Scene m_Scene;

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


    public CreateView(){
    }

    @Override
    public Scene getScene() {
        return this.m_Scene;
    }

    public void b_create(ActionEvent actionEvent) {
        CreateController cc = new CreateController(new ModelUserDB(), new CreateView());
        cc.b_create();
    }
}