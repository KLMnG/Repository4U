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
        vacations = model.getVacations();
        List<String[]> insert = new ArrayList<>();
        String insertToTable[];
        for (String str :vacations.keySet()) {
            insertToTable = null;
            //from
            insertToTable[0] = vacations.get(str).get(1);
            //days
            insertToTable[1] = vacations.get(str).get(10);
            //depart
            insertToTable[2] = vacations.get(str).get(2);
            //return
            insertToTable[3] = vacations.get(str).get(3);
            //travellers
            insertToTable[4] = vacations.get(str).get(9);
            //price
            insertToTable[5] = vacations.get(str).get(14);
            insert.add(insertToTable);


        }
        this.view.addToTable(insert);

    }



}
