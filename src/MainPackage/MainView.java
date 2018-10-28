package MainPackage;

import General.AController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import interfaces.IView;

public class MainView implements IView{

    @FXML
    private Button b_create;
    @FXML
    private Button b_update;
    @FXML
    private Button b_delete;
    @FXML
    private Button b_read;

    private MainController controller;


    public MainView() {

    }

    @Override
    public void setController(AController controller) {
        this.controller = (MainController) controller;
    }

    public void openCreateWindow(ActionEvent actionEvent) {
        this.controller.openCreateWindow();
    }

    public void openReadWindow(ActionEvent actionEvent) { this.controller.openReadWindow();}

    public void openUpdateWindow(ActionEvent actionEvent) { this.controller.openUpdateWindow(); }

    public void openDeleteWindow(ActionEvent actionEvent) { this.controller.openDeleteWindow(); }
}
