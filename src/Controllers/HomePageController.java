package Controllers;

import General.PapaController;
import Models.HomePageModel;
import Views.HomePageView;
import Views.IView;

public class HomePageController extends AController {


    private HomePageView view;
    private HomePageModel model;

    public HomePageController(PapaController papa,HomePageModel model) {
        super(papa);
        this.model = model;
    }

    @Override
    public void setView(IView view) {
        this.view = (HomePageView) view;
    }

    public void openLoginWindow() {
        SwapScene(PapaController.Views.LoginWindow);
    }
}
