package Views;

import Controllers.AController;
import Controllers.HomePageController;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;


import java.util.List;
import java.util.Observable;

public class HomePageView implements IView{

    public TableView tv_vacations;
    private HomePageController controller;
    public TableColumn col_from;


    public void Signin(ActionEvent actionEvent) {
        this.controller.openLoginWindow();
    }

    public void Signup(ActionEvent actionEvent) {
        this.controller.openRegisterWindow();
    }
    @Override
    public void setController(AController controller) {
        this.controller = (HomePageController) controller;
        initialize();
    }

    private void initialize() {
        this.controller.getVacations();
    }

    public void addToTable(List<String[]> listVac){

     
    }



}
