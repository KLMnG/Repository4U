package Views;

import Controllers.AController;
import Controllers.CreateVacationsController;

public class CreateVacationsView implements IView {

    private CreateVacationsController controller;

    @Override
    public void setController(AController controller) {
        this.controller = (CreateVacationsController) controller;
    }
}
