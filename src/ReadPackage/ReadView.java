package ReadPackage;

import interfaces.IView;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import java.io.IOException;

public class ReadView implements IView {

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

    private Scene r_Scene;

    public ReadView() {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("ReadPackage/readFXML.fxml"));
            this.r_Scene = new Scene(root);
        } catch (IOException e) {
            e.printStackTrace();
        }

        b_search = (Button) root.lookup("#b_search");
        tf_line = (TextField) root.lookup("#tf_line");
        l_name = (Label) root.lookup("#l_name");
        l_pass = (Label) root.lookup("#l_pass");
        l_first = (Label) root.lookup("#l_first");
        l_last = (Label) root.lookup("#l_last");
        l_address = (Label) root.lookup("#l_address");
        l_birth = (Label) root.lookup("#l_birth");
        gp_info = (GridPane) root.lookup("#gp_info");
        l_not = (Label) root.lookup("#l_not");
        b_back = (Button) root.lookup("#b_back");

    }

    public void setButtonSearchClickedHandler(EventHandler buttonSearchEventHandler){
        b_search.addEventHandler(javafx.scene.input.MouseEvent.MOUSE_CLICKED,buttonSearchEventHandler);
    }

    @Override
    public Scene getScene() {
        return r_Scene;
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
}
