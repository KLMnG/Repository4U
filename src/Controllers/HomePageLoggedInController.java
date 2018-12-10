package Controllers;

import General.PapaController;
import Models.HomePageLoggedinModel;
import Views.HomePageLoggedinView;
import Views.IView;

public class HomePageLoggedInController extends AController{

    private HomePageLoggedinView view;
    private HomePageLoggedinModel model;


    public HomePageLoggedInController(PapaController papa, HomePageLoggedinModel model) {
        super(papa);
        this.model = model;
    }

    @Override
    public void setView(IView view) {
        this.view = (HomePageLoggedinView) view;
    }

    public void initializeView() {
        this.view.setUserGesture("♥ Hello " + this.model.getUserName() + " ♥");

    }
}
