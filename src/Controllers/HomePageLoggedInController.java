package Controllers;

import General.PapaController;
import Models.HomePageLoggedinModel;
import Views.HomePageLoggeddinView;
import Views.IView;

public class HomePageLoggedInController extends AController{

    private HomePageLoggeddinView view;
    private HomePageLoggedinModel model;


    public HomePageLoggedInController(PapaController papa, HomePageLoggedinModel model) {
        super(papa);
        this.model = model;
    }

    @Override
    public void setView(IView view) {
        this.view = (HomePageLoggeddinView) view;
    }
}
