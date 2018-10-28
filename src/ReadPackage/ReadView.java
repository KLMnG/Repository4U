package ReadPackage;

import General.AController;
import interfaces.IView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class ReadView implements IView {

    private ReadController controller;
    @FXML
    public Button b_search;
    public Button b_back;
    public TextField tf_line;
    public Label l_name;
    public Label l_pass;
    public Label l_first;
    public Label l_last;
    public Label l_address;
    public Label l_birth;
    public GridPane gp_info;
    public Label l_not;


    public ReadView() {
    }

    @Override
    public void setController(AController controller) {
        this.controller = (ReadController) controller;
    }

    public void setUserName(String userName){this.l_name.setText(userName);}
    public void setUserPassword(String userPassword){this.l_pass.setText(userPassword);}
    public void setUserFirstName(String userFirstName){
        this.l_first.setText(userFirstName);
    }
    public void setUserLastName(String userLastName) {
        this.l_last.setText(userLastName);
    }
    public void setUserAddress(String userAddress){this.l_address.setText(userAddress);}
    public void setUserBirthDate(String userBirthDate){this.l_birth.setText(userBirthDate);}

    public String getUserNameFromTextField(){
        return tf_line.getText();
    }

    /* if user has not been found
          hide all the parameters
          and show (red) line -> the user has not been found !
    */
    public void setVisibleFalse(){
        gp_info.setVisible(false);
        l_not.setVisible(true);
        l_not.setText("The user "+ tf_line.getText()+" has not been found !");
        l_not.setStyle("-fx-text-fill: red");
    }

    // show all the parameters and hide the red line
    public void setVisibleTrue(){
        gp_info.setVisible(true);
        l_not.setVisible(false);
        
    }

    public void Search(ActionEvent actionEvent) {
        this.controller.SearchUser();
    }
}
