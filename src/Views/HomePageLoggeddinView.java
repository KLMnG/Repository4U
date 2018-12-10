package Views;

import Controllers.AController;
import Controllers.HomePageLoggedInController;
import javafx.event.ActionEvent;

public class HomePageLoggeddinView implements IView{

    private HomePageLoggedInController controller;

    public void Signout(ActionEvent actionEvent) {

    }

    @Override
    public void setController(AController controller) {
        this.controller = (HomePageLoggedInController) controller;
    }
}
