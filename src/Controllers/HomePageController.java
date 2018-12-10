package Controllers;

import General.PapaController;
import Models.HomePageModel;
import Views.HomePageView;
import Views.IView;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class HomePageController extends AController {


    private HomePageView view;
    private HomePageModel model;
    Map<String, List<String>> vacations;

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

    public void openRegisterWindow() {
        SwapScene(PapaController.Views.RegisterWindow);
    }


    public void getVacations() {
       model.getVacations();
       //this.view.addToTable(insert);

    }



}