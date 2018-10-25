import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import java.util.Observable;
import java.util.Observer;


public class MainView implements Observer {
     public AController controller;

    @FXML
    private Button b_login;

    public MainView(AController controller) {
        this.controller = controller;
    }





    public void loginPage(){

    }


    @Override
    public void update(Observable o, Object arg) {

    }
}
