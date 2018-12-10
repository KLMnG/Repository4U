package Views;

import Controllers.AController;
import Controllers.HomePageController;
import javafx.event.ActionEvent;

public class HomePageView implements IView{

    private HomePageController controller;

    public void Signin(ActionEvent actionEvent) {
        this.controller.openLoginWindow();
    }

    @Override
    public void setController(AController controller) {
        this.controller = (HomePageController) controller;
    }
}
